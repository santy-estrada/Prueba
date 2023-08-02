package FabricaPiezas;

public class Mixta extends Pieza{
	
	private Pieza piezas[];
	public Mixta(double pesoU, String codigo, String descripcion, Pieza piezas[]) {
		super(pesoU, codigo, descripcion);
		// TODO Auto-generated constructor stub
		this.piezas=piezas;
	}
	
	public double calcularCosto() {
		double costo = 0;
		for(Pieza p: piezas) {
			costo += p.calcularCosto();
		}
		return costo;
	}

}
