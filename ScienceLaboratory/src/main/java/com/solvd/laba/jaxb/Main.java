package com.solvd.laba.jaxb;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.ExperimentTimeline;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static com.solvd.laba.Main.logger;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File xmlFile = new File("src/main/resources/xmlfile.xml");
            Root root = (Root) unmarshaller.unmarshal(xmlFile);

            Experiment experiment = root.getExperiment();
            ExperimentTimeline experimentTimeline = root.getExperimentTimeline();


            logger.info(experiment.toString());
            logger.info(experimentTimeline.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

