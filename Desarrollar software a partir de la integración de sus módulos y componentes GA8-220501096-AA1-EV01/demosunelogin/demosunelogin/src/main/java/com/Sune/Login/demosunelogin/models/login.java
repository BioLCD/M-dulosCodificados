package com.Sune.Login.demosunelogin.models;

import java.util.Date;
import jakarta.persistence.*;


@Entity
@Table(name="logins")
public class login {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nombres;
	private String apellidos;
	private String tipousuario;
	private String correo;
	private double ccoce;
	private double celular;
	private double edad;
	
	@Column(columnDefinition = "Text")
	private String descripcion;
	
	private Date fechaCreacion;
	private String nombreArchivoImagen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getNombreArchivoImagen() {
		return nombreArchivoImagen;
	}
	public void setNombreArchivoImagen(String nombreArchivoImagen) {
		this.nombreArchivoImagen = nombreArchivoImagen;
	}
	
	
}
