package com.example.purchase_requisition.service;

import com.example.purchase_requisition.dto.CreateRequisitionRequest;
import com.example.purchase_requisition.dto.RequisitionProcessResponse;
import com.example.purchase_requisition.dto.RequisitionSummaryResponse;
import com.example.purchase_requisition.model.Requisition;
import com.example.purchase_requisition.model.RequisitionStatus;
import com.example.purchase_requisition.repository.RequisitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequisitionService {

    private final RequisitionRepository requisitionRepository;

    public RequisitionService(RequisitionRepository requisitionRepository) {
        this.requisitionRepository = requisitionRepository;
    }

    public Requisition createRequisition(CreateRequisitionRequest request) {
        Requisition requisition = new Requisition();
        requisition.setEmployeeId(request.getEmployeeId());
        requisition.setProduct(request.getProduct());
        requisition.setQuantity(request.getQuantity());
        requisition.setAmount(request.getAmount());
        requisition.setDescription(request.getDescription());
        requisition.setAccount("IT");
        requisition.setStatus(RequisitionStatus.PENDING);
        return requisitionRepository.save(requisition);
    }

    public Optional<Requisition> getRequisitionById(Long id) {
        return requisitionRepository.findById(id);
    }

    public RequisitionProcessResponse processRequisition(Long id) {
        Requisition requisition = requisitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requisition not found with id: " + id));

        boolean available = isProductAvailable(requisition.getProduct(), requisition.getQuantity());

        if (available) {
            requisition.setStatus(RequisitionStatus.DISPATCHED);
            requisitionRepository.save(requisition);
            return new RequisitionProcessResponse(requisition.getId(), "DISPATCHED", requisition.getStatus().name());
        }

        requisition.setStatus(RequisitionStatus.PURCHASED_AND_DISPATCHED);
        requisitionRepository.save(requisition);
        return new RequisitionProcessResponse(requisition.getId(), "PURCHASED_AND_DISPATCHED", requisition.getStatus().name());
    }

    public List<RequisitionSummaryResponse> getRequisitionSummary() {
        return requisitionRepository.findAll().stream()
                .map(requisition -> new RequisitionSummaryResponse(
                        requisition.getEmployeeId(),
                        requisition.getProduct(),
                        requisition.getQuantity(),
                        requisition.getAmount(),
                        requisition.getStatus().name()))
                .collect(Collectors.toList());
    }

    private boolean isProductAvailable(String product, Integer quantity) {
        if (product == null || quantity == null) {
            return false;
        }

        String normalizedProduct = product.toLowerCase();
        return switch (normalizedProduct) {
            case "laptop" -> quantity <= 5;
            case "keyboard" -> quantity <= 10;
            case "monitor" -> quantity <= 3;
            default -> quantity <= 2;
        };
    }
}
