/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sliit.fasttrack_logistics.models;

public class Shipments {

    String Shipment;
    String SenderName;
    String PackageContents;
    String DeliveryStatus;
    String Email;

    public Shipments() {

    }

    ;

    public String getShipment() {
        return Shipment;
    }

    public String getSenderName() {
        return SenderName;
    }

    public String getPackageContents() {
        return PackageContents;
    }

    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setShipment(String Shipment) {
        this.Shipment = Shipment;
    }

    public void setSenderName(String SenderName) {
        this.SenderName = SenderName;
    }

    public void setPackageContents(String PackageContents) {
        this.PackageContents = PackageContents;
    }

    public void setDeliveryStatus(String DeliveryStatus) {
        this.DeliveryStatus = DeliveryStatus;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
