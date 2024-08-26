package com.praxis.crmAlumnos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praxis.crmAlumnos.Vo.CursoVO;
import com.praxis.crmAlumnos.dao.CursoDao;
import com.praxis.crmAlumnos.dao.objetoPers.CursoIdNombre;
import com.praxis.crmAlumnos.model.Curso;

import jakarta.transaction.Transactional;

@Service
public class ServicioCursoImp implements ServicioCurso {

	@Autowired
	private CursoDao cursoDao;

	@Override
	public CursoVO findAll() {
		CursoVO cursoVo = new CursoVO();

		try {
			List<Curso> cursos = (List<Curso>) cursoDao.findAll();

			if (cursos.size() == 0) {
				cursoVo.setEstado(0);
				cursoVo.setMensaje("No se detectan cursos en la base de datos");
				cursoVo.setCursos(cursos);
			} else {
				cursoVo.setEstado(1);
				cursoVo.setMensaje("Cursos encontrados, cantidad de cursos: " + cursos.size());
				cursoVo.setCursos(cursos);
			}

		} catch (Exception e) {
			cursoVo.setEstado(100);
			cursoVo.setMensaje("Error de exeption (Metodo: findAll): " + e.getMessage());
			cursoVo.setCursos(new ArrayList<Curso>());
		}
		return cursoVo;
	}

	@Override
	public CursoVO findById(Integer id) {
		CursoVO cursoVo = new CursoVO();

		try {
			
			Curso curso = cursoDao.findById(id).orElse(null);
			if (curso == null) {
				cursoVo.setEstado(0);
				cursoVo.setMensaje("No se encontro el curso id: "+id);
				cursoVo.setCursos(new ArrayList<Curso>());
			} else {
				List<Curso> cursos = new ArrayList<>();
				cursos.add(curso);
				cursoVo.setEstado(1);
				cursoVo.setMensaje("Curso Encontrado con id: "+id);
				cursoVo.setCursos(cursos);
			}
		} catch (Exception e) {
			cursoVo.setEstado(100);
			cursoVo.setMensaje("Error de exeption (Metodo: findById): " + e.getMessage());
			cursoVo.setCursos(new ArrayList<Curso>());
		}

		return cursoVo;
	}

	@Transactional
	@Override
	public CursoVO save(Curso curso) {
		CursoVO cursoVo = new CursoVO();
		try {
			Curso cursoInsertado = cursoDao.save(curso);
			
			
			if (cursoInsertado == null) {
				cursoVo.setEstado(0);
				cursoVo.setMensaje("No se pudo guardar el curso: "+ curso.getNombre());
			}else {
				cursoVo.setEstado(1);
				cursoVo.setMensaje("Curso ingresado con éxito: "+ curso.getNombre());
			}
			
			
		} catch (Exception e) {
			cursoVo.setEstado(100);
			cursoVo.setMensaje("Error de exeption (Metodo: save): " + e.getMessage());
		}
		
		return cursoVo;
	}

	
	
	@Transactional
	@Override
	public CursoVO update(Curso curso) {
	
		CursoVO cursoVo = new CursoVO();
		try {
			
			Curso cursoTemp  = cursoDao.findById(curso.getIdCurso()).orElse(null);
			
			curso.setAlumnos(cursoTemp.getAlumnos());			
			
			Curso cursoInsertado = cursoDao.save(curso);
			
			
			
			
			
			if (cursoInsertado == null) {
				cursoVo.setEstado(0);
				cursoVo.setMensaje("No se puedo actualizar el curso: "+ curso.getNombre());
			}else {
				cursoVo.setEstado(1);
				cursoVo.setMensaje("Curso actualizado con éxito: "+ curso.getNombre());
			}
			
			
		} catch (Exception e) {
			cursoVo.setEstado(100);
			cursoVo.setMensaje("Error de exeption (Metodo: update): " + e.getMessage());
		}
		
		return cursoVo;
	}

	
	@Transactional
	@Override
	public CursoVO deleteById(Integer id) {
			
		CursoVO cursoVo = new CursoVO();
		
		try {
			cursoDao.deleteById(id);
			
			if (cursoDao.existsById(id)) {
				cursoVo.setEstado(0);
				cursoVo.setMensaje("No se pudo eliminar el curso: " + id);
			}else {
				cursoVo.setEstado(1);
				cursoVo.setMensaje("Curso Eliminado con éxito. Alumnos del curso fueron eliminados: ");
			}	
			
		} catch (Exception e) {
			cursoVo.setEstado(100);
			cursoVo.setMensaje("Error de exeption (Metodo: DeleteById): " + e.getMessage());
		}
		
		return cursoVo;
	
	}

	@Override
	public List<CursoIdNombre> findIdandName() {
		
		List<Curso> cursos = (List<Curso>) cursoDao.findAll();
		
		List<CursoIdNombre> cursosNombresId = new ArrayList<>();
		for (Curso curso : cursos) {
			
			CursoIdNombre cursoIDNombre= new CursoIdNombre(curso.getIdCurso(), curso.getNombre());
			cursosNombresId.add(cursoIDNombre);
			
		}
				
		return cursosNombresId;
	}

}
