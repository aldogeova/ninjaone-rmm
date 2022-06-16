package com.ninjaone.service.dataaccess.type.device.mapper;

import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.dataaccess.type.device.entity.TypeDeviceViewEntity;
import com.ninjaone.service.domain.entity.TypeDeviceView;
import org.springframework.stereotype.Component;

@Component
public class TypeDeviceViewDataAccessMapper {

    public TypeDeviceView typeDeviceViewEntityToTypeDeviceView(TypeDeviceViewEntity typeDeviceView) {
        return TypeDeviceView.builder()
                .id(new TypeDeviceId(typeDeviceView.getId()))
                .operativeSystemId(typeDeviceView.getOperativeSystemId())
                .deviceClassificationId(typeDeviceView.getDeviceClassificationId())
                .enabled(typeDeviceView.isEnabled())
                .createdDate(typeDeviceView.getCreatedDate())
                .modifiedDate(typeDeviceView.getModifiedDate())
                .build();
    }

}
