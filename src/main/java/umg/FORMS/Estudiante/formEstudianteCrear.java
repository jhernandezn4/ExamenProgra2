package umg.FORMS.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class formEstudianteCrear extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField campocarnet;
    private JTextField camponombre;
    private JTextField campoapellido;
    private JButton btnProcesar;
    private JButton btnCancelar;
    private JTextField txtCarnet;
    private JTextField txtNombre;
    private JTextField txtApellido;

    private EstudiantesDAO dao = new EstudiantesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();

    public formEstudianteCrear() {
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getRootPane().setDefaultButton(btnProcesar);
        btnProcesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesar();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cerrar();
            }
        });
        setTitle("Agregar Estudiante");
    }

    private boolean validar(){
        return (!txtCarnet.getText().isBlank() && !txtNombre.getText().isBlank() && !txtApellido.getText().isBlank() );
    }
    private void procesar() {
        if (!validar()){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String carnet = txtCarnet.getText();
        String nombre = txtNombre.getText();
        String apellido= txtApellido.getText();
        estudiante.setCarnet(carnet);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        dao.crear(estudiante);
        JOptionPane.showMessageDialog(null, "Estudiante Creado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }
    public String getCarnet(){
        return this.txtCarnet.getText();
    }
}
