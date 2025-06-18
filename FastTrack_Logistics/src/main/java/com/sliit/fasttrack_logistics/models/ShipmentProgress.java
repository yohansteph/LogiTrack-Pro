package com.sliit.fasttrack_logistics.models;

import java.sql.Timestamp;

public class ShipmentProgress {
    private int id;
    private int shipmentId;
    private String currentLocation;
    private Timestamp estimatedDeliveryTime;
    private String status;
    private Timestamp updatedAt;

    // Default constructor
    public ShipmentProgress() {}

    // Constructor with all fields
    public ShipmentProgress(int id, int shipmentId, String currentLocation, Timestamp estimatedDeliveryTime, String status, Timestamp updatedAt) {
        this.id = id;
        this.shipmentId = shipmentId;
        this.currentLocation = currentLocation;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Timestamp getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Timestamp estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ShipmentProgress{" +
                "id=" + id +
                ", shipmentId=" + shipmentId +
                ", currentLocation='" + currentLocation + '\'' +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

