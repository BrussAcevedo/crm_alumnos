package com.praxis.crmAlumnos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")

public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;
	private String nombre;
	private String descripcion;
	private Integer cantidadEstudiantes;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Alumno> alumnos;

	public Curso(String nombre, String descripcion, Integer cantidadEstudiantes, List<Alumno> alumnos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadEstudiantes = cantidadEstudiantes;
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", cantidadEstudiantes=" + cantidadEstudiantes + ", alumnos=" + alumnos.toString() + "]";
	}

}
