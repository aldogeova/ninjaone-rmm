package com.ninjaone.service.domain;

import com.ninjaone.service.domain.dto.command.associate.device.service.AssociateDeviceServiceRequest;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.event.IncorrectDeviceServiceEvent;
import com.ninjaone.service.domain.event.ReviewDeviceServiceEvent;
import com.ninjaone.service.domain.ports.input.message.listener.AssociateServicesRequestMessageListener;
import com.ninjaone.service.domain.ports.output.message.publisher.AssociatedDeviceServiceMessagePublisher;
import com.ninjaone.service.domain.ports.output.message.publisher.IncorrectDeviceServiceMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AssociateServicesRequestMessageListenerImpl implements AssociateServicesRequestMessageListener {

    private final AssociateServicesRequestHelper associateServicesRequestHelper;

    public AssociateServicesRequestMessageListenerImpl(AssociateServicesRequestHelper associateServicesRequestHelper) {
        this.associateServicesRequestHelper = associateServicesRequestHelper;
    }

    @Override
    public void review(AssociateDeviceServiceRequest associateDeviceServiceRequest) {
        ReviewDeviceServiceEvent reviewDeviceServiceEvent = associateServicesRequestHelper.review(associateDeviceServiceRequest);
        fireEvent(reviewDeviceServiceEvent);
    }

    private void fireEvent(ReviewDeviceServiceEvent reviewDeviceServiceEvent) {
        log.info("Publishing review eevent with id {} associate to a device id {}", reviewDeviceServiceEvent.getAssociateDeviceService().getId(),
                reviewDeviceServiceEvent.getAssociateDeviceService().getDeviceId());

        reviewDeviceServiceEvent.fire();

//        if(reviewDeviceServiceEvent instanceof AssociatedDeviceServiceEvent) {
//            associatedDeviceServiceMessagePublisher.publish((AssociatedDeviceServiceEvent) reviewDeviceServiceEvent);
//        } else {
//            incorrectDeviceServiceMessagePublisher.publish((IncorrectDeviceServiceEvent) reviewDeviceServiceEvent);
//        }

    }
}
