
package Proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class Usuario {
    String nombre;
    String apellido;
    String correo;
    String pass;
    List<String> rol;
    
    // Metodos Constructores
    public Usuario(){}

    public Usuario(String nombre, String apellido, String correo, String pass, List<String> rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public List<String> getRol() {
        return rol;
    }
    public void setRol(List<String> rol) {
        this.rol = rol;
    }
    
    // Metodos de Clase
    
    // Registrar
    public static void agregarUsuario() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese apellido: ");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese correo: ");
            String correo = scanner.nextLine();

            System.out.println("Ingrese contraseña: ");
            String pass = scanner.nextLine();

            System.out.println("Ingrese rol: ");
            String rol = scanner.nextLine();

            // Crear un FileWriter para escribir en el archivo
            FileWriter fileWriter = new FileWriter("docProject/usuarios.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Escribir los datos en el archivo
            printWriter.println(nombre + "," + apellido + "," + correo + "," + pass + "," + rol);

            // Cerrar el archivo
            printWriter.close();
            System.out.println("Usuario agregado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
    
    
    // Editar 
    
    public static void editarUsuario(String nombreUsuario) {
    Scanner scanner = new Scanner(System.in);

    try {
        File archivoUsuarios = new File("docProject/usuarios.txt");
        File archivoTemporal = new File("docProject/temporal.txt");

        BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios));
        PrintWriter writer = new PrintWriter(new FileWriter(archivoTemporal, true));

        String linea;
        boolean encontrado = false;

        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombre = partes[0];

            if (nombre.equals(nombreUsuario)) {
                encontrado = true;

                // Realiza las ediciones necesarias (por ejemplo, permite cambiar el nombre, apellido, correo, pass o rol)
                System.out.println("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();

                System.out.println("Ingrese el nuevo apellido: ");
                String nuevoApellido = scanner.nextLine();

                System.out.println("Ingrese el nuevo correo: ");
                String nuevoCorreo = scanner.nextLine();

                System.out.println("Ingrese la nueva contraseña: ");
                String nuevaPass = scanner.nextLine();

                System.out.println("Ingrese el nuevo rol: ");
                String nuevoRol = scanner.nextLine();

                // Escribe los datos editados en el archivo temporal
                writer.println(nuevoNombre + "," + nuevoApellido + "," + nuevoCorreo + "," + nuevaPass + "," + nuevoRol);
                System.out.println("Usuario editado con éxito.");
            } else {
                // Si no es el usuario a editar, copia la línea original al archivo temporal
                writer.println(linea);
            }
        }

        reader.close();
        writer.close();

        // Reemplaza el archivo original con el archivo temporal
        archivoUsuarios.delete();
        archivoTemporal.renameTo(archivoUsuarios);

                if (!encontrado) {
                    System.out.println("Usuario no encontrado. Cerrando el método de edición.");
                    return; // Sale del método si el usuario no se encuentra
                }
            } catch (IOException e) {
                System.err.println("Error al editar el usuario.");
                e.printStackTrace();
            }
        }

    
    // Deshabilitar 
    
    public static void deshabilitarUsuarioPorCorreo(String correoUsuario) {
    try {
        File archivoUsuarios = new File("docProject/usuarios.txt");
        File archivoTemporal = new File("docProject/temporal.txt");

        BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios));
        PrintWriter writer = new PrintWriter(new FileWriter(archivoTemporal, true));

        String linea;
        boolean encontrado = false;

        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            String correo = partes[2]; // El correo se encuentra en la tercera posición en la línea

            if (correo.equals(correoUsuario)) {
                encontrado = true;
                System.out.println("Usuario con correo '" + correoUsuario + "' deshabilitado con éxito.");
            } else {
                // Si no es el usuario a deshabilitar, copia la línea original al archivo temporal
                writer.println(linea);
            }
        }

            reader.close();
            writer.close();

            // Reemplaza el archivo original con el archivo temporal (sin la línea eliminada)
            archivoUsuarios.delete();
            archivoTemporal.renameTo(archivoUsuarios);

            if (!encontrado) {
                System.out.println("Usuario con correo '" + correoUsuario + "' no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al deshabilitar usuario por correo.");
            e.printStackTrace();
        }
    }

    // Inicio de sesion
    public static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inicio de Sesión:");
        System.out.print("Ingrese su correo: ");
        String correoIngresado = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseniaIngresada = scanner.nextLine();

        // Validar las credenciales leyendo el archivo "usuarios.txt"
        if (validarCredenciales(correoIngresado, contraseniaIngresada)) {
            // Usuario autenticado
            System.out.println("Inicio de sesión exitoso.");
            // Aquí debes determinar el rol del usuario y mostrar las funcionalidades correspondientes.
        } else {
            System.out.println("Credenciales incorrectas. Inicio de sesión fallido.");
        }
    }
    
    private static boolean validarCredenciales(String correo, String contrasenia) {
        try (BufferedReader reader = new BufferedReader(new FileReader("docProject/usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String correoRegistrado = partes[2]; // Suponiendo que el correo es el tercer elemento
                String contraseniaRegistrada = partes[3]; // Suponiendo que la contraseña es el cuarto elemento

                if (correoRegistrado.equals(correo) && contraseniaRegistrada.equals(contrasenia)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios.");
            e.printStackTrace();
        }
        return false; // Credenciales incorrectas
    }
    
    
}// Fin clase


