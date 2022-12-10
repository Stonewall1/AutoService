package com.autoservice.repository;

import com.autoservice.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterRepository extends JpaRepository<Master, Long> {
    Optional<Master> findByPhoneNumber(String phoneNumber);
}
