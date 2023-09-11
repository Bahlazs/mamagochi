package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.Granny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface GrannyRepository  extends JpaRepository<Granny, Long> {
    Optional<Granny> findGrannyById(Long id);

}
