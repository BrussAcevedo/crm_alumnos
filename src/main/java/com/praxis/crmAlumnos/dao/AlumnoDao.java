package com.praxis.crmAlumnos.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.praxis.crmAlumnos.model.Alumno;

@Repository
public interface AlumnoDao extends CrudRepository<Alumno, Integer>{
	

	
}
