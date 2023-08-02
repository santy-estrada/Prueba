package FabricaPiezas;

public class MainFabricaPiezas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Metalica m = new Metalica(17.2,"hjk", "demetal");
		System.out.println(m.calcularCosto());
		
		Plastica p = new Plastica(17.2,"abc", "deplastico");
		System.out.println(p.calcularCosto());
		
		Pieza piezas[] = {p,m};
		
		Mixta mi = new Mixta(17.2,"def", "detodo", piezas);
		System.out.println(mi.calcularCosto());
		System.out.println("-----------------------------");
		
		FormaDePago  co = FormaDePago .valueOf("CO");
		FormaDePago  ce = FormaDePago .valueOf("CE");
		FormaDePago  cc = FormaDePago .valueOf("CC");
		FormaDePago  t = FormaDePago .valueOf("T");
		
		Cliente c1 = new Cliente("pqk", "cll 36", "carros sas", "carros@hotmail.com", co);
		Cliente c2 = new Cliente("123", "cll 50", "motos sas", "motos.com", t);

		c1.addSolicitud("1", "hjk", 5);
		c1.addSolicitud("2.1", "roc", 100);
		c1.addSolicitud("2", "aba", 14);
		c2.addSolicitud("3", "def", 74);
		
		for(Solicitud s: c1.getSolicitudes()) {
			System.out.println(s.getCodigoPieza());
			System.out.println(s.getCantidadFabricar());
		}
		System.out.println();
		
		c1.modSolicitud("2", "", "abc", 15);
		c1.delSolicitud("2.1");
		
		for(Solicitud s: c1.getSolicitudes()) {
			System.out.println(s.getCodigoPieza());
			System.out.println(s.getCantidadFabricar());

		}
		System.out.println();
		*/
		
		Empresa e = new Empresa("Empresa sas", "calle 19");
		
		e.addPieza('m', 17.2, "hjk", "demetal");
		e.addPieza('p', 17.2, "abc", "deplastico");
		
		String piezas[] = {"abc","hjk"};
		e.addPieza('i', 17.2, "pqr", "detodo", piezas);
		
		e.addCliente("pqk", "cll 36", "carros sas", "carros@hotmail.com", FormaDePago.valueOf("CO"));
		e.addCliente("123", "cll 50", "motos sas", "motos.com", FormaDePago.valueOf("T"));
		
		System.out.println(e.getCostoPieza("hjk"));
		System.out.println(e.getCostoPieza("abc"));
		System.out.println(e.getCostoPieza("pqr"));
		System.out.println();
		
		e.crearSolicitud("123", "##1", "pqr", 10);
		System.out.println(e.getCostoCliente("123"));

		e.crearSolicitud("123", "##2", "abc", 1);
		
		System.out.println(e.getCostoCliente("123"));
		e.crearSolicitud("123", "##4", "abc", -1);
		
		System.out.println(e.getCostoCliente("123"));
		
		e.crearSolicitud("pqk", "##3", "hjk", 12);
		System.out.println(e.getCostoCliente("pqk"));

		System.out.println();

		System.out.println("VIP: " + e.getVIP().getCodigo());

		e.modSolicitud("##2","" , "", 3);
		System.out.println(e.getCostoCliente("123"));
		System.out.println();

		e.registrarEntrega("##2");
		
		e.delCliente("123");
		System.out.println("VIP: " + e.getVIP().getCodigo());
		
		e.delPieza("hjk");
		
		e.crearSolicitud("pqk", "##5", "hjk", 5);

		
	}
	
	

}
