package umg.Vistas.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class EstudianteCrear extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField campocarnet;
    private JTextField camponombre;
    private JTextField campoapellido;

    private EstudiantesDAO dao = new EstudiantesDAO();

    public EstudianteCrear() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(300, 200);
        setLocationRelativeTo(null);

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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String carnet = campocarnet.getText();
        String nombre =camponombre.getText();
        String apellido=campoapellido.getText();
        EstudiantesDTO estudiante= new EstudiantesDTO();
        estudiante.setCarnet(carnet);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        dao.crear(estudiante);
        JOptionPane.showMessageDialog(null, "Estudiante Creado", "Exito", JOptionPane.INFORMATION_MESSAGE);



        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EstudianteCrear dialog = new EstudianteCrear();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
