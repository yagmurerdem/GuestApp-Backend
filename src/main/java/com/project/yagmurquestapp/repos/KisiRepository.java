package com.project.yagmurquestapp.repos;

import com.project.yagmurquestapp.entities.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KisiRepository extends JpaRepository<Kisi,Long> {
    Kisi findByUserName(String userName);

}
