package com.sliit.fasttrack_logistics.dao;

import com.sliit.fasttrack_logistics.config.DBConnection;
import com.sliit.fasttrack_logistics.models.Driver;
import EmailUtil.EmailUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriverDao {

    // List all drivers
    public List<Driver> listDrivers() {
        List<Driver> driverList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM drivers";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Driver driver = new Driver();
                    driver.setId(rs.getInt("id"));
                    driver.setName(rs.getString("name"));
                    driver.setLicenseNumber(rs.getString("license_number"));
                    driver.setContact(rs.getString("contact"));
                    driver.setStatus(rs.getString("status"));
                    driver.setEmail(rs.getString("DriverEmail"));
                    driverList.add(driver);
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return driverList;
    }

    // Add a new driver
    public boolean addDriver(Driver driver) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO drivers (name, license_number, contact, status, DriverEmail) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, driver.getName());
                stmt.setString(2, driver.getLicenseNumber());
                stmt.setString(3, driver.getContact());
                stmt.setString(4, driver.getStatus());
                stmt.setString(5, driver.getEmail());

                int rowsInserted = stmt.executeUpdate();
                isSuccess = rowsInserted > 0;

                if (isSuccess && driver.getEmail() != null && !driver.getEmail().isEmpty()) {
                    EmailUtil.sendEmail(
                        driver.getEmail(),
                        "Welcome to FastTrack Logistics",
                        "Dear " + driver.getName() + ",\n\nYou have been added as a driver in the FastTrack Logistics system.\n\nStatus: " + driver.getStatus() + "\n\nThank you!"
                    );
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Update an existing driver
    public boolean updateDriver(Driver driver) {
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE drivers SET name = ?, license_number = ?, contact = ?, status = ?, DriverEmail = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, driver.getName());
                stmt.setString(2, driver.getLicenseNumber());
                stmt.setString(3, driver.getContact());
                stmt.setString(4, driver.getStatus());
                stmt.setString(5, driver.getEmail());
                stmt.setInt(6, driver.getId());

                int rowsUpdated = stmt.executeUpdate();
                isSuccess = rowsUpdated > 0;

                if (isSuccess && driver.getEmail() != null && !driver.getEmail().isEmpty()) {
                    EmailUtil.sendEmail(
                        driver.getEmail(),
                        "Profile Updated - FastTrack Logistics",
                        "Dear " + driver.getName() + ",\n\nYour profile information has been updated.\n\nStatus: " + driver.getStatus() + "\n\nThank you!"
                    );
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Delete a driver
    public boolean deleteDriver(int driverId) {
        boolean isSuccess = false;

        // First, fetch the driver's email
        Driver driver = getDriverById(driverId);

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM drivers WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, driverId);

                int rowsDeleted = stmt.executeUpdate();
                isSuccess = rowsDeleted > 0;

                if (isSuccess && driver != null && driver.getEmail() != null && !driver.getEmail().isEmpty()) {
                    EmailUtil.sendEmail(
                        driver.getEmail(),
                        "Account Removed - FastTrack Logistics",
                        "Dear " + driver.getName() + ",\n\nYour account has been removed from the FastTrack Logistics system.\n\nThank you!"
                    );
                }

                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isSuccess;
    }

    // Get a driver by ID
    public Driver getDriverById(int driverId) {
        Driver driver = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM drivers WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, driverId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    driver = new Driver();
                    driver.setId(rs.getInt("id"));
                    driver.setName(rs.getString("name"));
                    driver.setLicenseNumber(rs.getString("license_number"));
                    driver.setContact(rs.getString("contact"));
                    driver.setStatus(rs.getString("status"));
                    driver.setEmail(rs.getString("DriverEmail"));
                }

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return driver;
    }
}
