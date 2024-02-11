package com.service.delivery.service;

import com.service.delivery.entity.DPartner;
import com.service.delivery.repository.DPartnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class DPartnerServiceImpl implements DPartnerService{

    private final DPartnerRepository dPartnerRepository;

    @Override
    public List<DPartner> getAllDPartners() {
        return dPartnerRepository.findAll();
    }

    @Override
    public DPartner getDPartnerById(Long dPartnerId) {
        Optional<DPartner> dPartnerOptional = dPartnerRepository.findById(dPartnerId);
        return dPartnerOptional.orElse(null);
    }

    @Override
    public DPartner createDPartner(DPartner dPartner) {
        dPartner.setCreatedDate(LocalDateTime.now());
        return dPartnerRepository.save(dPartner);
    }

    @Override
    public DPartner updateDPartner(Long dPartnerId, DPartner updatedDPartner) {
        DPartner existingDPartner = dPartnerRepository.findById(dPartnerId).orElse(null);
        if (existingDPartner == null) {
            throw new IllegalArgumentException("DPartner with ID " + dPartnerId + " not found.");
        }
        // Update the fields
        existingDPartner.setName(updatedDPartner.getName());
        existingDPartner.setEmail(updatedDPartner.getEmail());
        existingDPartner.setPhone(updatedDPartner.getPhone());
        existingDPartner.setCurrentLocation(updatedDPartner.getCurrentLocation());
        existingDPartner.setActive(updatedDPartner.isActive());
        existingDPartner.setUpdatedDate(LocalDateTime.now());
        // Save and return the updated DPartner
        return dPartnerRepository.save(existingDPartner);
    }

    @Override
    public void deleteDPartner(Long dPartnerId) {
        DPartner existingDPartner = dPartnerRepository.findById(dPartnerId).orElse(null);
        if (existingDPartner == null) {
            throw new IllegalArgumentException("DPartner with ID " + dPartnerId + " not found.");
        }
        dPartnerRepository.delete(existingDPartner);
    }

}
