package com.ninjaone.service.domain;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.domain.valueobject.DeviceStatus;
import com.ninjaone.service.domain.entity.AssociateDeviceService;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.event.IncorrectDeviceServiceEvent;
import com.ninjaone.service.domain.event.ReviewDeviceServiceEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.ninjaone.domain.constant.DomainConstants.UTC;

@Slf4j
public class AssociateDeviceServiceServiceImpl implements AssociateDeviceServiceService {


    @Override
    public ReviewDeviceServiceEvent review(AssociateDeviceService associateDeviceService,
                                           List<String> failureMessages,
                                           DomainEventPublisher<AssociatedDeviceServiceEvent> associatedDeviceServiceEventDomainEventPublisher,
                                           DomainEventPublisher<IncorrectDeviceServiceEvent> incorrectDeviceServiceEventDomainEventPublisher) {
        associateDeviceService.validate(failureMessages);
        associateDeviceService.initialize();

        if (failureMessages.isEmpty()) {
            log.info("Device services is reviewed and accepted for associate to device with id: {}",
                    associateDeviceService.getDeviceId());
            associateDeviceService.updateStatus(DeviceStatus.VALIDATED);
            return new AssociatedDeviceServiceEvent(associateDeviceService,
                    ZonedDateTime.now(ZoneId.of(UTC)), associatedDeviceServiceEventDomainEventPublisher );
        } else {
            log.info("Device services is reviewed and rejected for associate to device with id: {}",
                    associateDeviceService.getDeviceId());
            associateDeviceService.updateStatus(DeviceStatus.INVALID);
            return new IncorrectDeviceServiceEvent(associateDeviceService,
                    ZonedDateTime.now(ZoneId.of(UTC)), failureMessages, incorrectDeviceServiceEventDomainEventPublisher);
        }

    }


}
