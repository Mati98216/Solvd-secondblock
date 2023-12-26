package com.solvd.laba;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.EquipmentDAOImpl;
import com.solvd.laba.dao.impl.ResearchAreaDAOImpl;
import com.solvd.laba.dao.interfaces.EquipmentDAO;
import com.solvd.laba.dao.interfaces.ResearchAreaDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.service.impl.EquipmentServiceImpl;
import com.solvd.laba.service.impl.ResearchAreaServiceImpl;
import com.solvd.laba.service.interfaces.EquipmentService;
import com.solvd.laba.service.interfaces.ResearchAreaService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/science_laboratory";
        String username = "root";
        String password = "root";

        ConnectionPool pool = new ConnectionPool(10, jdbcUrl, username, password);

        // Initialize DAOs
        ResearchAreaDAO researchAreaDAO = new ResearchAreaDAOImpl(pool);
        EquipmentDAO equipmentDAO = new EquipmentDAOImpl(pool);


        // Initialize Services
        ResearchAreaService researchAreaService = new ResearchAreaServiceImpl(researchAreaDAO);
        EquipmentService equipmentService = new EquipmentServiceImpl(equipmentDAO);


        // Mock Data for Demonstrations
        ResearchArea mockResearchArea = new ResearchArea(78, "Biology");
        Equipment mockEquipment = new Equipment(78, "Telescope", 1);


        try {
            // Example operations for ResearchAreaService
            researchAreaService.addResearchArea(mockResearchArea);
            ResearchArea fetchedArea = researchAreaService.getResearchAreaById(1);
            List<ResearchArea> allAreas = researchAreaService.getAllResearchAreas();
            researchAreaService.updateResearchArea(mockResearchArea);
            researchAreaService.deleteResearchArea(78);


            // Example operations for EquipmentService
            equipmentService.addEquipment(mockEquipment);
            Equipment fetchedEquipment = equipmentService.getEquipmentById(1);
            List<Equipment> allEquipments = equipmentService.getAllEquipment();
            equipmentService.updateEquipment(mockEquipment);
            equipmentService.deleteEquipment(78);


        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            try {
                pool.closeAllConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
