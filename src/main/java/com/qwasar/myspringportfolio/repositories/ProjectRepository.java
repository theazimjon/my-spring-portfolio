package com.qwasar.myspringportfolio.repositories;
import com.qwasar.myspringportfolio.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
