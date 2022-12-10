package com.autoservice.service.mapper;

import com.autoservice.dto.MasterDto;
import com.autoservice.entity.Master;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    public Master convertMasterDtoToMaster(MasterDto masterDto) {
        return new Master(masterDto.getFirstName(), masterDto.getLastName(), masterDto.getPhoneNumber());
    }
}
