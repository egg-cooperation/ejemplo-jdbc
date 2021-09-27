package jdbc.persistencia;

import java.util.ArrayList;
import java.util.List;
import jdbc.entidad.Usuario;

public class UsuarioDAO extends DAO {

    public void guardarUsuario(Usuario usuario) throws Exception {
        try {
            if (usuario == null) {
                throw new Exception("Usuario inválido");
            }

            String plantilla = "INSERT INTO usuario(correo, nombre, apellido) "
                    + "VALUES('%s', '%s', '%s');";
            String sql = String.format(plantilla, usuario.getCorreo(), usuario.getNombre(), usuario.getApellido());

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar usuario");
        }
    }

    public void modificarUsuario(Usuario usuario) throws Exception {
        try {
            if (usuario == null) {
                throw new Exception("Usuario inválido");
            }

            String sql = "UPDATE usuario SET nombre = '" + usuario.getNombre() + "', "
                    + "apellido = '" + usuario.getApellido() + "' "
                    + "WHERE correo = '" + usuario.getCorreo() + "';";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar usuario");
        }
    }

    public void eliminarUsuario(String correo) throws Exception {
        try {
            String sql = "DELETE FROM usuario WHERE correo = '" + correo + "';";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar usuario");
        }
    }

    public List<Usuario> obtenerUsuarios() throws Exception {
        try {
            String sql = "SELECT correo, nombre, apellido FROM usuario;";

            consultarBase(sql);

            List<Usuario> usuarios = new ArrayList<>();
            Usuario usuario = null;

            while (resultado.next()) {
                usuario = new Usuario();

                usuario.setCorreo(resultado.getString(1));
                usuario.setNombre(resultado.getString(2));
                usuario.setApellido(resultado.getString(3));

                usuarios.add(usuario);
            }

            return usuarios;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener usuarios");
        } finally {
            desconectarBase();
        }
    }
}
