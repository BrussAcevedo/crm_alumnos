package com.praxis.crmAlumnos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "alumnos")
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstudiante;
	private String rut;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_curso")
	private Curso curso;

	public Alumno(String rut, String nombre, String apellido, String correo, String telefono, Curso curso) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Alumno [idEstudiante=" + idEstudiante + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", correo=" + correo + ", telefono=" + telefono + " ,curso=" + curso.getNombre() + "]";
	}

}
