package umg.Vistas;

import umg.DAO.UsuariosDAO;
import umg.DTO.UsuariosDTO;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtUsuario;
    private JTextField txtPassword;

    UsuariosDAO usuariosDAO = new UsuariosDAO();

    public Login() {
        setSize(400, 300);

        // Centrar el formulario en la pantalla
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);



    }

    private void onOK() {
        // add your code here
        String usuario =  txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null,"Usuario y Contraseña Requeridos","Campos Requeridos",2);
            return;
        }

        UsuariosDTO u = usuariosDAO.leer(usuario);
        if (u==null){
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta", "Inicio de Sesión Incorrecta ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (BCrypt.checkpw(password, u.getPassword())) {
            Inicio inicio= new Inicio();
            inicio.setVisible(true);
            inicio.requestFocus();

            setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Inicio de Sesión Incorrecta ", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);

    }
}
