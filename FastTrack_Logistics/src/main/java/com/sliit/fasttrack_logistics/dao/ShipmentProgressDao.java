package com.sliit.fasttrack_logistics.dao;

import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.ShipmentProgress;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmentProgressDao {

    // Add new ShipmentProgress record
    public boolean addShipmentProgress(ShipmentProgress sp) {
        String sql = "INSERT INTO shipment_progress (shipment_id, current_location, estimated_delivery_time, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sp.getShipmentId());
            pstmt.setString(2, sp.getCurrentLocation());
            pstmt.setTimestamp(3, sp.getEstimatedDeliveryTime());
            pstmt.setString(4, sp.getStatus());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update existing ShipmentProgress by id
    public boolean updateShipmentProgress(ShipmentProgress sp) {
        String sql = "UPDATE shipment_progress SET shipment_id=?, current_location=?, estimated_delivery_time=?, status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sp.getShipmentId());
            pstmt.setString(2, sp.getCurrentLocation());
            pstmt.setTimestamp(3, sp.getEstimatedDeliveryTime());
            pstmt.setString(4, sp.getStatus());
            pstmt.setInt(5, sp.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete ShipmentProgress by id
    public boolean deleteShipmentProgress(int id) {
        String sql = "DELETE FROM shipment_progress WHERE id=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load all ShipmentProgress records from the database
    public List<ShipmentProgress> getAllShipmentProgress() {
        List<ShipmentProgress> progressList = new ArrayList<>();

        String sql = "SELECT * FROM shipment_progress";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ShipmentProgress sp = new ShipmentProgress();
                sp.setId(rs.getInt("id"));
                sp.setShipmentId(rs.getInt("shipment_id"));
                sp.setCurrentLocation(rs.getString("current_location"));
                sp.setEstimatedDeliveryTime(rs.getTimestamp("estimated_delivery_time"));
                sp.setStatus(rs.getString("status"));
                sp.setUpdatedAt(rs.getTimestamp("updated_at"));

                progressList.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return progressList;
    }
}
