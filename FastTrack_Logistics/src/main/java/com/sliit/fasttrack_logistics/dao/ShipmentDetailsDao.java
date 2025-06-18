package com.sliit.fasttrack_logistics.dao;

import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.Shipments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShipmentDetailsDao {

    /**
     * Get shipment details by shipment ID (int)
     */
    public Shipments getShipmentDetailsById(int shipmentId) {
        Shipments shipment = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM shipments WHERE Shipment = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, shipmentId);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    shipment = new Shipments();
                    shipment.setShipment(rs.getString("Shipment"));  // Assuming Shipment is int in model too
                    shipment.setSenderName(rs.getString("SenderName"));
                    shipment.setPackageContents(rs.getString("PackageContents"));
                    shipment.setDeliveryStatus(rs.getString("DeliveryStatus"));
                    shipment.setEmail(rs.getString("Shipment_email"));
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error fetching shipment details: " + e.getMessage());
        }

        return shipment;
    }
}
