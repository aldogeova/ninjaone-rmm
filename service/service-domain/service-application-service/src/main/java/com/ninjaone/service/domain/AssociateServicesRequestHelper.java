package com.ninjaone.service.domain;

import com.ninjaone.domain.valueobject.*;
import com.ninjaone.service.domain.dto.command.associate.device.service.AssociateDeviceServiceRequest;
import com.ninjaone.service.domain.dto.command.associate.device.service.DeviceServiceRequest;
import com.ninjaone.service.domain.entity.AssociateDeviceService;
import com.ninjaone.service.domain.entity.DeviceServiceAssociate;
import com.ninjaone.service.domain.entity.ServiceType;
import com.ninjaone.service.domain.event.ReviewDeviceServiceEvent;
import com.ninjaone.service.domain.mapper.AssociateDeviceServiceDataMapper;
import com.ninjaone.service.domain.ports.output.message.publisher.AssociatedDeviceServiceMessagePublisher;
import com.ninjaone.service.domain.ports.output.message.publisher.IncorrectDeviceServiceMessagePublisher;
import com.ninjaone.service.domain.ports.output.repository.ServiceRepository;
import com.ninjaone.service.domain.ports.output.repository.ServiceTypeRepository;
import com.ninjaone.service.domain.valueobject.DeviceServiceAssociatetId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AssociateServicesRequestHelper {

    private final AssociateDeviceServiceService associateDeviceServiceService;

    private final AssociateDeviceServiceDataMapper associateDeviceServiceDataMapper;

    private final ServiceRepository serviceRepository;

    private final ServiceTypeRepository serviceTypeRepository;

    private final AssociatedDeviceServiceMessagePublisher associatedDeviceServiceMessagePublisher;
    private final IncorrectDeviceServiceMessagePublisher incorrectDeviceServiceMessagePublisher;





    public AssociateServicesRequestHelper(AssociateDeviceServiceService associateDeviceServiceService,
                                          AssociateDeviceServiceDataMapper associateDeviceServiceDataMapper,
                                          ServiceRepository serviceRepository,
                                          ServiceTypeRepository serviceTypeRepository,
                                          AssociatedDeviceServiceMessagePublisher associatedDeviceServiceMessagePublisher,
                                          IncorrectDeviceServiceMessagePublisher incorrectDeviceServiceMessagePublisher) {
        this.associateDeviceServiceService = associateDeviceServiceService;
        this.associateDeviceServiceDataMapper = associateDeviceServiceDataMapper;
        this.serviceRepository = serviceRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.associatedDeviceServiceMessagePublisher = associatedDeviceServiceMessagePublisher;
        this.incorrectDeviceServiceMessagePublisher = incorrectDeviceServiceMessagePublisher;
    }

    @Transactional
    public ReviewDeviceServiceEvent review(AssociateDeviceServiceRequest associateDeviceServiceRequest) {
        log.info("Reviewing associate device service to device with id: {}", associateDeviceServiceRequest.getDeviceId());
        List<ServiceType> servicesTypesToReview = associateDeviceServiceDataMapper.servicesTypeRequestToServiceType(associateDeviceServiceRequest);
        List<String> failureMessages = new ArrayList<>();
        AssociateDeviceService asociateDeviceService = reviewServices(associateDeviceServiceRequest, failureMessages);
        ReviewDeviceServiceEvent reviewDeviceServiceEvent = associateDeviceServiceService.review(asociateDeviceService, failureMessages,
                associatedDeviceServiceMessagePublisher, incorrectDeviceServiceMessagePublisher);
        return reviewDeviceServiceEvent;
    }

    private AssociateDeviceService reviewServices(AssociateDeviceServiceRequest associateDeviceServiceRequest, List<String> failureMessages) {
        List<DeviceServiceAssociate> deviceServices = reviewDeviceServices(associateDeviceServiceRequest.getDeviceServices(), failureMessages);

        boolean errorValidation =  deviceServices.stream().anyMatch (d -> !d.getValidated());

        return AssociateDeviceService.builder()
                .id(new AssociateDeviceId(identifierToUuid(associateDeviceServiceRequest.getId())))
                .deviceId(new DeviceId(identifierToUuid(associateDeviceServiceRequest.getDeviceId())))
                .deviceServicesAssociate(deviceServices)
                .deviceStatus(errorValidation ? DeviceStatus.INVALID : DeviceStatus.VALIDATED)
                .build();
    }

    private List<DeviceServiceAssociate> reviewDeviceServices(List<DeviceServiceRequest> deviceServices, List<String> failureMessages) {

        return deviceServices.stream().map(ds ->
                {
                    Optional<ServiceType> serviceType =  serviceTypeRepository.findByIdAndTypeDeviceId(new ServiceTypeId(identifierToUuid(ds.getServiceTypeId())),
                                new TypeDeviceId(identifierToUuid(ds.getTypeDeviceId())));

                    if(serviceType.isPresent()) {

                        ServiceType serviceTypeFound = serviceType.get();

                        return DeviceServiceAssociate.builder()
                                .deviceServiceAssociatetId(new DeviceServiceAssociatetId(identifierToUuid(ds.getId())))
                                .serviceTypeId(serviceTypeFound.getId().getValue())
                                .price(serviceTypeFound.getPrice())
                                .validated(true)
                                .build();
                    }else{

                        failureMessages.add("Service type not found or not is avaliable for the id"+ds.getServiceTypeId());

                        return DeviceServiceAssociate.builder()
                                .deviceServiceAssociatetId(new DeviceServiceAssociatetId(identifierToUuid(ds.getId())))
                                .serviceTypeId(identifierToUuid(ds.getServiceTypeId()))
                                .price(Money.ZERO)
                                .validated(false)
                                .message("Service type not found or not is avaliable for this type of device")
                                .build();
                    }

                }
                ).collect(Collectors.toList());

    }


}
