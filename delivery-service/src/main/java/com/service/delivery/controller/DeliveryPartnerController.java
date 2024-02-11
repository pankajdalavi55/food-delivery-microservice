package com.service.delivery.controller;

import com.service.delivery.entity.DPartner;
import com.service.delivery.response.APIResponse;
import com.service.delivery.response.ErrorResponse;
import com.service.delivery.response.SuccessResponse;
import com.service.delivery.service.DPartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/dpartners")
public class DeliveryPartnerController {

    private final DPartnerService dPartnerService;

    @GetMapping("/fetch-partners")
    public ResponseEntity<APIResponse> getAllDPartners() {
        try {
            List<DPartner> partnerList = dPartnerService.getAllDPartners();
            SuccessResponse<List<DPartner>> response = partnerList.isEmpty() ?
                    new SuccessResponse<>(true, "There is no Delivery Partner..!", null)
                    : new SuccessResponse<>(true, "Fetched Delivery Partner Successfully..!", partnerList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Exception occurred while fetching Delivery Partners: {}", e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(false, "Failed to fetch Delivery Partners. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }

    @PostMapping("/add-partner")
    public ResponseEntity<APIResponse> createDPartner(@RequestBody DPartner dPartner) {
        try {
            DPartner createdDPartner = dPartnerService.createDPartner(dPartner);
            SuccessResponse<DPartner> response = new SuccessResponse<>(true, "DPartner created successfully.", createdDPartner);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Exception occurred while adding DPartner: {}", e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(false, "Failed to add DPartner. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/fetch-partner/{dPartnerId}")
    public ResponseEntity<APIResponse> fetchDPartnerById(@PathVariable Long dPartnerId) {
        try {
            DPartner dPartner = dPartnerService.getDPartnerById(dPartnerId);
            if (dPartner != null) {
                SuccessResponse<DPartner> response = new SuccessResponse<>(true, "Fetched DPartner successfully.", dPartner);
                return ResponseEntity.ok(response);
            } else {
                ErrorResponse errorResponse = new ErrorResponse(false, "DPartner not found with ID: " + dPartnerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching DPartner: {}", e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(false, "Failed to fetch DPartner. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{dPartnerId}")
    public ResponseEntity<APIResponse> updateDPartner(@PathVariable Long dPartnerId, @RequestBody DPartner updatedDPartner) {
        try {
            DPartner updatedDPartnerEntity = dPartnerService.updateDPartner(dPartnerId, updatedDPartner);
            SuccessResponse<DPartner> response = new SuccessResponse<>(true, "DPartner updated successfully.", updatedDPartnerEntity);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Exception occurred while updating DPartner with ID {}: {}", dPartnerId, e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(false, "Failed to update DPartner with ID " + dPartnerId + ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{dPartnerId}")
    public ResponseEntity<APIResponse> deleteDPartner(@PathVariable Long dPartnerId) {
        try {
            dPartnerService.deleteDPartner(dPartnerId);
            SuccessResponse<String> response = new SuccessResponse<>(true, "DPartner deleted successfully.", "DPartner with ID " + dPartnerId + " deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Exception occurred while deleting DPartner with ID {}: {}", dPartnerId, e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(false, "Failed to delete DPartner with ID " + dPartnerId + ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
