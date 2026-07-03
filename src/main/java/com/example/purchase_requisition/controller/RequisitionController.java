package com.example.purchase_requisition.controller;

import com.example.purchase_requisition.dto.CreateRequisitionRequest;
import com.example.purchase_requisition.dto.RequisitionProcessResponse;
import com.example.purchase_requisition.dto.RequisitionSummaryResponse;
import com.example.purchase_requisition.model.Requisition;
import com.example.purchase_requisition.service.RequisitionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/requisitions")
public class RequisitionController {

    private final RequisitionService requisitionService;

    public RequisitionController(RequisitionService requisitionService) {
        this.requisitionService = requisitionService;
    }

    @PostMapping
    public ResponseEntity<Requisition> createRequisition(@Valid @RequestBody CreateRequisitionRequest request) {
        Requisition created = requisitionService.createRequisition(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<RequisitionProcessResponse> processRequisition(@PathVariable Long id) {
        RequisitionProcessResponse response = requisitionService.processRequisition(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<RequisitionSummaryResponse>> getRequisitionSummary() {
        List<RequisitionSummaryResponse> summary = requisitionService.getRequisitionSummary();
        return ResponseEntity.ok(summary);
    }
}
