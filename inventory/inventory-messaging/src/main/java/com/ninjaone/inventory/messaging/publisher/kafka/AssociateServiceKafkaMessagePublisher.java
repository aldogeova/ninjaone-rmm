package com.ninjaone.inventory.messaging.publisher.kafka;

import com.ninjaone.inventory.domain.config.DeviceConfigData;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;
import com.ninjaone.inventory.domain.ports.output.message.publisher.service.AssociateServiceMessagePublisher;
import com.ninjaone.inventory.messaging.mapper.DeviceMessagingDataMapper;
import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel;
import com.ninjaone.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class AssociateServiceKafkaMessagePublisher implements AssociateServiceMessagePublisher {

    private final DeviceMessagingDataMapper deviceMessagingDataMapper;
    private final DeviceConfigData deviceConfigData;
    private final KafkaProducer<String, AssociateServiceRequestAvroModel> kafkaProducer;

    public AssociateServiceKafkaMessagePublisher(DeviceMessagingDataMapper deviceMessagingDataMapper,
                                                 DeviceConfigData deviceConfigData,
                                                 KafkaProducer<String, AssociateServiceRequestAvroModel> kafkaProducer) {
        this.deviceMessagingDataMapper = deviceMessagingDataMapper;
        this.deviceConfigData = deviceConfigData;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(AssociateServicesEvent associateServiceEvent) {
        String deviceId = associateServiceEvent.getDevice().getId().getValue().toString();
        log.info("Received DeviceCreateEvent for device id: {}", deviceId);


        try {
            AssociateServiceRequestAvroModel associateServiceRequestAvroModel =
                    deviceMessagingDataMapper.associateServiceEventToAssociateServiceRequestAvroModel(associateServiceEvent);

            kafkaProducer.send("associate-services-request",
                    deviceId,
                    associateServiceRequestAvroModel,
                    getKafkaCallback("associate-services-response",
                            associateServiceRequestAvroModel));



            log.info("AssociateServiceRequestAvroModel sent to Kafka for device id: {}", associateServiceRequestAvroModel.getDeviceId());
        } catch (Exception e) {
            log.error("Error while sending AssociateServiceRequestAvroModel to Kafka for device id: {}, error{}", deviceId, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ListenableFutureCallback<SendResult<String, AssociateServiceRequestAvroModel>> getKafkaCallback(String associateServiceResponseTopicName, AssociateServiceRequestAvroModel associateServiceRequestAvroModel) {
        return new ListenableFutureCallback<SendResult<String, AssociateServiceRequestAvroModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending AssociateServiceRequestAvroModel message" +
                        " to kafka with device id: {}, error: {}", associateServiceRequestAvroModel.getDeviceId(), throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, AssociateServiceRequestAvroModel> stringAssociateServiceRequestAvroModelSendResult) {
                RecordMetadata recordMetadata = stringAssociateServiceRequestAvroModelSendResult.getRecordMetadata();
                log.info("Received successful response from Kafka for device id: {}, topic: {}, partition: {}, offset: {}, Timestamp{}",
                        associateServiceRequestAvroModel.getDeviceId(),
                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp());

            }
        };
    }

}
