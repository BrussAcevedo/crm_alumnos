package com.praxis.crmAlumnos.dao.objetoPers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CursoIdNombre {

	private Integer idCurso;
	private String nombre;
	private String descripcion;

	public CursoIdNombre(Integer idCurso, String nombre) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
	}

}
