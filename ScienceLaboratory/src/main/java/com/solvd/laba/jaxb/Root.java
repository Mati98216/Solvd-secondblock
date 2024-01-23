package com.solvd.laba.jaxb;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.ExperimentTimeline;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class Root {
    private Experiment experiment;
    private ExperimentTimeline experimentTimeline;

    public Root() {

    }

    public Root(Experiment experiment, ExperimentTimeline experimentTimeline) {
        this.experiment = experiment;
        this.experimentTimeline = experimentTimeline;
    }

    @XmlElement(name = "experiment")
    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @XmlElement(name = "experimentTimeline")
    public ExperimentTimeline getExperimentTimeline() {
        return experimentTimeline;
    }

    public void setExperimentTimeline(ExperimentTimeline experimentTimeline) {
        this.experimentTimeline = experimentTimeline;
    }
}

