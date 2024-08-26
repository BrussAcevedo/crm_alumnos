package com.praxis.crmAlumnos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.praxis.crmAlumnos.Vo.AlumnoVO;
import com.praxis.crmAlumnos.Vo.CursoVO;
import com.praxis.crmAlumnos.dao.objetoPers.CursoIdNombre;
import com.praxis.crmAlumnos.model.Alumno;

import com.praxis.crmAlumnos.service.ServicioAlumnoImp;

import com.praxis.crmAlumnos.service.ServicioCursoImp;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlumnoControlador {

	@Autowired
	ServicioAlumnoImp servicioAlumno;

	@Autowired
	ServicioCursoImp servicioCurso;

	@GetMapping("/alumnos")
	public ModelAndView listadoAlumnos() {
		ModelAndView mav = new ModelAndView("alumnos");
		List<Alumno> alumnos = new ArrayList<>();
		AlumnoVO alumnoVo = servicioAlumno.findAll();

		if (alumnoVo.getEstado().equals(1)) {
			alumnos = alumnoVo.getAlumnos();
			mav.addObject("listaAlumnos", alumnos);

		} else {
			String mensajeError = alumnoVo.getMensaje().concat("Codigo Error: " + alumnoVo.getEstado());
			mav.addObject("mensajeError", mensajeError);
		}

		return mav;
	}

	@GetMapping("/eliminarAlumno")
	public String eliminarAlumno(@RequestParam String idAlumnoTxt) {
		AlumnoVO alumnoVo = servicioAlumno.deleteById(Integer.parseInt(idAlumnoTxt));

		System.out.println(alumnoVo.getMensaje());

		return "redirect:/alumnos";
	}

	@GetMapping("/actualizarAlumno")
	public String actualizarAlumno(@RequestParam String idAlumnoTxt, Model model) {
		AlumnoVO alumnoVo = servicioAlumno.findById(Integer.parseInt(idAlumnoTxt));

		List<CursoIdNombre> nombreCursos = servicioCurso.findIdandName();
		Alumno alumno = alumnoVo.getAlumnos().get(0);

		model.addAttribute("alumno", alumno);
		model.addAttribute("cursos", nombreCursos);

		return "actualizarAlumno";
	}

	@PostMapping("/alumnoActualizado")
	public String alumnoActualizado(@RequestParam String idAlumnoTxt, @RequestParam String nombreTxt,
			@RequestParam String apellidoTxt, @RequestParam String correoTxt, @RequestParam String rutTxt,
			@RequestParam String telefonoTxt, @RequestParam String cursoTxt) {

		CursoVO cursoEncontrado = servicioCurso.findById(Integer.parseInt(cursoTxt));
		Alumno alumno = new Alumno(Integer.parseInt(idAlumnoTxt), rutTxt, nombreTxt, apellidoTxt, correoTxt,
				telefonoTxt, cursoEncontrado.getCursos().get(0));

		servicioAlumno.update(alumno);

		return "redirect:/alumnos";
	}

	@GetMapping("/agregarAlumno")
	public String agregarAlumnoVista(Model model) {

		List<CursoIdNombre> nombreCursos = servicioCurso.findIdandName();
		System.out.println(nombreCursos);
		model.addAttribute("cursos", nombreCursos);

		return "nuevoAlumno";
	}

	@PostMapping("/alumnoAgregado")
	public String alumnoAgregado(@RequestParam String nombreTxt, @RequestParam String apellidoTxt,
			@RequestParam String correoTxt, @RequestParam String rutTxt, @RequestParam String telefonoTxt,
			@RequestParam String cursoTxt) {

		CursoVO cursoEncontrado = servicioCurso.findById(Integer.parseInt(cursoTxt));
		Alumno alumno = new Alumno(0, rutTxt, nombreTxt, apellidoTxt, correoTxt, telefonoTxt,
				cursoEncontrado.getCursos().get(0));

		servicioAlumno.save(alumno);

		return "redirect:/alumnos";
	}

}
