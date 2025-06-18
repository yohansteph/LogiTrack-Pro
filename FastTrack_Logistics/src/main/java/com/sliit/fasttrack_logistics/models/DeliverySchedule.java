package com.sliit.fasttrack_logistics.models;

import java.sql.Date;

public class DeliverySchedule {
    private int id;
    private String shipmentId;
    private Date deliveryDate;
    private String deliveryTimeSlot;
    private String status;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getShipmentId() { return shipmentId; }
    public void setShipmentId(String shipmentId) { this.shipmentId = shipmentId; }

    public Date getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getDeliveryTimeSlot() { return deliveryTimeSlot; }
    public void setDeliveryTimeSlot(String deliveryTimeSlot) { this.deliveryTimeSlot = deliveryTimeSlot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

