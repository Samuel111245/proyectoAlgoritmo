
package Proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        
        TSE tse2 = new TSE();
        
        tse2.tse_2023();
           
    }// fin main
    
    
    // Metodo de configuracion inicial 
    public static void configInicial() {
        File archivoConfig = new File("docProject/config.txt");

        if (archivoConfig.exists()) {
            System.out.println("La configuración ya ha sido realizada anteriormente.");
        } else {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Configuración inicial: Ingrese la contraseña de administrador:");
                String contraseniaAdmin = scanner.nextLine();

                // Crear un FileWriter para escribir en el archivo de configuración
                FileWriter fileWriter = new FileWriter(archivoConfig);

                // Escribir la contraseña de administrador en el archivo "config"
                fileWriter.write(contraseniaAdmin);
                fileWriter.close();

                System.out.println("Configuración inicial completada. Contraseña de administrador guardada en 'config'.");
            } catch (IOException e) {
                System.err.println("Error al realizar la configuración inicial.");
                e.printStackTrace();
            }
        }
    }// fin configuracion inicial
    
}


