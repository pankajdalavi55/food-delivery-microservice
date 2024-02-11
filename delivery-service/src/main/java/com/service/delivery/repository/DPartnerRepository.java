package com.service.delivery.repository;

import com.service.delivery.entity.DPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DPartnerRepository extends JpaRepository<DPartner, Long> {
}
