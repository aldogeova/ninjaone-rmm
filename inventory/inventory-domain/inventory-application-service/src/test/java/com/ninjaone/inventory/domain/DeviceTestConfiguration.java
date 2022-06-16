package com.ninjaone.inventory.domain;

import com.ninjaone.inventory.domain.ports.output.message.publisher.service.AssociateServiceMessagePublisher;
import com.ninjaone.inventory.domain.ports.output.repository.CustomerRepository;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceRepository;
import com.ninjaone.inventory.domain.ports.output.repository.ServiceTypeRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(scanBasePackages = "com.ninjaone.inventory")
//public class DeviceTestConfiguration {
//
//    @Bean
//    public AssociateServiceMessagePublisher deviceCreatedAssociateServiceMessagePublisher(){
//        return Mockito.mock(AssociateServiceMessagePublisher.class);
//    }
//
//    @Bean
//    public DeviceRepository deviceRepository(){
//        return Mockito.mock(DeviceRepository.class);
//    }
//
//    @Bean
//    public CustomerRepository customerRepository(){
//        return Mockito.mock(CustomerRepository.class);
//    }
//
//    @Bean
//    public ServiceTypeRepository serviceTypeRepository(){
//        return Mockito.mock(ServiceTypeRepository.class);
//    }
//
//    @Bean
//    public InventoryDomainService inventoryDomainService(){
//        return  new InventoryDomainServiceImpl();
//    }
//}
