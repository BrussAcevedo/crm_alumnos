package com.praxis.crmAlumnos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praxis.crmAlumnos.Vo.AlumnoVO;
import com.praxis.crmAlumnos.dao.AlumnoDao;
import com.praxis.crmAlumnos.model.Alumno;

import jakarta.transaction.Transactional;

@Service
public class ServicioAlumnoImp implements ServicioAlumno {

	@Autowired
	private AlumnoDao alumnoDao;

	@Override
	public AlumnoVO findAll() {
		AlumnoVO alumnoVo = new AlumnoVO();

		try {
			List<Alumno> alumnos = (List<Alumno>) alumnoDao.findAll();

			if (alumnos.size() == 0) {
				alumnoVo.setEstado(0);
				alumnoVo.setMensaje("No se detectan Alumnos en la base de datos");
				alumnoVo.setAlumnos(alumnos);
			} else {
				alumnoVo.setEstado(1);
				alumnoVo.setMensaje("Alumnos encontrados, cantidad de alumnos: " + alumnos.size());
				alumnoVo.setAlumnos(alumnos);
			}

		} catch (Exception e) {
			alumnoVo.setEstado(100);
			alumnoVo.setMensaje("Error de exeption (Metodo: findAll): " + e.getMessage());
			alumnoVo.setAlumnos(new ArrayList<Alumno>());
		}
		return alumnoVo;
	}

	@Override
	public AlumnoVO findById(Integer id) {
		AlumnoVO alumnoVo = new AlumnoVO();

		try {

			Alumno alumno = alumnoDao.findById(id).orElse(null);
			if (alumno == null) {
				alumnoVo.setEstado(0);
				alumnoVo.setMensaje("No se encontro el alumno id: " + id);
				alumnoVo.setAlumnos(new ArrayList<Alumno>());
			} else {
				List<Alumno> alumnos = new ArrayList<>();
				alumnos.add(alumno);
				alumnoVo.setEstado(1);
				alumnoVo.setMensaje("Alumno Encontrado con id: " + id);
				alumnoVo.setAlumnos(alumnos);
			}
		} catch (Exception e) {
			alumnoVo.setEstado(100);
			alumnoVo.setMensaje("Error de exeption (Metodo: findById): " + e.getMessage());
			alumnoVo.setAlumnos(new ArrayList<Alumno>());
		}

		return alumnoVo;
	}

	@Transactional
	@Override
	public AlumnoVO save(Alumno alumno) {
		AlumnoVO alumnoVo = new AlumnoVO();
		try {
			Alumno alumnoInsertado = alumnoDao.save(alumno);

			if (alumnoInsertado == null) {
				alumnoVo.setEstado(0);
				alumnoVo.setMensaje("No se pudo guardar el alumno: " + alumno.getNombre());
			} else {
				alumnoVo.setEstado(1);
				alumnoVo.setMensaje("Alumno ingresado con éxito: " + alumno.getNombre());
			}

		} catch (Exception e) {
			alumnoVo.setEstado(100);
			alumnoVo.setMensaje("Error de exeption (Metodo: save): " + e.getMessage());
		}

		return alumnoVo;
	}

	@Transactional
	@Override
	public AlumnoVO update(Alumno alumno) {
		AlumnoVO alumnoVo = new AlumnoVO();
		try {
			Alumno alumnoInsertado = alumnoDao.save(alumno);

			if (alumnoInsertado == null) {
				alumnoVo.setEstado(0);
				alumnoVo.setMensaje("No se puedo actualizar el alumno: " + alumno.getNombre());
			} else {
				alumnoVo.setEstado(1);
				alumnoVo.setMensaje("Alumno actualizado con éxito: " + alumno.getNombre());
			}

		} catch (Exception e) {
			alumnoVo.setEstado(100);
			alumnoVo.setMensaje("Error de exeption (Metodo: update): " + e.getMessage());
		}

		return alumnoVo;
	}

	@Transactional
	@Override
	public AlumnoVO deleteById(Integer id) {
		AlumnoVO alumnoVo = new AlumnoVO();

		try {
			Alumno alumnoEncontrado = alumnoDao.findById(id).orElse(null);
				alumnoEncontrado.setCurso(null);
				alumnoDao.save(alumnoEncontrado);
				
				alumnoDao.deleteById(id);
			

			if (alumnoDao.existsById(id)) {
				alumnoVo.setEstado(0);
				alumnoVo.setMensaje(String.format("No se pudo eliminar el alumno: %d", id));
				System.out.println(id);

			} else {

				alumnoVo.setEstado(1);
				alumnoVo.setMensaje("Alumnourso Eliminado con éxito: ");
			}

		} catch (Exception e) {
			alumnoVo.setEstado(100);
			alumnoVo.setMensaje("Error de exeption (Metodo: DeleteById): " + e.getMessage());
		}

		return alumnoVo;
	}

}
