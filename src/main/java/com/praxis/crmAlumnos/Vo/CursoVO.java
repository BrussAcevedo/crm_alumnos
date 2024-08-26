package com.praxis.crmAlumnos.Vo;

import java.util.List;

import com.praxis.crmAlumnos.model.Curso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CursoVO {
	private Integer estado;
	private String mensaje;
	private List<Curso> cursos;
	
	public CursoVO(Integer estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	
}
