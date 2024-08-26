package com.praxis.crmAlumnos.service;

import java.util.List;

import com.praxis.crmAlumnos.Vo.CursoVO;
import com.praxis.crmAlumnos.dao.objetoPers.CursoIdNombre;
import com.praxis.crmAlumnos.model.Curso;

public interface ServicioCurso {
	public CursoVO findAll();

	public CursoVO findById(Integer id);

	public CursoVO save(Curso curso);

	public CursoVO update(Curso curso);

	public CursoVO deleteById(Integer id);

	public List<CursoIdNombre> findIdandName();
}
