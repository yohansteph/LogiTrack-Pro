/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sliit.fasttrack_logistics.Services;

import com.sliit.fasttrack_logistics.dao.DeliveryPersonnelDao;
import com.sliit.fasttrack_logistics.models.DeliveryPersonnel;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DeliveryPersonnelService {
     private DeliveryPersonnelDao dao = new DeliveryPersonnelDao();

    public List<DeliveryPersonnel> getAllPersonnel() {
        return dao.listPersonnel();
    }

    public boolean addPersonnel(DeliveryPersonnel dp) {
        return dao.addPersonnel(dp);
    }

    public boolean updatePersonnel(DeliveryPersonnel dp) {
        return dao.updatePersonnel(dp);
    }

    public boolean deletePersonnel(int id) {
        return dao.deletePersonnel(id);
    }

    public DeliveryPersonnel getPersonnelById(int id) {
        return dao.getPersonnelById(id);
    }
    
}
