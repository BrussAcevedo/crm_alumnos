package com.praxis.crmAlumnos.service;

import com.praxis.crmAlumnos.Vo.AlumnoVO;
import com.praxis.crmAlumnos.model.Alumno;

public interface ServicioAlumno {

	public AlumnoVO findAll();

	public AlumnoVO findById(Integer id);

	public AlumnoVO save(Alumno alumno);

	public AlumnoVO update(Alumno alumno);

	public AlumnoVO deleteById(Integer id);

}
