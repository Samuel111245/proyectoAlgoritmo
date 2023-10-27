
package Proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Votante extends Rol {
    
    public Votante(){};
    public Votante(String nombre, String apellido, String correo, String pass) {
        super(nombre, apellido, correo, pass);
    }
    
    // Método para mostrar todas las elecciones disponibles según la fecha ingresada
    public static void mostrarEleccionesPorFecha(String fecha) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaIngresada = LocalDateTime.parse(fecha, formato);

            System.out.println("Elecciones disponibles para la fecha " + fecha + ":");

            // Leer el archivo de elecciones
            try (BufferedReader reader = new BufferedReader(new FileReader("docProject/elecciones.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    String nombreEleccion = partes[0];
                    String fechaInicioInscripcionStr = partes[4];
                    String fechaFinInscripcionStr = partes[5];

                    LocalDateTime fechaInicioInscripcion = LocalDateTime.parse(fechaInicioInscripcionStr, formato);
                    LocalDateTime fechaFinInscripcion = LocalDateTime.parse(fechaFinInscripcionStr, formato);

                    // Comprobar si la fecha ingresada está dentro del período de inscripción de la elección
                    if (fechaIngresada.isAfter(fechaInicioInscripcion) && fechaIngresada.isBefore(fechaFinInscripcion)) {
                        System.out.println("- " + nombreEleccion);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al mostrar las elecciones disponibles.");
            e.printStackTrace();
        }
    }
    
    
    // Método para buscar una elección por su código de identificación único y validar su existencia
    public static boolean eleccionExistePorCodigo(String codigoEleccion) {
        try (BufferedReader reader = new BufferedReader(new FileReader("docProject/elecciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String codigoIdentificacion = partes[3];

                if (codigoIdentificacion.equals(codigoEleccion)) {
                    return true; // La elección existe
                }
            }
        } catch (IOException e) {
            System.err.println("Error al verificar si la elección existe por código.");
            e.printStackTrace();
        }

        return false; // La elección no existe
    }
    
    
    // Método para mostrar todos los candidatos asociados a la elección seleccionada
    public static void mostrarCandidatosPorEleccion(String codigoEleccion) {
        try (BufferedReader reader = new BufferedReader(new FileReader("docProject/candidatos.txt"))) {
            System.out.println("Candidatos para la elección con código de identificación '" + codigoEleccion + "':");
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombreCandidato = partes[0];
                String eleccionAsociada = partes[3];

                if (eleccionAsociada.equals(codigoEleccion)) {
                    System.out.println("- " + nombreCandidato);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al mostrar los candidatos por elección.");
            e.printStackTrace();
        }
    }
    
    // Método para emitir un voto por un candidato y guardar el voto en un archivo
    public static void emitirVoto(String nombreEleccion, String nombreCandidato, String nombreVotante) {
        try {
            // Crear un FileWriter para escribir en el archivo "votos.txt"
            FileWriter fileWriter = new FileWriter("docProject/votos.txt", true);
            fileWriter.write(
                nombreEleccion + "," + nombreCandidato + "," + nombreVotante + System.lineSeparator()
            );
            fileWriter.close();

            System.out.println("Voto emitido con éxito en la elección '" + nombreEleccion + "' por el candidato '" + nombreCandidato + "'.");
        } catch (IOException e) {
            System.err.println("Error al emitir el voto.");
            e.printStackTrace();
        }
    }
    
    
}

