package com.ninjaone.service.messaging.publisher.kafka;

import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceRequestAvroModel;
import com.ninjaone.kafka.associate.service.avro.model.AssociateServiceResponseAvroModel;
import com.ninjaone.kafka.producer.service.KafkaProducer;
import com.ninjaone.service.domain.config.ServiceConfigData;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.ports.output.message.publisher.AssociatedDeviceServiceMessagePublisher;
import com.ninjaone.service.messaging.mapper.AssociateDeviceServiceMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class AsociatedDeviceServiceKafkaMessagePublisher implements AssociatedDeviceServiceMessagePublisher {

    private final AssociateDeviceServiceMessagingDataMapper associateDeviceServiceMessagingDataMapper;
    private final KafkaProducer<String, AssociateServiceResponseAvroModel> kafkaProducer;
    private final ServiceConfigData serviceConfigData;

    public AsociatedDeviceServiceKafkaMessagePublisher(AssociateDeviceServiceMessagingDataMapper associateDeviceServiceMessagingDataMapper,
                                                       KafkaProducer<String, AssociateServiceResponseAvroModel> kafkaProducer,
                                                       ServiceConfigData serviceConfigData) {
        this.associateDeviceServiceMessagingDataMapper = associateDeviceServiceMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.serviceConfigData = serviceConfigData;
    }

    @Override
    public void publish(AssociatedDeviceServiceEvent domainEvent) {
        String deviceId = domainEvent.getAssociateDeviceService().getDeviceId().getValue().toString();
        log.info("Received AssociatedDeviceServiceEvent for device id: {}", deviceId);
        try {
            AssociateServiceResponseAvroModel associateServiceResponseAvroModel = associateDeviceServiceMessagingDataMapper.associatedDeviceServiceEventToAssociateServiceResponseAvroModel(domainEvent);
            kafkaProducer.send("associate-services-response",
                    deviceId,
                    associateServiceResponseAvroModel,
                    getKafkaCallback("associate-services-response",
                            associateServiceResponseAvroModel));

            log.info("AssociateServiceResponseAvroModel sent to kafka for device id: {}", deviceId);
        } catch (Exception e) {
            log.error("Error while sending AssociateServiceResponseAvroModel to kafka for device id: {}, error: {}", deviceId, e.getMessage());
        }
    }


    private ListenableFutureCallback<SendResult<String, AssociateServiceResponseAvroModel>> getKafkaCallback(String associateServiceResponseTopicName,
                                                                                                             AssociateServiceResponseAvroModel associateServiceResponseAvroModel) {
        return new ListenableFutureCallback<SendResult<String, AssociateServiceResponseAvroModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending AssociateServiceRequestAvroModel message" +
                        " to kafka with device id: {}, error: {}", associateServiceResponseAvroModel.getDeviceId(), throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, AssociateServiceResponseAvroModel> stringAssociateServiceRequestAvroModelSendResult) {
                RecordMetadata recordMetadata = stringAssociateServiceRequestAvroModelSendResult.getRecordMetadata();
                log.info("Received successful response from Kafka for device id: {}, topic: {}, partition: {}, offset: {}, Timestamp{}",
                        associateServiceResponseAvroModel.getDeviceId(),
                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp());

            }
        };
    }
}
