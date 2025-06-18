package com.sliit.fasttrack_logistics.dao;

import EmailUtil.EmailUtil;
import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.Shipments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShipmentDao {

    // List all shipments
    public List<Shipments> listShipments() {
        List<Shipments> shipmentList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM shipments";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Shipments shipment = new Shipments();
                    shipment.setShipment(rs.getString("Shipment"));
                    shipment.setSenderName(rs.getString("SenderName"));
                    shipment.setPackageContents(rs.getString("PackageContents"));
                    shipment.setDeliveryStatus(rs.getString("DeliveryStatus"));
                    shipment.setEmail(rs.getString("Shipment_email"));

                    shipmentList.add(shipment);
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return shipmentList;
    }

    // Add a new shipment
    public boolean addShipment(Shipments shipment) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO shipments (Shipment, SenderName, PackageContents, DeliveryStatus, Shipment_email) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, shipment.getShipment());
                stmt.setString(2, shipment.getSenderName());
                stmt.setString(3, shipment.getPackageContents());
                stmt.setString(4, shipment.getDeliveryStatus());
                stmt.setString(5, shipment.getEmail());

                int rowsInserted = stmt.executeUpdate();
                isSuccess = rowsInserted > 0;

                if (isSuccess) {
                    // Send email notification about new shipment added
                    String toEmail = shipment.getEmail();
                    String subject = "Shipment Added Successfully";
                    String body = "Dear " + shipment.getSenderName() + ",\n\n"
                            + "Your shipment with ID: " + shipment.getShipment() + " has been successfully added.\n"
                            + "Current Status: " + shipment.getDeliveryStatus() + "\n\n"
                            + "Thank you for choosing FastTrack Logistics.";

                    EmailUtil.sendEmail(toEmail, subject, body);
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Update an existing shipment
    public boolean updateShipment(Shipments shipment) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE shipments SET SenderName = ?, PackageContents = ?, DeliveryStatus = ?, Shipment_email = ? WHERE Shipment = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, shipment.getSenderName());
                stmt.setString(2, shipment.getPackageContents());
                stmt.setString(3, shipment.getDeliveryStatus());
                stmt.setString(4, shipment.getEmail());
                stmt.setString(5, shipment.getShipment());

                int rowsUpdated = stmt.executeUpdate();
                isSuccess = rowsUpdated > 0;

                if (isSuccess) {
                    // Send email notification to customer
                    String toEmail = shipment.getEmail();
                    String subject = "Shipment Status Updated";
                    String body = "Dear " + shipment.getSenderName() + ",\n\n"
                            + "Your shipment with ID: " + shipment.getShipment() + " has been updated.\n"
                            + "Current Status: " + shipment.getDeliveryStatus() + "\n\n"
                            + "Thank you for choosing FastTrack Logistics.";

                    EmailUtil.sendEmail(toEmail, subject, body);
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Delete a shipment
    public boolean deleteShipment(String shipmentId) {
        boolean isSuccess = false;

        // Get shipment details before deleting
        Shipments shipment = getShipmentById(shipmentId);

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM shipments WHERE Shipment = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, shipmentId);

                int rowsDeleted = stmt.executeUpdate();
                isSuccess = rowsDeleted > 0;

                if (isSuccess && shipment != null) {
                    // Send email notification about shipment deletion
                    String toEmail = shipment.getEmail();
                    String subject = "Shipment Deleted";
                    String body = "Dear " + shipment.getSenderName() + ",\n\n"
                            + "Your shipment with ID: " + shipment.getShipment() + " has been deleted from our system.\n\n"
                            + "If you have any questions, please contact us.\n\n"
                            + "Thank you for choosing FastTrack Logistics.";

                    EmailUtil.sendEmail(toEmail, subject, body);
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Count shipments by delivery status
    public int countByStatus(String status) {
        int count = 0;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS count FROM shipments WHERE DeliveryStatus = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, status);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("count");
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return count;
    }

    private Shipments getShipmentById(String shipmentId) {
        Shipments shipment = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM shipments WHERE Shipment = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, shipmentId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    shipment = new Shipments();
                    shipment.setShipment(rs.getString("Shipment"));
                    shipment.setSenderName(rs.getString("SenderName"));
                    shipment.setPackageContents(rs.getString("PackageContents"));
                    shipment.setDeliveryStatus(rs.getString("DeliveryStatus"));
                    shipment.setEmail(rs.getString("Shipment_email"));
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return shipment;
    }
}


