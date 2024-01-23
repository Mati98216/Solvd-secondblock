package com.solvd.laba.xml;

import com.solvd.laba.domain.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.laba.Main.logger;

public class XMLParser {

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/main/resources/xmlfile.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node experimentNode = doc.getElementsByTagName("experiment").item(0);
            Experiment experiment = parseExperiment(experimentNode);

            Node experimentTimelineNode = doc.getElementsByTagName("experimentTimeline").item(0);
            ExperimentTimeline experimentTimeline = parseExperimentTimeline(experimentTimelineNode);

            logger.info(experiment);
            logger.info(experimentTimeline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Experiment parseExperiment(Node experimentNode) {
        Experiment experiment = new Experiment();

        if (experimentNode.getNodeType() == Node.ELEMENT_NODE) {
            Element experimentElement = (Element) experimentNode;

            experiment.setExperimentId(Integer.parseInt(experimentElement.getElementsByTagName("experimentId").item(0).getTextContent()));
            experiment.setExperimentName(experimentElement.getElementsByTagName("experimentName").item(0).getTextContent());

            Node scientistNode = experimentElement.getElementsByTagName("scientist").item(0);
            experiment.setScientist(parseScientist(scientistNode));


            List<Equipment> equipmentList = new ArrayList<>();
            NodeList equipmentNodeList = experimentElement.getElementsByTagName("equipment");
            for (int i = 0; i < equipmentNodeList.getLength(); i++) {
                equipmentList.add(parseEquipment(equipmentNodeList.item(i)));
            }
            experiment.setEquipmentList(equipmentList);


        }

        return experiment;
    }

    private static Scientist parseScientist(Node scientistNode) {
        Scientist scientist = new Scientist();
        if (scientistNode.getNodeType() == Node.ELEMENT_NODE) {
            Element scientistElement = (Element) scientistNode;
            scientist.setScientistId(Integer.parseInt(scientistElement.getElementsByTagName("id").item(0).getTextContent()));
            scientist.setName(scientistElement.getElementsByTagName("name").item(0).getTextContent());
            scientist.setEmail(scientistElement.getElementsByTagName("email").item(0).getTextContent());

            Node departmentNode = scientistElement.getElementsByTagName("department").item(0);
            scientist.setDepartment(parseDepartment(departmentNode));
        }
        return scientist;
    }

    private static Department parseDepartment(Node departmentNode) {
        Department department = new Department();
        if (departmentNode.getNodeType() == Node.ELEMENT_NODE) {
            Element departmentElement = (Element) departmentNode;
            department.setDepartmentId(Integer.parseInt(departmentElement.getElementsByTagName("departmentId").item(0).getTextContent()));
            department.setDepartmentName(departmentElement.getElementsByTagName("departmentName").item(0).getTextContent());
        }
        return department;
    }

    private static Equipment parseEquipment(Node equipmentNode) {
        Equipment equipment = new Equipment();
        if (equipmentNode.getNodeType() == Node.ELEMENT_NODE) {
            Element equipmentElement = (Element) equipmentNode;
            equipment.setEquipmentId(Integer.parseInt(equipmentElement.getElementsByTagName("equipmentId").item(0).getTextContent()));
            equipment.setEquipmentName(equipmentElement.getElementsByTagName("equipmentName").item(0).getTextContent());

            Node departmentNode = equipmentElement.getElementsByTagName("department").item(0);
            equipment.setDepartment(parseDepartment(departmentNode));
        }
        return equipment;
    }

    private static ExperimentTimeline parseExperimentTimeline(Node timelineNode) {
        ExperimentTimeline timeline = new ExperimentTimeline();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (timelineNode.getNodeType() == Node.ELEMENT_NODE) {
            Element timelineElement = (Element) timelineNode;
            timeline.setTimelineId(Integer.parseInt(timelineElement.getElementsByTagName("timelineId").item(0).getTextContent()));

            try {
                String startDateStr = timelineElement.getElementsByTagName("startDate").item(0).getTextContent();
                String endDateStr = timelineElement.getElementsByTagName("endDate").item(0).getTextContent();

                java.util.Date startDateUtil = dateFormat.parse(startDateStr);
                java.util.Date endDateUtil = dateFormat.parse(endDateStr);

                timeline.setStartDate(new Date(startDateUtil.getTime()));
                timeline.setEndDate(new Date(endDateUtil.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeline;
    }

}
