
package Proyecto;

import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Administrador extends Rol {
   public Administrador(){};
   public Administrador(String nombre, String apellido, String correo, String pass) {
        super(nombre, apellido, correo, pass);
    }
   
   
   
   // Método estático para crear elecciones
    public static void crearEleccion() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Creación de Elección:");
            System.out.println("Ingrese el Título de la Elección:");
            String titulo = scanner.nextLine();

            System.out.println("Ingrese el Propósito de la Elección:");
            String proposito = scanner.nextLine();

            System.out.println("Ingrese una Descripción de la Elección:");
            String descripcion = scanner.nextLine();

            System.out.println("Ingrese un Código de Identificación Único:");
            String codigoIdentificacion = scanner.nextLine();

            System.out.println("Ingrese la Fecha y Hora de Inicio de Inscripción (YYYY-MM-DD HH:MM):");
            String fechaInicioInscripcion = scanner.nextLine();

            System.out.println("Ingrese la Fecha y Hora de Fin de Inscripción (YYYY-MM-DD HH:MM):");
            String fechaFinInscripcion = scanner.nextLine();

            // Crear un FileWriter para escribir en el archivo "elecciones.txt"
            FileWriter fileWriter = new FileWriter("docProject/elecciones.txt", true);
            fileWriter.write(
                titulo + "," + proposito + "," + descripcion + "," + codigoIdentificacion + "," +
                fechaInicioInscripcion + "," + fechaFinInscripcion + System.lineSeparator()
            );
            fileWriter.close();

            System.out.println("Elección creada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al crear la elección.");
            e.printStackTrace();
        }
    }
   
  
  // Método para verificar si una elección existe
    public static boolean eleccionExiste(String nombreEleccion) {
        try {
            // Leer el archivo de elecciones y verificar si el nombre coincide
            File archivoElecciones = new File("docProject/elecciones.txt");
            Scanner scanner = new Scanner(archivoElecciones);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                String nombreEleccionEnArchivo = partes[0];

                if (nombreEleccionEnArchivo.equals(nombreEleccion)) {
                    scanner.close();
                    return true; // La elección existe
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Error al verificar si la elección existe.");
            e.printStackTrace();
        }

        return false; // La elección no existe
    }
    
   // Método para inscribir candidatos en una elección
    public static void inscribirCandidato(String nombreEleccion) {
        Scanner scanner = new Scanner(System.in);

        try {
            if (eleccionExiste(nombreEleccion)) {
                System.out.println("Inscripción de Candidato en la Elección '" + nombreEleccion + "':");

                // Solicitar información del candidato
                System.out.println("Ingrese el Nombre del Candidato:");
                String nombreCandidato = scanner.nextLine();

                System.out.println("Ingrese la Formación del Candidato:");
                String formacionCandidato = scanner.nextLine();

                System.out.println("Ingrese la Experiencia Profesional del Candidato:");
                String experienciaCandidato = scanner.nextLine();

                // Agregar el candidato a un archivo "candidatos.txt" asociándolo a la elección
                FileWriter fileWriter = new FileWriter("docProject/candidatos.txt", true);
                fileWriter.write(
                    nombreCandidato + "," + formacionCandidato + "," + experienciaCandidato + "," + nombreEleccion + System.lineSeparator()
                );
                fileWriter.close();

                System.out.println("Candidato inscrito con éxito en la Elección '" + nombreEleccion + "'.");
            } else {
                System.out.println("La elección '" + nombreEleccion + "' no existe o no está disponible para inscripción de candidatos.");
            }
        } catch (IOException e) {
            System.err.println("Error al inscribir el candidato.");
            e.printStackTrace();
        }
    }
    
    // Método para eliminar candidatos de una elección
    public static void eliminarCandidato(String nombreEleccion, String nombreCandidato) {
        try {
            // Leer el archivo de candidatos y crear un nuevo archivo temporal sin el candidato a eliminar
            File archivoCandidatos = new File("docProject/candidatos.txt");
            File archivoTemporal = new File("docProject/temporal.txt");

            Scanner scanner = new Scanner(archivoCandidatos);
            FileWriter fileWriter = new FileWriter(archivoTemporal, true);

            boolean encontrado = false;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                String candidatoNombre = partes[0];
                String eleccionNombre = partes[3];

                if (!candidatoNombre.equals(nombreCandidato) || !eleccionNombre.equals(nombreEleccion)) {
                    fileWriter.write(linea + System.lineSeparator());
                } else {
                    encontrado = true;
                }
            }

            scanner.close();
            fileWriter.close();

            if (encontrado) {
                archivoCandidatos.delete();
                archivoTemporal.renameTo(archivoCandidatos);
                System.out.println("Candidato '" + nombreCandidato + "' eliminado de la Elección '" + nombreEleccion + "'.");
            } else {
                System.out.println("Candidato '" + nombreCandidato + "' no encontrado en la Elección '" + nombreEleccion + "'.");
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar el candidato.");
            e.printStackTrace();
        }
    }
   
}
