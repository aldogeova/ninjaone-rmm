package com.ninjaone.inventory.messaging.listener.kafka;

import com.ninjaone.inventory.domain.ports.input.message.listener.associateservices.AssociateServicesResponseMessageListener;
import com.ninjaone.inventory.messaging.mapper.DeviceMessagingDataMapper;
import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceResponseAvroModel;
import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus;
import com.ninjaone.kafka.associate.service.avro.model.DeviceServiceStatus;
import com.ninjaone.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AssociateServiceResponseKafkaListener implements KafkaConsumer<AssociateServiceResponseAvroModel> {

    private final AssociateServicesResponseMessageListener associateServicesResponseMessageListener;
    private final DeviceMessagingDataMapper deviceMessagingDataMapper;


    public AssociateServiceResponseKafkaListener(AssociateServicesResponseMessageListener associateServicesResponseMessageListener,
                                                 DeviceMessagingDataMapper deviceMessagingDataMapper) {
        this.associateServicesResponseMessageListener = associateServicesResponseMessageListener;
        this.deviceMessagingDataMapper = deviceMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.associate-services-consumer-group-id}",
            topics = "${inventory-service.associate-services-response-topic-name}")
    public void receive(@Payload List<AssociateServiceResponseAvroModel> message,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of associate services responses received with keys:{}, partitions:{} and offsets: {}",
                message.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        message.forEach(associateServiceResponseAvroModel -> {

            boolean errorsValidation = associateServiceResponseAvroModel.getDeviceServicesResponseAvroModel().stream().anyMatch(x -> x.getValidation() == DeviceServiceStatus.INVALID);

            if(!errorsValidation
             || AssociateServiceStatus.VALIDATED == associateServiceResponseAvroModel.getAssociateServiceStatus()) {
                log.info("The devices services are validated successfully for device id: {}", associateServiceResponseAvroModel.getDeviceId());
                associateServicesResponseMessageListener.servicesAssociated(
                        deviceMessagingDataMapper.associateServiceResponseAvroModelToAssociateServiceResponse(associateServiceResponseAvroModel));
            } else if(errorsValidation || AssociateServiceStatus.INVALID == associateServiceResponseAvroModel.getAssociateServiceStatus() ) {
                log.info("The devices services are invalidated or rejected for device id: {}", associateServiceResponseAvroModel.getDeviceId());
                associateServicesResponseMessageListener.servicesNoAssociated(deviceMessagingDataMapper.associateServiceResponseAvroModelToAssociateServiceResponse(associateServiceResponseAvroModel));
            }
        });

    }
}
