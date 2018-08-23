package biblio;

import static java.nio.file.StandardOpenOption.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class main {
	public static Map<String, Libro> libroMatriz = new TreeMap<String, Libro>();
	public static Path path = Paths.get("C:\\Users\\Java\\Desktop\\path.txt");

	public static void main(String[] args) {

		String[] opcionesLibro = { "Agregar libro", "consultar estado", "Retirar libro", "devolver libro" };

		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			libroMatriz = (Map<String, Libro>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		boolean stahp = true;

		while (stahp) {
			int opcionBiblioteca = JOptionPane.showOptionDialog(null, "Seleccione la operacion a realizar", "bibliofer",
					0, 0, null, opcionesLibro, 1);
			switch (opcionBiblioteca) {

			case 0:
				String libroNombreTemp = JOptionPane.showInputDialog("Ingrese nombre del libro");
				libroMatriz.put(libroNombreTemp.toLowerCase() + ",",
						new Libro(libroNombreTemp, JOptionPane.showInputDialog("Ingrese el nombre del autor") + ",",
								JOptionPane.showInputDialog("Ingrese el nombre de la editorial" + ","),
								JOptionPane.showConfirmDialog(null, "Esta prestado ahora?")));

				break;

			case 1:
				String libroConsultado = JOptionPane
						.showInputDialog("Ingrese el nombre del libro que desea encontrar".toLowerCase());
				if (!libroMatriz.isEmpty() && libroMatriz.containsKey(libroConsultado)) {
					JOptionPane.showMessageDialog(null, "El libro existe!!!");
					JOptionPane.showMessageDialog(null, libroMatriz.get(libroConsultado).toString());
				} else {
					JOptionPane.showMessageDialog(null, "El libro " + libroConsultado + " no existe");

				}
				break;

			case 2:
				String libroARetirar = JOptionPane.showInputDialog("Escriba el libro que desea retirar".toLowerCase());
				Libro libroTemp = libroMatriz.get(libroARetirar);
				if (!libroMatriz.isEmpty() && libroMatriz.containsKey(libroARetirar) && libroTemp.getPrestado() == 1) {
					libroTemp.setPrestado(0);
					libroMatriz.put(libroARetirar, libroTemp);
					JOptionPane.showMessageDialog(null, "libro retirado");

				} else if (libroTemp.getPrestado() == 0) {
					JOptionPane.showMessageDialog(null, "El libro esta prestado", "bibliofer", 0);
				} else {
					JOptionPane.showMessageDialog(null, "Error, no se encontro el libro");

				}
				break;
			case 3:
				String libroADevolver = JOptionPane
						.showInputDialog("Escriba el libro que desea reingresar".toLowerCase());
				Libro libroTemp2 = libroMatriz.get(libroADevolver);
				if (!libroMatriz.isEmpty() && libroMatriz.containsKey(libroADevolver)
						&& libroTemp2.getPrestado() == 0) {
					libroTemp2.setPrestado(1);
					libroMatriz.put(libroADevolver, libroTemp2);
					JOptionPane.showMessageDialog(null, "libro devuelto");
				} else if (libroTemp2.getPrestado() == 1) {
					JOptionPane.showMessageDialog(null, "El libro ya esta en la biblioteca", "bibliofer", 0);
				} else {
					JOptionPane.showMessageDialog(null, "Error, no se encontro el libro");
				}
				break;

			default:

				try {
					ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, CREATE));
					out.writeObject(libroMatriz);
				} catch (IOException e) {

					e.printStackTrace();
				}
				System.exit(0);

			}

		}
	}

}
