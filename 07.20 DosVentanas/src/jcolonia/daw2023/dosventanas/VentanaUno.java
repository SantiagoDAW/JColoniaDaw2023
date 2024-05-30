package jcolonia.daw2023.dosventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaUno extends JFrame {
	private static final long serialVersionUID = 1L;

	private String título;
	private ControladorTicket control;

	private JPanel jpanelExterior;
	private JPanel jpanelCentral;
	private JMenuBar barraMenú;
	private JMenu menúAyuda;
	private JMenuItem opmenúAcercaDe;
	private JPanel jpanelEstado;
	private JLabel textoEstado;

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public VentanaUno(ControladorTicket control, String nombreComercio) {
		this.control = control;
		título = nombreComercio + " –1–";

		initialize();
	}

	private void initialize() {
		setTitle(título);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setJMenuBar(getMenuBar_1());
		jpanelExterior = new JPanel();
		jpanelExterior.setName("jpanelExterior");
		jpanelExterior.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().setLayout(new BorderLayout());
		setContentPane(jpanelExterior);
		jpanelExterior.setLayout(new BorderLayout(10, 10));
		jpanelExterior.add(getJpanelCentral(), BorderLayout.CENTER);
		jpanelExterior.add(getJpanelEstado(), BorderLayout.SOUTH);
	}

	private JPanel getJpanelCentral() {
		if (jpanelCentral == null) {
			jpanelCentral = new JPanel();
			jpanelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
			jpanelCentral.setName("jpanelCentral");
			jpanelCentral.setLayout(new BorderLayout(10, 10));
		}
		return jpanelCentral;
	}

	private JMenuBar getMenuBar_1() {
		if (barraMenú == null) {
			barraMenú = new JMenuBar();
			barraMenú.setName("barraMenú");
			barraMenú.add(getMenúAyuda());
		}
		return barraMenú;
	}

	private JMenu getMenúAyuda() {
		if (menúAyuda == null) {
			menúAyuda = new JMenu("Ayuda");
			menúAyuda.setMnemonic(KeyEvent.VK_U);
			menúAyuda.setName("menúAyuda");
			menúAyuda.add(getOpmenúAcercaDe());
		}
		return menúAyuda;
	}

	private JMenuItem getOpmenúAcercaDe() {
		if (opmenúAcercaDe == null) {
			opmenúAcercaDe = new JMenuItem("Acerca de…");
			opmenúAcercaDe.addActionListener(e -> control.mostrarCréditos());
			opmenúAcercaDe.setMnemonic(KeyEvent.VK_A);
			opmenúAcercaDe.setName("opmenúAcercaDe");

		}
		return opmenúAcercaDe;
	}

	private JPanel getJpanelEstado() {
		if (jpanelEstado == null) {
			jpanelEstado = new JPanel();
			jpanelEstado.setBackground(UIManager.getColor("ToolTip.background"));
			jpanelEstado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			jpanelEstado.setName("jpanelEstado");
			jpanelEstado.setLayout(new BorderLayout(0, 0));
			jpanelEstado.add(getTextoEstado());
		}
		return jpanelEstado;
	}

	private JLabel getTextoEstado() {
		if (textoEstado == null) {
			textoEstado = new JLabel("© VentanaTicket 2024");
			textoEstado.setName("textoEstado");
			textoEstado.setBorder(new EmptyBorder(2, 5, 2, 5));
		}
		return textoEstado;
	}
}
