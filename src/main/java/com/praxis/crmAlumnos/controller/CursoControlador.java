package com.praxis.crmAlumnos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.praxis.crmAlumnos.Vo.CursoVO;
import com.praxis.crmAlumnos.model.Curso;
import com.praxis.crmAlumnos.service.ServicioCursoImp;

@Controller
public class CursoControlador {

	@Autowired
	ServicioCursoImp servicioCurso;

	@GetMapping("/cursos")
	public ModelAndView listadoCursos() {
		ModelAndView mav = new ModelAndView("cursos");
		List<Curso> cursos = new ArrayList<>();
		CursoVO cursoVo = servicioCurso.findAll();

		if (cursoVo.getEstado().equals(1)) {

			cursos = cursoVo.getCursos();
			mav.addObject("listaCursos", cursos);

		} else {
			String mensajeError = cursoVo.getMensaje().concat("Codigo Error: " + cursoVo.getEstado());
			mav.addObject("mensajeError", mensajeError);
		}

		return mav;
	}

	@GetMapping("/eliminarCurso")
	public String eliminarCurso(@RequestParam String idCursoTxt) {
		CursoVO cursoVo = servicioCurso.deleteById(Integer.parseInt(idCursoTxt));

		System.out.println(cursoVo.getMensaje());

		return "redirect:/cursos";
	}

	@GetMapping("/actualizarCurso")
	public String actualizarCurso(@RequestParam String idCursoTxt, Model model) {
		CursoVO cursoVo = servicioCurso.findById(Integer.parseInt(idCursoTxt));

		List<Curso> curso = cursoVo.getCursos();
		model.addAttribute("curso", curso.get(0));

		return "actualizarCurso";
	}

	@PostMapping("/cursoActualizado")
	public String cursoActualizado(@RequestParam String idCursoTxt, @RequestParam String nombreTxt,
			@RequestParam String descripcionTxt, @RequestParam String estudiantesTxt) {

		Curso curso = new Curso(Integer.parseInt(idCursoTxt), nombreTxt, descripcionTxt,
				Integer.parseInt(estudiantesTxt), null);

		servicioCurso.update(curso);

		return "redirect:/cursos";
	}

	@GetMapping("/agregarCurso")
	public String agregarCurso() {

		return "nuevoCurso";
	}

	@PostMapping("/cursoAgregado")
	public String cursoAgregado(@RequestParam String nombreTxt, @RequestParam String descripcionTxt) {
		Curso curso = new Curso(nombreTxt, descripcionTxt, 0, null);

		servicioCurso.save(curso);

		return "redirect:/alumnos";
	}

}
