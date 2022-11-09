package com.romario.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romario.os.domain.Cliente;
import com.romario.os.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}
