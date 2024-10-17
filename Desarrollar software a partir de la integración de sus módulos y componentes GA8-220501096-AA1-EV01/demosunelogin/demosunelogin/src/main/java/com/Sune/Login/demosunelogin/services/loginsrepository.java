package com.Sune.Login.demosunelogin.services;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Sune.Login.demosunelogin.models.login;

public interface loginsrepository extends JpaRepository<login, Integer>{

}
