
package com.sliit.fasttrack_logistics.Services;

import com.sliit.fasttrack_logistics.dao.DriverDao;
import com.sliit.fasttrack_logistics.models.Driver;

import java.util.List;

public class DriverService {
    private DriverDao driverDao;

    public DriverService() {
        driverDao = new DriverDao(); // Assumes DBConnection is handled inside DriverDao
    }

    // List all drivers
    public List<Driver> getAllDrivers() {
        return driverDao.listDrivers();
    }

    // Add a driver
    public boolean addDriver(Driver driver) {
        return driverDao.addDriver(driver);
    }

    // Update a driver
    public boolean updateDriver(Driver driver) {
        return driverDao.updateDriver(driver);
    }

    // Delete a driver
    public boolean deleteDriver(int driverId) {
        return driverDao.deleteDriver(driverId);
    }

    // Get a single driver by ID
    public Driver getDriverById(int driverId) {
        return driverDao.getDriverById(driverId);
    }
}

