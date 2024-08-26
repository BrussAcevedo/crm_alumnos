package com.praxis.crmAlumnos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.praxis.crmAlumnos.dao.objetoPers.CursoIdNombre;
import com.praxis.crmAlumnos.model.Curso;

@Repository
public interface CursoDao extends CrudRepository<Curso, Integer>{
	
	
}
