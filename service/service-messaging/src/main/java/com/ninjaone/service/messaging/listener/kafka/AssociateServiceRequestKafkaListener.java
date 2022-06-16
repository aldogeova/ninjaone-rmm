package com.ninjaone.service.messaging.listener.kafka;

import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel;
import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceStatus;
import com.ninjaone.kafka.consumer.KafkaConsumer;
import com.ninjaone.service.domain.ports.input.message.listener.AssociateServicesRequestMessageListener;
import com.ninjaone.service.messaging.mapper.AssociateDeviceServiceMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AssociateServiceRequestKafkaListener implements KafkaConsumer<AssociateServiceRequestAvroModel> {

    public final AssociateServicesRequestMessageListener associateServiceRequestMessageListener;
    public final AssociateDeviceServiceMessagingDataMapper associateDeviceServiceMessagingDataMapper;

    public AssociateServiceRequestKafkaListener(AssociateServicesRequestMessageListener associateServiceRequestMessageListener,
                                                AssociateDeviceServiceMessagingDataMapper associateDeviceServiceMessagingDataMapper) {
        this.associateServiceRequestMessageListener = associateServiceRequestMessageListener;
        this.associateDeviceServiceMessagingDataMapper = associateDeviceServiceMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.associate-services-consumer-group-id}",
            topics = "${service-service.associate-services-request-topic-name}")
    public void receive(@Payload List<AssociateServiceRequestAvroModel> message,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of associate services requests received with keys:{}, partitions:{} and offsets: {}",
                message.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        message.forEach(associateServiceRequestAvroModel -> {
            if(AssociateServiceStatus.PENDING == associateServiceRequestAvroModel.getAssociateServiceStatus() ) {
                log.info("Reviewing service request associated with the device with id : {}", associateServiceRequestAvroModel.getDeviceId());
                associateServiceRequestMessageListener.review(
                        associateDeviceServiceMessagingDataMapper.associateServiceRequestAvroModelToAssociateDeviceServiceRequest(associateServiceRequestAvroModel));
            }
        });





    }
}
