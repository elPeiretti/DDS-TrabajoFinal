package dominio;

import java.time.Instant;

public class Pasajero {
	private String nombre;
	private String apellido;
	private String cuit;
	private String nroDocumento;
	private Instant fechaDeNacimiento;
	private String nacionalidad; //es String en diagrama de clases pero la interfaz esta hecha con ComboBox. Podria usarse Pais pero en el diseño de entradas el ejemplo es "Argentino/a"
	private String email;
	private String telefono;
	private String ocupacion;
	private TipoDocumento tipoDocumento;
	private PosicionIVA posicionIVA;
	private Direccion direccion;
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getCuit() {
		return cuit;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public Instant getNacimiento() {
		return fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public PosicionIVA getPosicionIVA() {
		return posicionIVA;
	}
	public Direccion getDireccion() {
		return direccion;
	}
}
