package com.Sune.Login.demosunelogin.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;

public class loginA {
	
	@NotEmpty(message = "El o los nombres son obligatorios")
	private String nombres;
	
	@NotEmpty(message = "El o los apellidos son obligatorios")
	private String apellidos;
	
	@NotEmpty(message = "El tipo de usuario es obligatorio")
	private String tipousuario;
	
	@NotEmpty(message = "El correo es obligatorio")
	private String correo;
	
	@Min(10)
	private double ccoce;
	
	@Min(10)
	private double celular;
	
	@Min(0)
	private double edad;
	
	@Size(min=5, message = "La descripción debe tener mas de 5 caracteres")
	@Size(max=20, message = "La descripción no debe exceder los 20 caracteres")
	private String descripcion;
	
	private MultipartFile archivoImagen;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public double getCcoce() {
		return ccoce;
	}

	public void setCcoce(double ccoce) {
		this.ccoce = ccoce;
	}

	public double getCelular() {
		return celular;
	}

	public void setCelular(double celular) {
		this.celular = celular;
	}

	public double getEdad() {
		return edad;
	}

	public void setEdad(double edad) {
		this.edad = edad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MultipartFile getArchivoImagen() {
		return archivoImagen;
	}

	public void setArchivoImagen(MultipartFile archivoImagen) {
		this.archivoImagen = archivoImagen;
	}


	
}
