package FabricaPiezas;

import java.util.Arrays;

public class Cliente {

	private String codigo;
	private String direccion;
	private String nombre;
	private String correo;
	private FormaDePago fp;
	private Solicitud[] solicitudes;
	
	public Cliente(String codigo, String direccion, String nombre, String correo, FormaDePago fp) {
		this.codigo = codigo;
		this.direccion = direccion;
		this.nombre = nombre;
		this.correo = correo;
		this.fp = fp;
		this.solicitudes = new Solicitud[0];
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public FormaDePago getFp() {
		return fp;
	}
	
	public Solicitud[] getSolicitudes() {
		return solicitudes;
	}
	
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void addSolicitud(String codigo, String codigoPieza, int cantidadFabricar) {
		
		Solicitud s = new Solicitud(codigo, codigoPieza, cantidadFabricar);
		
		this.solicitudes = Arrays.copyOf(this.solicitudes, this.solicitudes.length+1);
		this.solicitudes[this.solicitudes.length-1] = s;
	}
	
	public void registrarEntrega(String codigo) {
		int index = searchSolicitud(codigo);
		
		if(index != -1) {
			
			solicitudes[index].setCumplida(true);
			System.out.println("Solicitud cumplida");
			
		}else {System.out.println("La solicitud no existe");}
	}
	
	
	public int searchSolicitud(String codigo) {
		int index = 0;
		
		while(index < solicitudes.length && !solicitudes[index].getCodigo().equals(codigo)) {
			index++;
		}
		
		return (index < solicitudes.length)? index: -1;
	}
	
	public void modSolicitud(String codigoV, String codigoN, String codigoPieza, int cantidadFabricar) {
		int index = searchSolicitud(codigoV);
		
		if(index != -1) {
			
			if(!codigoN.equals("")) {solicitudes[index].setCodigo(codigoN);}
			if(!codigoPieza.equals("")) {solicitudes[index].setCodigoPieza(codigoPieza);}
			if(cantidadFabricar > 0) {solicitudes[index].setCantidadFabricar(cantidadFabricar);}
	
			
		}else {
			System.out.println("No existe una solicitud con ese código");
		}
				
	}
	
	public void delSolicitud(String codigo) {
		int index = searchSolicitud(codigo);
		
		if(index !=-1) {	//Si sí existe
			if(index == 0) {// Si está en la primera posición
				Solicitud[] aux = new Solicitud[this.solicitudes.length-1];
				
				System.arraycopy(this.solicitudes, 1, aux, 0, aux.length);//aux es arreglo excepto el primero
				this.solicitudes = Arrays.copyOf(aux, aux.length);	//se convierte en aux
				
			}else if (index == solicitudes.length-1) {	//Si está en la última posición
				
				this.solicitudes = Arrays.copyOf(this.solicitudes, this.solicitudes.length-1);
				///Es sí mismo menos la úlitma posición
				
			}else {// Si está entre la primera y última posicíon
				
				Solicitud[] aux1 = new Solicitud[index]; //aux1 con posiciones igual al índice
				Solicitud[] aux2 = new Solicitud[this.solicitudes.length-index-1];
				//aux2 con posiciones de arreglo atributo menos el ínidce y menos 1 posición
				//total de posiciones = arregloAtributo.length-1
				
				System.arraycopy(this.solicitudes, 0, aux1, 0, aux1.length);
				//aux1 son todos los elementos del arreglo hasta el que se quiere eliminar (sin incluir)
				System.arraycopy(this.solicitudes, index+1, aux2, 0, aux2.length);
				//aux2 son todos los autores del arreglo desde el que se quiere eliminar (sin incluir)
				
				this.solicitudes = Arrays.copyOf(this.solicitudes, this.solicitudes.length-1); //Quitar una posición
				
				System.arraycopy(aux1, 0, this.solicitudes, 0, aux1.length);
				//el arreglo atributo toma los valores de aux1 desde 0
				System.arraycopy(aux2, 0, this.solicitudes, index, aux2.length);
				//el arreglo atributo toma los valores de aux2 desde el indice del elemento que se quería eliminar
			}

		}else {
			System.out.println("La solicitud no existe");
		}
	}
	
	
	
	
	
}
