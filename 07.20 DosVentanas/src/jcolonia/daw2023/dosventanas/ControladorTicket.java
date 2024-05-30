package jcolonia.daw2023.dosventanas;

import java.util.Objects;

public class ControladorTicket {
	private static final String TEXTO_CRÉDITOS = "Créditos";
	
	private VentanaUno frame1;
	private VentanaDos frame2;

	private TicketTurno ticket;

	public ControladorTicket() {
		ticket = new TicketTurno("Mariscos Recio");
	}

	public VentanaUno getFrame1() {
		if (Objects.isNull(frame1)) {
			frame1 = new VentanaUno(this, ticket.getDescripción());
		}
		return frame1;
	}

	public VentanaDos getFrame2() {
		if (Objects.isNull(frame2)) {
			frame2 = new VentanaDos(this, ticket.getDescripción(), getFrame1());
		}
		return frame2;
	}

	public void ejecutar() {
		getFrame1();
		getFrame2();

		frame2.setVisible(true);
		frame1.setVisible(true);
		frame1.toFront();
	}
	
	public void mostrarCréditos() {
		VentanaDos ventana=getFrame2();
		ventana.mostrarTexto(TEXTO_CRÉDITOS);
	}
}
