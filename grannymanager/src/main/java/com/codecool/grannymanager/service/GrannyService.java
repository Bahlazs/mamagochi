package com.codecool.grannymanager.service;

import com.codecool.grannymanager.model.Granny;
import com.codecool.grannymanager.repository.GrannyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrannyService {
    private final GrannyRepository grannyRepository;
    @Autowired
    public GrannyService(GrannyRepository grannyRepository) {
        this.grannyRepository = grannyRepository;
    }

    public Granny findGrannyById(int id) {
        return grannyRepository.findGrannyById(id);
    }

    public void increaseStat(Stat stat) {
        grannyRepository.updateStat(stat);
    }

}
