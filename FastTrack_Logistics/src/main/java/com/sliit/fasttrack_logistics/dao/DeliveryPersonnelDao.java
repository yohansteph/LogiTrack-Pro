package com.sliit.fasttrack_logistics.dao;

import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.DeliveryPersonnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersonnelDao {

    // List all delivery personnel
    public List<DeliveryPersonnel> listPersonnel() {
        List<DeliveryPersonnel> personnelList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM delivery_personnel";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    DeliveryPersonnel dp = new DeliveryPersonnel();
                    dp.setId(rs.getInt("id"));
                    dp.setName(rs.getString("name"));
                    dp.setContactNumber(rs.getString("contact_number"));
                    dp.setSchedule(rs.getString("schedule"));
                    dp.setAssignedRoute(rs.getString("assigned_route"));
                    dp.setDeliveryHistory(rs.getString("delivery_history"));
                    personnelList.add(dp);
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in listPersonnel: " + e.getMessage());
        }

        return personnelList;
    }

    // Add new delivery personnel
    public boolean addPersonnel(DeliveryPersonnel dp) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO delivery_personnel (name, contact_number, schedule, assigned_route, delivery_history) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, dp.getName());
                stmt.setString(2, dp.getContactNumber());
                stmt.setString(3, dp.getSchedule());
                stmt.setString(4, dp.getAssignedRoute());
                stmt.setString(5, dp.getDeliveryHistory());

                int rowsInserted = stmt.executeUpdate();
                isSuccess = rowsInserted > 0;

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in addPersonnel: " + e.getMessage());
        }

        return isSuccess;
    }

    // Update existing delivery personnel
    public boolean updatePersonnel(DeliveryPersonnel dp) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE delivery_personnel SET name = ?, contact_number = ?, schedule = ?, assigned_route = ?, delivery_history = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, dp.getName());
                stmt.setString(2, dp.getContactNumber());
                stmt.setString(3, dp.getSchedule());
                stmt.setString(4, dp.getAssignedRoute());
                stmt.setString(5, dp.getDeliveryHistory());
                stmt.setInt(6, dp.getId());

                int rowsUpdated = stmt.executeUpdate();
                isSuccess = rowsUpdated > 0;

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in updatePersonnel: " + e.getMessage());
        }

        return isSuccess;
    }

    // Delete delivery personnel by id
    public boolean deletePersonnel(int id) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM delivery_personnel WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, id);

                int rowsDeleted = stmt.executeUpdate();
                isSuccess = rowsDeleted > 0;

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in deletePersonnel: " + e.getMessage());
        }

        return isSuccess;
    }

    // Get a delivery personnel by id
    public DeliveryPersonnel getPersonnelById(int id) {
        DeliveryPersonnel dp = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM delivery_personnel WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    dp = new DeliveryPersonnel();
                    dp.setId(rs.getInt("id"));
                    dp.setName(rs.getString("name"));
                    dp.setContactNumber(rs.getString("contact_number"));
                    dp.setSchedule(rs.getString("schedule"));
                    dp.setAssignedRoute(rs.getString("assigned_route"));
                    dp.setDeliveryHistory(rs.getString("delivery_history"));
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error in getPersonnelById: " + e.getMessage());
        }

        return dp;
    }
}

