package FabricaPiezas;

import java.util.Date;

public class Solicitud {
	private String codigo;
	private String codigoPieza;
	private Date fechaSolicitud;
	private int cantidadFabricar;
	private boolean cumplida;
	
	public Solicitud(String codigo, String codigoPieza, int cantidadFabricar) {
		this.codigo = codigo;
		this.cantidadFabricar = cantidadFabricar;
		this.codigoPieza = codigoPieza;
		this.cumplida = false;
		this.fechaSolicitud = new Date();
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getCodigoPieza() {
		return codigoPieza;
	}
	
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public int getCantidadFabricar() {
		return cantidadFabricar;
	}
	
	public boolean getCumplida() {
		return cumplida;
	}
	


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public  void setCodigoPieza(String codigoPieza) {
		this.codigoPieza = codigoPieza;
	}
	
	
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public void setCantidadFabricar(int cantidadFabricar) {
		this.cantidadFabricar = cantidadFabricar;
	}
	
	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}	

	
	
}
