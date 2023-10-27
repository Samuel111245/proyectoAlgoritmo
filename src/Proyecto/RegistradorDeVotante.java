
package Proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegistradorDeVotante extends Rol {
    
    
    public RegistradorDeVotante(String nombre, String apellido, String correo, String pass) {
        super(nombre, apellido, correo, pass);
    }
    public RegistradorDeVotante(){}
    
    // Método para verificar si una persona es mayor de 18 años
    public static boolean esMayorDe18(String fechaNacimiento) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formato);
            LocalDate fechaHoy = LocalDate.now();
            int edad = fechaHoy.getYear() - fechaNac.getYear();
            return edad >= 18;
        } catch (Exception e) {
            System.err.println("Error al verificar la edad.");
            return false;
        }
    }

    // Método para generar una contraseña aleatoria
    public static String generarContraseniaAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasenia = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(caracteres.length());
            contrasenia.append(caracteres.charAt(index));
        }

        return contrasenia.toString();
    }

    // Método para registrar votantes
    public static void registrarVotante() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Registro de Votante:");
            System.out.println("Ingrese Nombres Completos:");
            String nombres = scanner.nextLine();

            System.out.println("Ingrese Apellidos Completos:");
            String apellidos = scanner.nextLine();

            System.out.println("Ingrese CUI:");
            String cui = scanner.nextLine();

            System.out.println("Ingrese Sexo:");
            String sexo = scanner.nextLine();

            System.out.println("Ingrese Fecha de Nacimiento (DD/MM/AAAA):");
            String fechaNacimiento = scanner.nextLine();

            if (esMayorDe18(fechaNacimiento)) {
                System.out.println("Ingrese Dirección de Residencia:");
                String direccionResidencia = scanner.nextLine();

                System.out.println("Ingrese Departamento de Residencia:");
                String departamentoResidencia = scanner.nextLine();

                System.out.println("Ingrese Municipio de Residencia:");
                String municipioResidencia = scanner.nextLine();

                System.out.println("Ingrese País de Residencia:");
                String paisResidencia = scanner.nextLine();

                // Generar una contraseña aleatoria
                String contrasenia = generarContraseniaAleatoria();

                // Crear un FileWriter para escribir en el archivo "votante.txt"
                FileWriter fileWriter = new FileWriter("docProject/votante.txt", true);
                fileWriter.write(nombres + "," + apellidos + "," + cui + "," + sexo + "," + fechaNacimiento + "," +
                        direccionResidencia + "," + departamentoResidencia + "," + municipioResidencia + "," +
                        paisResidencia + "," + contrasenia + System.lineSeparator());
                fileWriter.close();

                System.out.println("Votante registrado con éxito. Contraseña generada: " + contrasenia);
            } else {
                System.out.println("El votante debe ser mayor de 18 años para registrarse.");
            }
        } catch (IOException e) {
            System.err.println("Error al registrar el votante.");
            e.printStackTrace();
        }
    }

}

