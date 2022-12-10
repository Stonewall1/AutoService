package com.autoservice.service;

import com.autoservice.dto.MasterDto;
import com.autoservice.entity.Master;
import com.autoservice.entity.Role;
import com.autoservice.exception.MasterNotFoundException;
import com.autoservice.repository.MasterRepository;
import com.autoservice.service.mapper.MasterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MasterService {
    private final MasterRepository masterRepository;
    private final MasterMapper masterMapper;

    public MasterService(MasterRepository masterRepository, MasterMapper masterMapper) {
        this.masterRepository = masterRepository;
        this.masterMapper = masterMapper;
    }

    public void save(MasterDto masterDto) {
        Master master = masterMapper.convertMasterDtoToMaster(masterDto);
        master.setRole(Role.MASTER);
        masterRepository.save(master);
    }

    public Master findByPhoneNumber(String phoneNumber) {
        Optional<Master> byPhoneNumber = masterRepository.findByPhoneNumber(phoneNumber);
        if (byPhoneNumber.isPresent()) {
            return byPhoneNumber.get();
        } else throw new MasterNotFoundException();
    }

    public boolean masterExists(String phoneNumber) {
        Optional<Master> byPhoneNumber = masterRepository.findByPhoneNumber(phoneNumber);
        return byPhoneNumber.isPresent();
    }
}
