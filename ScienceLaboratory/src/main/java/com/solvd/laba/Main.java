package com.solvd.laba;

import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.domain.Scientist;
import com.solvd.laba.service.jdbc.*;
import com.solvd.laba.service.interfaces.ScientistService;

import com.solvd.laba.domain.*;
import com.solvd.laba.service.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ScientistService scientistService = new ScientistServiceImpl();
        ExperimentService experimentService = new ExperimentServiceImpl();
        EquipmentService equipmentService = new EquipmentServiceImpl();
        PublicationService publicationService = new PublicationServiceImpl();
        LaboratoryAssistantService laboratoryAssistantService = new LaboratoryAssistantServiceImpl();

        try {
            // Adding a new scientist
            Department department = new Department(1, "Biology");
            ResearchArea area = new ResearchArea(1, "Genetics");
            Scientist newScientist = new Scientist(333, "Jane Doe", "jane.ds@example.com", department, area);
            scientistService.addScientist(newScientist);

            // Adding a laboratory assistant
            LaboratoryAssistant assistant = new LaboratoryAssistant(0, "ds Doe", "ds.dssdgfdljytrllbgdsbbsdoe@example.com", department, area);
            laboratoryAssistantService.addLaboratoryAssistant(assistant);

            // Adding equipment
            Equipment microscope = new Equipment(0, "Microscope",department);
            equipmentService.addEquipment(microscope);

            // Conducting an experiment
            Experiment experiment = new Experiment(0, "DNA Analysis", newScientist);
            experimentService.addExperiment(experiment);
            experimentService.addEquipmentToExperiment(experiment.getExperimentId(), microscope.getEquipmentId());

            // Publishing results
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            Publication publication = new Publication(0, "DNA Analysis Findings", sqlDate, newScientist);
            publicationService.addPublication(publication);
            publicationService.addExperimentToPublication(experiment.getExperimentId(), publication.getPublicationId());

            // Updating a scientist
            Scientist retrievedScientist = scientistService.getScientistById(newScientist.getScientistId());
            if (retrievedScientist != null) {
                retrievedScientist.setName("Jane Smith");
                scientistService.updateScientist(retrievedScientist);
            }

            // Deleting a scientist
            scientistService.deleteScientist(177);
            // Retrieving and displaying all scientists
            logger.info("All Scientists:");
            for (Scientist s : scientistService.getAllScientists()) {
                logger.info(s);
            }

            // Displaying all equipment
            logger.info("All Equipment:");
            for (Equipment e : equipmentService.getAllEquipment()) {
                logger.info(e);
            }

            // Displaying all experiments
            logger.info("All Experiments:");
            for (Experiment e : experimentService.getAllExperiments()) {
                logger.info(e);
            }

            // Displaying all publications
            logger.info("All Publications:");
            for (Publication p : publicationService.getAllPublications()) {
                logger.info(p);
            }

        } catch (Exception e) {
            logger.error("An error occurred: ", e);
        }
    }
}
