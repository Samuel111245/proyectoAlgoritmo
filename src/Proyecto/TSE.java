
package Proyecto;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import static Proyecto.Run.configInicial;
import static Proyecto.Usuario.iniciarSesion;


public class TSE {
   public void tse_2023() {
       
       TSE tse = new TSE();
               
        // Realiza la configuración inicial
        configInicial();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar el menú principal
            System.out.println("********** Menú Principal **********");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarte");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 1) {
                // Iniciar Sesión
                Usuario usuario = new Usuario();
                usuario.iniciarSesion();

                if (usuario != null) {
                    // Determinar el rol del usuario
                    System.out.println("Confirme de nuevo sus datos");
                    System.out.println("correo: ");
                    String correo = scanner.next();
                    System.out.println("contrasenia: ");
                    String pass = scanner.next();
                    
                    String rol = tse.validarRol(correo, pass);
                    
                    System.out.println(rol);

                    // Menú específico según el rol
                    while (true) {
                        System.out.println("### Menú de " + rol + " ###");
                        if(rol.equals("Administrador"))
                            System.out.println("1. Crear elecciones");
                        
                        if(rol.equals("RegistradorDeVotante"))
                            System.out.println("2. Inscribir Votante");
                        
                        if(rol.equals("Votante"))
                            System.out.println("3. Votar");
                        
                        if(rol.equals("Auditor"))
                            System.out.println("4. Generar reporte");
                        
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Seleccione una opción o presione 5 para salir  ");
                        

                        int opcionRol = scanner.nextInt();

                        switch (opcionRol) {
                            case 1:
                                int opc = 0;
                                do{
                                // Opción 1 para el rol actual
                                System.out.println("Rol Administrador");
                                System.out.println("1. Crear Elecciones");
                                System.out.println("2. Inscribir Candidato");
                                System.out.println("3. Eliminar Candidato");
                                System.out.println("4. Salir");
                                opc = scanner.nextInt();
                                Administrador admin = new Administrador();
                                switch(opc){
                                    case 1: 
                                        
                                        admin.crearEleccion();
                                        break;
                                    case 2: 
                                        System.out.println("Nombre de la Eleccion");
                                        String nombreEleccion = scanner.next();
                                        admin.inscribirCandidato(nombreEleccion);
                                    case 3: 
                                        System.out.println("Nombre de la Eleccion");
                                        String nombreEleccion2 = scanner.next();
                                        System.out.println("Nombre de Candidato");
                                        String nombreCandidato = scanner.next();
                                        admin.eliminarCandidato(nombreEleccion2, nombreCandidato);
                                        break;
                                    case 4:
                                        opc = 5;
                                        break;
                                    default: 
                                        System.out.print("Opcion invalida");
                                    }
                                    }while(opc != 5);

                                    break;

                            case 2:
                                RegistradorDeVotante rg = new RegistradorDeVotante();
                                rg.registrarVotante();
                                System.out.println("");
                                break;
                            case 3:
                                Votante vt = new Votante();
                                System.out.println("Ingrese una fecha");
                                String fecha = scanner.next();
                                vt.mostrarEleccionesPorFecha(fecha);
                                System.out.println("");
                                System.out.println("");
                                System.out.println("Ingrese el codigo de eleccion");
                                String id = scanner.next();
                                vt.mostrarCandidatosPorEleccion(id);
                                
                                System.out.println("");
                                System.out.println("");
                                System.out.println("Emitir voto");
                                System.out.println("");
                                
                                System.out.println("Nombre de Eleccion");
                                String elc = scanner.next();
                                System.out.println("Nombre candidato");
                                String cand = scanner.next();
                                System.out.println("Nombre votante");
                                String vtn = scanner.next();
                                vt.emitirVoto(elc, cand, vtn);
                                break;
                            case 4:
                                Auditor aud = new Auditor();
                                System.out.println("Generar Reporte");
                                System.out.println("1. Si");
                                System.out.println("2. no");
                                int opc3 = scanner.nextInt();
                                switch(opc3){
                                    case 1: 
                                        aud.mostrarInforme();
                                        break;
                                    case 2:
                                        System.out.println("Adios");
                                        break;
                                    default:
                                        System.out.println("opcion invalida, adios");
                                        break;
                                }
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (opcionRol == 5) {
                            break; // Salir del menú de rol
                        }
                    }
                }
            } else if (opcion == 2) {
                Usuario user = new Usuario();
                user.agregarUsuario();
                
            } else if(opcion == 3){
                System.out.println("Opción no válida.");
                break;
                        
            }
        }

        scanner.close();
    }
   
   
   public static String validarRol(String correoIngresado, String contraseniaIngresada) {
       //String rolRegistrado = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("docProject/usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String correoRegistrado = partes[2]; // Suponiendo que el correo es el tercer elemento
                String contraseniaRegistrada = partes[3]; // Suponiendo que la contraseña es el cuarto elemento
                String rolRegistrado = partes[4]; // Suponiendo que el rol es el quinto elemento

                if (correoRegistrado.equals(correoIngresado) && contraseniaRegistrada.equals(contraseniaIngresada)) {
                    return rolRegistrado; // El rol es válido para el correo y contraseña ingresados
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios.");
            e.printStackTrace();
        }

        return ""; // El rol no es válido para las credenciales ingresadas
    }
}


