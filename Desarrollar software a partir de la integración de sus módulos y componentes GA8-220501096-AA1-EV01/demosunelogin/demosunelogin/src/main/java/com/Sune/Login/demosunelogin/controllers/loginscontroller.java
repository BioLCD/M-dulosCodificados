package com.Sune.Login.demosunelogin.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Sune.Login.demosunelogin.models.login;
import com.Sune.Login.demosunelogin.models.loginA;
import com.Sune.Login.demosunelogin.services.loginsrepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("logins")
public class loginscontroller {

	@Autowired
	private loginsrepository repo;
	
	@GetMapping({ "", "/" })
	public String mostrarListalogins(Model model) {
		List<login> logins = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("logins", logins);

		return "logins/index";
	}
	
	@GetMapping("/crear")
	public String showCreatePage(Model model) {
		loginA loginA = new loginA();
		model.addAttribute("loginA", loginA);

		return "logins/crearlogin";
	}
	
	@PostMapping("/crear")
	public String crearlogin(@Valid @ModelAttribute loginA loginA, BindingResult resultado) {

		if (loginA.getArchivoImagen().isEmpty()) {
			resultado.addError(
					new FieldError("loginA", "archivoImagen", "El archivo para la imagen es obligatorio"));
		}

		if (resultado.hasErrors()) {
			return "logins/crearlogin";
		}

		// Grabar Archivo de imagen
		MultipartFile image = loginA.getArchivoImagen();

		// Date fechaCreacion = new java.sql.Date(new java.util.Date().getTime());
		Date fechaCreacion = new Date(System.currentTimeMillis());
		
		String storageFileName = fechaCreacion.getTime() + "_" + image.getOriginalFilename();

		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = image.getInputStream()) {
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (Exception ex) {
			System.out.println("Exepción al Grabar la imagen: " + ex.getMessage());
		}
		// Registro en base de datos del nuevo registro.
		login prod = new login();
		prod.setNombres(loginA.getNombres());
		prod.setApellidos(loginA.getApellidos());
		prod.setTipousuario(loginA.getTipousuario());
		prod.setCorreo(loginA.getCorreo());
		prod.setCcoce(loginA.getCcoce());
		prod.setCelular(loginA.getCelular());
		prod.setEdad(loginA.getEdad());
		prod.setDescripcion(loginA.getDescripcion());
		prod.setFechaCreacion((java.sql.Date) fechaCreacion);
		prod.setNombreArchivoImagen(storageFileName);
				
		repo.save(prod);

		return "redirect:/logins";
	}
	
	@GetMapping("/editar")
	public String showEditPage(Model model, @RequestParam int id) {
		try {
			login prod = repo.findById(id).get();
			model.addAttribute("login",prod);
			
			loginA loginA = new loginA();
			loginA.setNombres(prod.getNombres());
			loginA.setApellidos(prod.getApellidos());
			loginA.setTipousuario(prod.getTipousuario());
			loginA.setCorreo(prod.getCorreo());
			loginA.setCcoce(prod.getCcoce());
			loginA.setCelular(prod.getCelular());
			loginA.setEdad(prod.getEdad());
			loginA.setDescripcion(prod.getDescripcion());
			
			model.addAttribute("loginA", loginA);
			
		}catch(Exception ex) {
			System.out.println("Excepión al Editar: " + ex.getMessage());
			return "redirect:/logins";
		}		
		return "/logins/editarlogin";
	}
	
	@PostMapping("/editar")
	public String actualizarlogin(Model model, @RequestParam int id,
			@Valid @ModelAttribute loginA loginA,
			BindingResult resultado) {
		try {
			login login = repo.findById(id).get();
			model.addAttribute("login", login);
			
			//Si hay errores
			if(resultado.hasErrors()) {
				return "logins/editarlogin";
			}
			
			if(!loginA.getArchivoImagen().isEmpty()) {
				//Eliminamos la imagen antigua
				String dirDeImagenes="public/images/";
				Path rutaAntiguaImagen = Paths.get(dirDeImagenes + login.getNombreArchivoImagen());
				try {
					Files.delete(rutaAntiguaImagen);
				}catch(Exception ex) {
					System.out.println("Excepción: " + ex.getMessage());
				}
				
				//Grabar el archivo de la nueva imagen
				MultipartFile image = loginA.getArchivoImagen();
				
				Date fechaCreacion = new Date(System.currentTimeMillis());
				String storageFileName = fechaCreacion.getTime() + "_" + image.getOriginalFilename();
				
				try(InputStream inputStream = image.getInputStream()) {
					Files.copy(inputStream, Paths.get(dirDeImagenes + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
				}				
				login.setNombreArchivoImagen(storageFileName);				
			}
			
			login.setNombres(loginA.getNombres());
			login.setApellidos(loginA.getApellidos());
			login.setTipousuario(loginA.getTipousuario());
			login.setCorreo(loginA.getCorreo());
			login.setCcoce(loginA.getCcoce());
			login.setCelular(loginA.getCelular());
			login.setEdad(loginA.getEdad());
			login.setDescripcion(loginA.getDescripcion());
			
			repo.save(login);
			
		}catch(Exception ex) {
			System.out.println("Excepción al grabar la edicón: " + ex.getMessage());
		}	
		
		return "redirect:/logins";
	}
	
	@GetMapping("/eliminar")
	public String eliminarlogin(@RequestParam int id) {
		
		try {
			
			login login = repo.findById(id).get();
			//Eliminamos la imagen del login
			Path rutaIamagen = Paths.get("public/images" + login.getNombreArchivoImagen());
			
			try {
				Files.delete(rutaIamagen);
			}catch(Exception ex) {
				System.out.println("Excepción al Eliminar: " + ex.getMessage());
			}
			
			//Eliminar el login de la base datos
			repo.delete(login);
			
		}catch(Exception ex) {
			System.out.println("Excepción al Eliminar: " + ex.getMessage());
		}		
		return "redirect:/logins";
	}

	
}
