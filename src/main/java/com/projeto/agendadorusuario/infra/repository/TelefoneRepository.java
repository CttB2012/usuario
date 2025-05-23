package com.projeto.agendadorusuario.infra.repository;


import com.projeto.agendadorusuario.infra.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
