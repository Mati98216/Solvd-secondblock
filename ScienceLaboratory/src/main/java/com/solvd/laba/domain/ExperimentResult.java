package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentResult implements IdentifiableEntity<Integer>{
    private int resultId;
    private Experiment experiment;
    private Analysis analysis;
    private String resultDetails;
    @Override
    public void setId(Number id) {
        this.resultId = id.intValue();
    }

    @Override
    public String toString() {
        return "ExperimentResult{" +
                "resultId=" + resultId +
                ", experiment=" + experiment +
                ", analysis=" + analysis +
                ", resultDetails='" + resultDetails + '\'' +
                '}';
    }

    public static class Builder {
        private int resultId;
        private Experiment experiment;
        private Analysis analysis;
        private String resultDetails;

        public Builder resultId(int resultId) {
            this.resultId = resultId;
            return this;
        }

        public Builder experiment(Experiment experiment) {
            this.experiment = experiment;
            return this;
        }

        public Builder analysis(Analysis analysis) {
            this.analysis = analysis;
            return this;
        }

        public Builder resultDetails(String resultDetails) {
            this.resultDetails = resultDetails;
            return this;
        }

        public ExperimentResult build() {
            ExperimentResult experimentResult = new ExperimentResult();
            experimentResult.resultId = this.resultId;
            experimentResult.experiment = this.experiment;
            experimentResult.analysis = this.analysis;
            experimentResult.resultDetails = this.resultDetails;
            return experimentResult;
        }
    }
}
