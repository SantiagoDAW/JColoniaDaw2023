package jcolonia.daw2023.dosventanas;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class Lanzador {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(() -> {
			try {
				ControladorTicket control;

				control = new ControladorTicket();
				control.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
