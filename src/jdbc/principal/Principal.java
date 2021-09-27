package jdbc.principal;

import java.util.Scanner;
import jdbc.servicio.UsuarioServicio;

public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
        UsuarioServicio usuarioServicio = new UsuarioServicio();

        try {
            usuarioServicio.imprimirUsuarios();

            System.out.println("Ingrese correo: ");
            String correo = entrada.next();

            System.out.println("Ingrese nombre: ");
            String nombre = entrada.next();

            System.out.println("Ingrese apellido: ");
            String apellido = entrada.next();

            usuarioServicio.crearUsuario(correo, nombre, apellido);

            usuarioServicio.imprimirUsuarios();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
