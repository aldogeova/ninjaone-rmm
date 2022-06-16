package com.ninjaone.service.domain.mapper;

import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.dto.command.associate.device.service.AssociateDeviceServiceRequest;
import com.ninjaone.service.domain.entity.ServiceType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Component
public class AssociateDeviceServiceDataMapper {
    public List<ServiceType> servicesTypeRequestToServiceType(AssociateDeviceServiceRequest associateDeviceServiceRequest) {

        return associateDeviceServiceRequest.getDeviceServices().stream()
                .map(deviceService -> ServiceType.builder()

                        .serviceTypeId(new ServiceTypeId(identifierToUuid(deviceService.getServiceTypeId())))
                        .typeDeviceId(new TypeDeviceId(identifierToUuid(deviceService.getTypeDeviceId())))
                        .build())
                .collect(Collectors.toList());

    }
}
