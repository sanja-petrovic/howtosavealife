package com.example.isa.service.interfaces;

import com.example.isa.dto.locator.TrackingRequestDto;
import com.example.isa.model.locator.TrackingRequestStatus;

import java.util.UUID;

public interface TrackingRequestService {
    void updateStatus(UUID requestId, TrackingRequestStatus status);
    void create(TrackingRequestDto trackingRequestDto);
}