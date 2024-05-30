package jcolonia.daw2023.dosventanas;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulación de dispensador de tickets como los usados en la cola de comercios
 * tradicionales. Se mantienen dos contadores que indican la posición de la
 * cabeza —cliente siendo atendido— y del final de la cola —último cliente,
 * pidiendo turno—. Ambos avanzan de uno en uno, con la cola siempre por detrás
 * de la cabeza. El turno viene dado por un número entero positivo de dos
 * dígitos [00..99] y una letra, mayúscula para la serie [A..Z].
 * 
 * @author <a href="mailto:dmartin.jcolonia@gmail.com">David H. Martín
 *         Alonso</a>
 * @version 3.0 (20240514)
 *
 */
public class TicketTurno {
	/** Identificación, texto descriptivo o título del Dispensador. */
	private final String descripción;

	/** Número de la cola. */
	private int último;

	/** Letra de la cola. */
	private char serie;

	/** Número de la cabeza. */
	private int turno;

	/** Letra de la cabeza. */
	private char letraTurno;

	/**
	 * Finalizadas todas las series de la A a la Z, indica la existencia de desfases
	 * entre la secuencia de letras de la cabeza y de la cola. Facilita el cruce
	 * cuando la cabeza va por la letra «Z» y se están repartiendo tickets de la
	 * letra «A»; pero un valor mayor que «1» supondría confusión para los clientes
	 * en la cola.
	 */
	private int desfase;

	/**
	 * Recoge el texto proporcionado como título/descripción e inicializa turno y
	 * ticket a «00A».
	 * 
	 * @param descripción el texto indicado
	 */
	public TicketTurno(String descripción) {
		this.descripción = descripción;
		último = 0;
		serie = 'A';
		turno = 0;
		letraTurno = 'A';
		desfase = 0;
	}

	/**
	 * Facilita la descripción.
	 * 
	 * @return el texto correspondiente
	 */
	public String getDescripción() {
		return descripción;
	}

	/**
	 * Facilita el número de la cola.
	 * 
	 * @return el número correspondiente
	 */
	public int getÚltimo() {
		return último;
	}

	/**
	 * Facilita la letra de la cola.
	 * 
	 * @return la letra correspondiente
	 */
	public char getSerie() {
		return serie;
	}

	/**
	 * Facilita el número de la cabeza.
	 * 
	 * @return el número correspondiente
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Facilita la letra de la cabeza.
	 * 
	 * @return la letra correspondiente
	 */
	public char getLetraTurno() {
		return letraTurno;
	}

	/**
	 * Devuelve un texto con el estado general del sistema: descripción, turno,
	 * ticket y tamaño de la cola.
	 * 
	 * <pre>
	 *   Ej.: «Bar Lento»              [98A] → 02B #4
	 * </pre>
	 * 
	 * @return el texto correspondiente
	 */
	@Override
	public String toString() {
		String display;
		display = String.format("%-24.24s [%02d%c] → %02d%c #%d", "«" + descripción + "»", turno, letraTurno, último,
				serie, cola());
		return display;
	}

	/**
	 * Incrementa el contador de la cola, o sea, el «último» ticket emitido. Al
	 * llegar a 100 pasa a 0 y avanza letra; si también la letra pasa a ser «A» se
	 * registra un incremento en el {@link #desfase}. ̣
	 * 
	 * @return el número del último, sin letra
	 */
	public int sacarTicket() {
		último = nextNúmero(último);
		if (último == 0) {
			serie = nextLetra(serie);
			if (serie == 'A') { // ¡Rollo nuevo!
				desfase++;
			}
		}
		return último;
	}

	/**
	 * Si la cabeza va por detrás de la cola ̣—hay clientes esperando— incrementa el
	 * contador de la cabeza o sea, el cliente al que «toca» atender; si no hay cola
	 * se mantiene el valor presente. Al llegar a 100 pasa a 0 y avanza letra; si
	 * también la letra pasa a ser «A» se registra un incremento en el
	 * {@link #desfase}. ̣
	 * 
	 * @return el número en cabeza, sin letra
	 */
	public int avanzarTurno() {
		// Hay desfase, o la letra va por detrás o el número va por detrás
		if (desfase > 0 || letraTurno < serie || turno < último) {
			turno = nextNúmero(turno);
			if (turno == 0) {
				letraTurno = nextLetra(letraTurno);
				if (letraTurno == 'A') { // ¡Alcanzamos rollo actual!
					desfase--;
				}
			}
		}
		return turno;
	}

	/**
	 * Suma 1 al número dado y lo pone 0 al llegar a 100.
	 * 
	 * @param n el valor inicial
	 * @return el valor modificado
	 */
	private int nextNúmero(int n) {
		int resultado;
		resultado = (n + 1) % 100;
		return resultado;
	}

	/**
	 * Avanza una letra a la letra dada o vuelve a «A» al pasar de «Z».
	 * 
	 * @param letra la letra inicial
	 * @return la letra modificada
	 */
	private char nextLetra(char letra) {
		char resultado = letra;
		if (resultado < 'Z') {
			resultado++;
		} else {
			resultado = 'A';
		}
		return resultado;
	}

	/**
	 * Calcula el número de posiciones de diferencia entra la cabeza y la cola. El
	 * valor del {@link #desfase} no puede pasar de 1.
	 * 
	 * @return el tamaño de la cola calculado
	 */
	public int cola() {
		int resultado = 0;
		if (desfase < 2) {
			if (serie >= letraTurno) {
				resultado = 100 * (serie - letraTurno) + (último - turno);
			} else {
				int númeroSeries = 'Z' - 'A' + 1;
				resultado = 100 * (númeroSeries + serie - letraTurno) + (último - turno);
			}
		}
		return resultado;
	}

	/**
	 * Prueba de uso de la clase. Crea varios dispensadores, y va pidiendo y
	 * avanzando turnos aleatoriamente.
	 * 
	 * @param args no se usa
	 */
	public static void main(String[] args) {
		String[] nombres = { "Pescadería Manolo", "Carnicería Pepa", "Mercería Toni", "Bar Lento" };

		TicketTurno[] dispensador = new TicketTurno[nombres.length];
		TicketTurno dispensadorActivo = null;

		Random rnd = ThreadLocalRandom.current();

		int i = 0;
		for (String identificador : nombres) {
			dispensador[i++] = new TicketTurno(identificador);
		}

		for (i = 1; i < 1000; i++) {
			int númeroTienda = rnd.nextInt(dispensador.length);
			dispensadorActivo = dispensador[númeroTienda];

			if (rnd.nextInt(2) == 0) {
				dispensadorActivo.sacarTicket();
			} else {
				dispensadorActivo.avanzarTurno();
			}

			System.out.println(dispensadorActivo);
		}
	}
}
