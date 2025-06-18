package com.sliit.fasttrack_logistics.dao;

import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.DeliverySchedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryScheduleDao {

    // Book a delivery slot
    public boolean bookDeliverySlot(DeliverySchedule schedule) {
        boolean isSuccess = false;
        String sql = "INSERT INTO delivery_schedule (shipment_id, delivery_date, delivery_time_slot, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, schedule.getShipmentId());
            stmt.setDate(2, schedule.getDeliveryDate());
            stmt.setString(3, schedule.getDeliveryTimeSlot());
            stmt.setString(4, schedule.getStatus());

            isSuccess = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error booking delivery slot: " + e.getMessage());
        }

        return isSuccess;
    }

    // Get all scheduled deliveries for a shipment
    public List<DeliverySchedule> getSchedulesByShipment(String shipmentId) {
        List<DeliverySchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM delivery_schedule WHERE shipment_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, shipmentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DeliverySchedule schedule = new DeliverySchedule();
                schedule.setId(rs.getInt("id"));
                schedule.setShipmentId(rs.getString("shipment_id"));
                schedule.setDeliveryDate(rs.getDate("delivery_date"));
                schedule.setDeliveryTimeSlot(rs.getString("delivery_time_slot"));
                schedule.setStatus(rs.getString("status"));

                schedules.add(schedule);
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("Error retrieving schedules: " + e.getMessage());
        }

        return schedules;
    }

    // Update delivery status in real-time
    public boolean updateDeliveryStatus(int scheduleId, String newStatus) {
        boolean isSuccess = false;
        String sql = "UPDATE delivery_schedule SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, scheduleId);

            isSuccess = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error updating delivery status: " + e.getMessage());
        }

        return isSuccess;
    }

    public List<DeliverySchedule> getAllSchedules() {
        List<DeliverySchedule> schedules = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM delivery_schedule"; // use your actual table name
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    DeliverySchedule schedule = new DeliverySchedule();
                    schedule.setId(rs.getInt("id"));  // adjust column names accordingly
                    schedule.setShipmentId(rs.getString("shipment_id"));
                    schedule.setDeliveryDate(rs.getDate("delivery_date"));
                    schedule.setDeliveryTimeSlot(rs.getString("delivery_time_slot"));
                    schedule.setStatus(rs.getString("status"));
                    // set other properties as needed

                    schedules.add(schedule);
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public boolean deleteScheduleById(int scheduleId) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM delivery_schedule WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, scheduleId);

                int rowsDeleted = stmt.executeUpdate();
                isSuccess = rowsDeleted > 0;

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in deleteScheduleById: " + e.getMessage());
        }

        return isSuccess;
    }

}
