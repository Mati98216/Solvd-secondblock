package com.solvd.laba.json;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.laba.domain.Experiment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.solvd.laba.Main.logger;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(new File("src/main/resources/data.json"));

            JsonNode experimentsNode = rootNode.path("experiments");

            List<Experiment> experiments = mapper.convertValue(
                    experimentsNode, new TypeReference<List<Experiment>>() {});


            experiments.forEach(logger::info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
