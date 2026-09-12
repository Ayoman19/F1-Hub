package com.example.sehja.repository;

import com.example.sehja.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findAllByOrderByDateAsc();
}
