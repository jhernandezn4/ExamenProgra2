package umg;

import umg.DAO.UsuariosDAO;
import umg.DTO.UsuariosDTO;
import umg.FORMS.formLogin;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        formLogin formLogin = new formLogin();
        formLogin.pack();
        formLogin.setVisible(true);

    }
    public static void crearUsuarioInicial(){
        //SE ENCRIPTA LA CONTRASEÑA
        String password = "1234";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        UsuariosDTO usuario= new UsuariosDTO();
        usuario.setUsuario("admin");
        usuario.setNombre("Administrador");
        usuario.setPassword(hashedPassword);

        UsuariosDAO DBUsuario= new UsuariosDAO();
        DBUsuario.crear(usuario);
        System.out.println("usuario creado");


    }
}