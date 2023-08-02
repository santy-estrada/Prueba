package FabricaPiezas;

public enum FormaDePago {

	CO("Cheque Ordinario"), CE("Cheque de Empresa"), CC("Cheque Certificado"), T("Transacci�n Bancaria en L�nea");

	private String tipo;
	
	private FormaDePago(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

}
