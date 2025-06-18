package com.sliit.fasttrack_logistics.Services;

import com.sliit.fasttrack_logistics.dao.DeliveryScheduleDao;
import com.sliit.fasttrack_logistics.models.DeliverySchedule;
import java.util.List;

public class DeliveryScheduleService {

    private DeliveryScheduleDao deliveryScheduleDao = new DeliveryScheduleDao();

    // Book a new delivery slot
    public boolean bookDeliverySlot(DeliverySchedule schedule) {
        return deliveryScheduleDao.bookDeliverySlot(schedule);
    }

    // Get all schedules by shipment ID
    public List<DeliverySchedule> getSchedulesByShipment(String shipmentId) {
        return deliveryScheduleDao.getSchedulesByShipment(shipmentId);
    }

    // Update delivery status
    public boolean updateDeliveryStatus(int scheduleId, String newStatus) {
        return deliveryScheduleDao.updateDeliveryStatus(scheduleId, newStatus);
    }

    public List<DeliverySchedule> getAllSchedules() {
        return deliveryScheduleDao.getAllSchedules();
    }

    public boolean deleteScheduleById(int scheduleId) {
        return deliveryScheduleDao.deleteScheduleById(scheduleId);
    }

}
