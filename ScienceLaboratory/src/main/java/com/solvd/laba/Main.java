package com.solvd.laba;

import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.domain.Scientist;
import com.solvd.laba.service.impl.ScientistServiceImpl;
import com.solvd.laba.service.interfaces.ScientistService;

public class Main {
    public static void main(String[] args) {
        ScientistService scientistService = new ScientistServiceImpl();

        try {
            // Example of adding a new scientist
            Department department = new Department(1, "Biology");
            ResearchArea area = new ResearchArea(1, "Genetics");
            Scientist newScientist = new Scientist(0, "Jane Doe", "jane.deose@example.com", department, area);
            scientistService.addScientist(newScientist);

            // Example of retrieving and updating a scientist
            Scientist scientist = scientistService.getScientistById(1);
            if (scientist != null) {
                scientist.setName("Jane Smith");
                scientistService.updateScientist(scientist);
            }

            // Example of deleting a scientist
            scientistService.deleteScientist(181);

            // Example of retrieving all scientists
            for (Scientist s : scientistService.getAllScientists()) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
