package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.Granny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GrannyRepository  extends JpaRepository<Granny, Long> {
    Granny findGrannyById(Long id);

}
