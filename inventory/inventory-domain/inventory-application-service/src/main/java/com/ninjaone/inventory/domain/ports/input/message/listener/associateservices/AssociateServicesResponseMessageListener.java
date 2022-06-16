package com.ninjaone.inventory.domain.ports.input.message.listener.associateservices;

import com.ninjaone.inventory.domain.dto.message.AssociateServiceResponse;

public interface AssociateServicesResponseMessageListener {

    void servicesAssociated(AssociateServiceResponse associateServiceResponse);

    void servicesNoAssociated(AssociateServiceResponse associateServiceResponse);

}
