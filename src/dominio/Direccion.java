package dominio;


/*
 * Hay problemas en el diagrama de clases, algunos campos no coinciden con el diseño de entradas
 */
public class Direccion {
	private Integer codigoPostal; //es Integer pero mi codigo postal es S3002ALE?
	private String calle;
	private Integer nroCalle;
	private Integer piso;
	private Integer nroDepartamento; //es Integer pero yo vivo en el dpto "A"
	private Ciudad ciudad;
	public Integer getCodigoPostal() {
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
	public Integer getNroDepartamento() {
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
