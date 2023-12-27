package com.solvd.laba;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.*;
import com.solvd.laba.dao.interfaces.*;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.*;
import com.solvd.laba.service.impl.*;
import com.solvd.laba.service.interfaces.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/science_laboratory";
        String username = "root";
        String password = "root";
        Calendar calendar = Calendar.getInstance();
        ConnectionPool pool = new ConnectionPool(10, jdbcUrl, username, password);

        // Initialize DAOs
        ResearchAreaDAO researchAreaDAO = new ResearchAreaDAOImpl(pool);
        EquipmentDAO equipmentDAO = new EquipmentDAOImpl(pool);
        AnalysisDAO analysisDAO=new AnalysisDAOImpl(pool);
        DepartmentDAO departmentDAO=new DepartmentDAOImpl(pool);
        ExperimentDAO experimentDAO= new ExperimentDAOImpl(pool);
        ExperimentEquipmentDAO experimentEquipmentDAO=new ExperimentEquipmentDAOImpl(pool);
        ExperimentPublicationsDAO experimentPublicationsDAO= new ExperimentPublicationsDAOImpl(pool);
        ExperimentResultDAO experimentResultDAO = new ExperimentResultDAOImpl(pool);
        ExperimentTimelineDAO experimentTimelineDAO = new ExperimentTimelineDAOImpl(pool);
        LaboratoryAssistantDAO laboratoryAssistantDAO = new LaboratoryAssistantDAOImpl(pool);
        PublicationDAO publicationDAO = new PublicationDAOImpl(pool);
        ScientistDAO scientistDAO = new ScientistDAOImpl(pool);

        // Initialize Services
        ResearchAreaService researchAreaService = new ResearchAreaServiceImpl(researchAreaDAO);
        EquipmentService equipmentService = new EquipmentServiceImpl(equipmentDAO);
        AnalysisService analysisService = new AnalysisServiceImpl(analysisDAO);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDAO);
        ExperimentService experimentService = new ExperimentServiceImpl(experimentDAO);
        ExperimentEquipmentService experimentEquipmentService = new ExperimentEquipmentServiceImpl(experimentEquipmentDAO);
        ExperimentPublicationsService experimentPublicationsService = new ExperimentPublicationsServiceImpl(experimentPublicationsDAO);
        ExperimentResultService experimentResultService = new ExperimentResultServiceImpl(experimentResultDAO);
        ExperimentTimelineService experimentTimelineService = new ExperimentTimelineServiceImpl(experimentTimelineDAO);
        LaboratoryAssistantService laboratoryAssistantService = new LaboratoryAssistantServiceImpl(laboratoryAssistantDAO);
        PublicationService publicationService = new PublicationServiceImpl(publicationDAO);
        ScientistService scientistService = new ScientistServiceImpl(scientistDAO);


        // Mock Data for Demonstrations
        ResearchArea mockResearchArea = new ResearchArea(78, "Biology");
        Equipment mockEquipment = new Equipment(78, "Telescope", 1);
        Analysis mockAnalysis = new Analysis(100, "Spectral Analysis", 1, 2);
        Department mockDepartment = new Department(100, "Physics Department");
        Experiment mockExperiment = new Experiment(100, "Gravitational Waves Study", 1);
        ExperimentEquipment mockExperimentEquipment = new ExperimentEquipment(100, 1, 1);
        ExperimentPublications mockExperimentPublications = new ExperimentPublications(100, 1, 1);
        ExperimentResult mockExperimentResult = new ExperimentResult(100,1, 1, "Preliminary findings on gravitational waves");
        calendar.set(2023, Calendar.JANUARY, 1);
        Date startDate = (Date) calendar.getTime();

        calendar.set(2023, Calendar.DECEMBER, 31);
        Date endDate = (Date) calendar.getTime();
        ExperimentTimeline mockExperimentTimeline = new ExperimentTimeline(100,1, startDate, endDate);
        LaboratoryAssistant mockLaboratoryAssistant = new LaboratoryAssistant(100, "John Doe", "johndoe@example.com", 1, 1);
        calendar.set(2023, Calendar.DECEMBER, 23);
        Date publicationDate = (Date) calendar.getTime();
        Publication mockPublication = new Publication(100, "The Study of Black Holes", publicationDate, 1);
        Scientist mockScientist = new Scientist(100, "Jane Smith", "janesmith@example.com", 1, 1);


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
