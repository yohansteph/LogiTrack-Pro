package com.sliit.fasttrack_logistics.Services;



import com.sliit.fasttrack_logistics.dao.ShipmentDao;
import com.sliit.fasttrack_logistics.models.Shipments;

import java.util.List;

public class ShipmentService {
    private ShipmentDao shipmentDao = new ShipmentDao();

    public List<Shipments> listShipments() {
        return shipmentDao.listShipments();
    }

    public boolean addShipment(Shipments shipment) {
        return shipmentDao.addShipment(shipment);
    }

    public boolean updateShipment(Shipments shipment) {
        return shipmentDao.updateShipment(shipment);
    }

    public boolean deleteShipment(String shipmentId) {
        return shipmentDao.deleteShipment(shipmentId);
    }

    public int countByStatus(String status) {
        return shipmentDao.countByStatus(status);
    }
}

