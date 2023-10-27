package Proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Auditor extends Rol {
    
    public Auditor(){};
    
    public Auditor(String nombre, String apellido, String correo, String pass) {
        super(nombre, apellido, correo, pass);
    }
    
    // Método para mostrar un informe con usuarios, elecciones y votos
    public static void mostrarInforme() {
        System.out.println("### Informe del Auditor ###");

        System.out.println("Usuarios Registrados:");
        mostrarContenidoArchivo("docProject/usuarios.txt");

        System.out.println("\nElecciones Disponibles:");
        mostrarContenidoArchivo("docProject/elecciones.txt");

        System.out.println("\nVotos Emitidos:");
        mostrarContenidoArchivo("docProject/votos.txt");
    }

    // Método para mostrar el contenido de un archivo
    public static void mostrarContenidoArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }
}


