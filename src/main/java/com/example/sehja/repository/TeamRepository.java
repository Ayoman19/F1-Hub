package com.example.sehja.repository;

import com.example.sehja.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByOrderByPositionAsc();
    Team findByConstructorSlug(String constructorSlug);
}
