package dominio;


public class Direccion {
	private String codigoPostal;
	private String calle;
	private Integer nroCalle;
	private Integer piso;
	private String nroDepartamento;
	private Ciudad ciudad;
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public Integer getNroCalle() {
		return nroCalle;
	}
	public Integer getPiso() {
		return piso;
	}
	public String getNroDepartamento() {
		return nroDepartamento;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public Pais getPais() {
		return ciudad.getPais();
	}
	public Provincia getProvincia() {
		return ciudad.getProvincia();
	}
}
