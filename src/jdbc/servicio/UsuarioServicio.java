package jdbc.servicio;

import java.util.List;
import jdbc.entidad.Usuario;
import jdbc.persistencia.UsuarioDAO;

public class UsuarioServicio {

    private UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        usuarioDAO = new UsuarioDAO();
    }

    public void crearUsuario(String correo, String nombre, String apellido) throws Exception {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                throw new Exception("Debe ingresar un correo");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe ingresar un nombre");
            }

            if (apellido == null || apellido.trim().isEmpty()) {
                throw new Exception("Debe ingresar un apellido");
            }

            Usuario usuario = new Usuario();

            usuario.setCorreo(correo);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);

            usuarioDAO.guardarUsuario(usuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirUsuarios() throws Exception {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();

            if (usuarios.isEmpty()) {
                throw new Exception("No existen usuarios");
            } else {
                System.out.println("*** LISTA DE USUARIOS");
                System.out.printf("%-25s%-15s%15s\n", "CORREO", "NOMBRE", "APELLIDO");
                for (Usuario usuario : usuarios) {
                    System.out.printf("%-25s%-15s%15s\n", usuario.getCorreo(), usuario.getNombre(), usuario.getApellido());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
