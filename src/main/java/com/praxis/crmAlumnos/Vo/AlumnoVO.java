package com.praxis.crmAlumnos.Vo;

import java.util.List;

import com.praxis.crmAlumnos.model.Alumno;

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
public class AlumnoVO {

	private Integer estado;
	private String mensaje;
	private List<Alumno> alumnos;
	
	public AlumnoVO(Integer estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	
}
