package FabricaPiezas;
import java.util.Arrays;

public class Empresa {

	private Pieza piezas[];
	private String nombre;
	private String direccion;
	private Cliente clientes[];
	
	public Empresa(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.piezas = new Pieza[0];
		this.clientes = new Cliente[0];
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Pieza[] getPiezas() {
		return piezas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public Cliente[] getClientes() {
		return clientes;
	}
	
	
	public void addPieza(char tipo, double pesoU, String codigo, String descripcion) {
		this.piezas = Arrays.copyOf(this.piezas, this.piezas.length+1);
		
		switch(tipo) {
			case 'm':
				Metalica m = new Metalica(pesoU, codigo, descripcion);
				this.piezas[this.piezas.length-1] = m;
				
				break;
			
			case 'p':
				Plastica p = new Plastica(pesoU, codigo, descripcion);
				this.piezas[this.piezas.length-1] = p;
				break;
				
			case 'i':
				System.out.println("No indicó las piezas que componen la pieza mixta");
				break;
				
			default:
				System.out.println("No existe el tipo de pieza");

		}
	}
	
	public void addPieza(char tipo, double pesoU, String codigo, String descripcion, String[] Cpiezas) {
		
		if(tipo == 'i') {
			Pieza p[] = new Pieza[0];
			
			for(String s: Cpiezas) {
				int index = searchPieza(s);
				if(index != -1) {
					p = Arrays.copyOf(p, p.length+1);
					p[p.length-1] = this.piezas[index];
				}
			}
			
			this.piezas = Arrays.copyOf(this.piezas, this.piezas.length+1);
			Mixta m = new Mixta(pesoU, codigo, descripcion, p);
			this.piezas[this.piezas.length-1] = m;
			
		}else {
			System.out.println("No existe el tipo de pieza");

		}
	}
	
	public void addCliente(String codigo, String direccion, String nombre, String correo, FormaDePago fp) {
		
		clientes = Arrays.copyOf(clientes, clientes.length+1);
		Cliente c = new Cliente(codigo, direccion, nombre, correo, fp);
		clientes[clientes.length-1] = c;
				
	}
	
	public void crearSolicitud(String codigoC, String codigoS, String codigoPieza, int cantidadFabricar) {
		int indexC = searchCliente(codigoC);
		int indexP = searchPieza(codigoPieza);
		
		if(indexC != -1 && indexP != -1 && cantidadFabricar > 0) {
			clientes[indexC].addSolicitud(codigoS, codigoPieza, cantidadFabricar);
			
		}else {System.out.println("Error");}
		
	}
	
	
	public int searchPieza(String codigo) {
		int index = 0;
		while(index < piezas.length && !piezas[index].getCodigo().equals(codigo)) {
			index++;
		}
		
		return(index < piezas.length)? index: -1;
	}
	
	public int searchCliente(String codigo) {
		int index = 0;
		while(index < clientes.length && !clientes[index].getCodigo().equals(codigo)) {
			index++;
		}
		
		return(index < clientes.length)? index: -1;
	}
	
	public int[] searchSolicitud(String codigo) {
		int[] index = new int[2];
		int indexC = 0;
		boolean flag = true;
		while(indexC < clientes.length && flag) {
			if(clientes[indexC].searchSolicitud(codigo) != -1) {
				flag = false;
				index[0] = indexC;
				index[1] = clientes[indexC].searchSolicitud(codigo);
			}
			indexC++;
		}
		
		if(indexC >= clientes.length && flag) {
			index[0] = -1;
			index[1] = -1;
		}
		
		return index;

	}
	
	public void delSolicitud(String codigo) {
		
		int index[] = searchSolicitud(codigo);
		clientes[index[0]].delSolicitud(codigo);
		
	}
	
	public void delPieza(String codigo) {
		int index = searchPieza(codigo);
		
		if(index !=-1) {	//Si sí existe
			if(index == 0) {// Si está en la primera posición
				Pieza[] aux = new Pieza[this.piezas.length-1];
				
				System.arraycopy(this.piezas, 1, aux, 0, aux.length);//aux es arreglo excepto el primero
				this.piezas = Arrays.copyOf(aux, aux.length);	//se convierte en aux
				
			}else if (index == piezas.length-1) {	//Si está en la última posición
				
				this.piezas = Arrays.copyOf(this.piezas, this.piezas.length-1);
				///Es sí mismo menos la úlitma posición
				
			}else {// Si está entre la primera y última posicíon
				
				Pieza[] aux1 = new Pieza[index]; //aux1 con posiciones igual al índice
				Pieza[] aux2 = new Pieza[this.piezas.length-index-1];
				//aux2 con posiciones de arreglo atributo menos el ínidce y menos 1 posición
				//total de posiciones = arregloAtributo.length-1
				
				System.arraycopy(this.piezas, 0, aux1, 0, aux1.length);
				//aux1 son todos los elementos del arreglo hasta el que se quiere eliminar (sin incluir)
				System.arraycopy(this.piezas, index+1, aux2, 0, aux2.length);
				//aux2 son todos los autores del arreglo desde el que se quiere eliminar (sin incluir)
				
				this.piezas = Arrays.copyOf(this.piezas, this.piezas.length-1); //Quitar una posición
				
				System.arraycopy(aux1, 0, this.piezas, 0, aux1.length);
				//el arreglo atributo toma los valores de aux1 desde 0
				System.arraycopy(aux2, 0, this.piezas, index, aux2.length);
				//el arreglo atributo toma los valores de aux2 desde el indice del elemento que se quería eliminar
			}

		}else {
			System.out.println("La pieza no existe");
		}
	}
	
	public void delCliente(String codigo) {
		int index = searchCliente(codigo);
		
		if(index !=-1) {	//Si sí existe
			if(index == 0) {// Si está en la primera posición
				Cliente[] aux = new Cliente[this.clientes.length-1];
				
				System.arraycopy(this.clientes, 1, aux, 0, aux.length);//aux es arreglo excepto el primero
				this.clientes = Arrays.copyOf(aux, aux.length);	//se convierte en aux
				
			}else if (index == clientes.length-1) {	//Si está en la última posición
				
				this.clientes = Arrays.copyOf(this.clientes, this.clientes.length-1);
				///Es sí mismo menos la úlitma posición
				
			}else {// Si está entre la primera y última posicíon
				
				Cliente[] aux1 = new Cliente[index]; //aux1 con posiciones igual al índice
				Cliente[] aux2 = new Cliente[this.clientes.length-index-1];
				//aux2 con posiciones de arreglo atributo menos el ínidce y menos 1 posición
				//total de posiciones = arregloAtributo.length-1
				
				System.arraycopy(this.clientes, 0, aux1, 0, aux1.length);
				//aux1 son todos los elementos del arreglo hasta el que se quiere eliminar (sin incluir)
				System.arraycopy(this.clientes, index+1, aux2, 0, aux2.length);
				//aux2 son todos los autores del arreglo desde el que se quiere eliminar (sin incluir)
				
				this.clientes = Arrays.copyOf(this.clientes, this.clientes.length-1); //Quitar una posición
				
				System.arraycopy(aux1, 0, this.clientes, 0, aux1.length);
				//el arreglo atributo toma los valores de aux1 desde 0
				System.arraycopy(aux2, 0, this.clientes, index, aux2.length);
				//el arreglo atributo toma los valores de aux2 desde el indice del elemento que se quería eliminar
			}

		}else {
			System.out.println("El cliente no existe");
		}
	}
	
	public void modSolicitud(String codigoSV, String codigoSN, String codigoP, int cantidadFabricar) {
		int index[] = searchSolicitud(codigoSV);
		if(index[0] !=-1) {
			clientes[index[0]].modSolicitud(codigoSV, codigoSN, codigoP, cantidadFabricar);
		}else {
			System.out.println("El cliente o la solicitud no existe");

		}
	}
	
	public void registrarEntrega(String codigo) {
		int index[] = searchSolicitud(codigo);
		if(index[0] != -1) {
			clientes[index[0]].registrarEntrega(codigo);
		}
	}

	public double getCostoPieza(String codigo) {
		int index = searchPieza(codigo);
		
		return (index != -1)? piezas[index].calcularCosto():-1;
	}
	
	public double getCostoCliente(String codigo) {
		int index = searchCliente(codigo);
		double costo = 0;
		
		if(index != -1 && clientes[index].getSolicitudes().length != 0) {
			
			for(Solicitud s:clientes[index].getSolicitudes()) {
				int indexP = searchPieza(s.getCodigoPieza());
				costo += s.getCantidadFabricar() * this.piezas[indexP].calcularCosto();
			}
			
		}else {costo = -1;}
		
		return costo;
	}
	
	public Cliente getVIP() {
		double max = -1;
		double aux = 0;
		Cliente vip[] = new Cliente[1];
		
		for(Cliente c: clientes) {
			aux = getCostoCliente(c.getCodigo());
			if(aux > max) {
				max = aux;
				vip[0] = c;
			}
		}
		
		return vip[0];
		
		
	}
	
	
}
