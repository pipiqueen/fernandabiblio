package biblio;

import java.io.Serializable;

public class Libro implements Serializable {

	private String nombre;
	private String autor;
	private String editorial;
	private int prestado;
	
	
	
	public Libro (String nombre, String autor, String editorial, int prestado) {
		this.nombre = nombre;
		this.autor = autor;
		this.editorial = editorial;
		this.prestado= prestado;
		
		
	}


	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial + ", prestado=" + prestado
				;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public int getPrestado() {
		return prestado;
	}


	public void setPrestado(int prestado) {
		this.prestado = prestado;
	}


}
