package com.sliit.fasttrack_logistics.Services;

import com.sliit.fasttrack_logistics.dao.ShipmentProgressDao;
import com.sliit.fasttrack_logistics.models.ShipmentProgress;

import java.util.List;

public class ShipmentProgressService {

    private ShipmentProgressDao shipmentProgressDao;

    public ShipmentProgressService() {
        shipmentProgressDao = new ShipmentProgressDao();
    }

    // Add shipment progress
    public boolean addShipmentProgress(ShipmentProgress sp) {
        return shipmentProgressDao.addShipmentProgress(sp);
    }

    // Update shipment progress
    public boolean updateShipmentProgress(ShipmentProgress sp) {
        return shipmentProgressDao.updateShipmentProgress(sp);
    }

    // Delete shipment progress by id
    public boolean deleteShipmentProgress(int id) {
        return shipmentProgressDao.deleteShipmentProgress(id);
    }

    // Get all shipment progress records
    public List<ShipmentProgress> getAllShipmentProgress() {
        return shipmentProgressDao.getAllShipmentProgress();
    }
}

