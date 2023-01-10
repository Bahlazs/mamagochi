package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.Granny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class GrannyRepository {

    private ArrayList<Granny> grannies;

    @Autowired
    public GrannyRepository() {
        this.grannies = new ArrayList<>();
    }

    public void createGranny(int id, String name) {
        grannies.add(new Granny(id, name));
    }

    public Granny findGrannyById(int id) {
        Granny granny = null;
        for (int i = 0; i < grannies.size(); i++) {
            if (id == grannies.get(i).getId()) {
                granny = grannies.get(i);
            }
        }
        return granny;
    }




}
