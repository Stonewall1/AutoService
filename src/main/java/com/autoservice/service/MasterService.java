package com.autoservice.service;

import com.autoservice.dto.MasterDto;
import com.autoservice.entity.Master;
import com.autoservice.entity.Role;
import com.autoservice.exception.MasterNotFoundException;
import com.autoservice.repository.MasterRepository;
import com.autoservice.service.mapper.MasterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Master findById(long id) {
        Optional<Master> byId = masterRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new MasterNotFoundException();
    }

    public boolean masterExists(String phoneNumber) {
        Optional<Master> byPhoneNumber = masterRepository.findByPhoneNumber(phoneNumber);
        return byPhoneNumber.isPresent();
    }

    @Transactional(readOnly = true)
    public List<Master> findAllMasters() {
        return masterRepository.findAll();
    }

    public void deleteById(long id) {
        masterRepository.deleteById(id);
    }
}
