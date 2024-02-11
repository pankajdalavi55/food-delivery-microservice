package com.service.delivery.service;

import com.service.delivery.entity.DPartner;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface DPartnerService {

    List<DPartner> getAllDPartners();

    DPartner createDPartner(DPartner dPartner);

    DPartner getDPartnerById(Long dPartnerId);

    DPartner updateDPartner(Long dPartnerId, DPartner updatedDPartner);

    void deleteDPartner(Long dPartnerId);
}
