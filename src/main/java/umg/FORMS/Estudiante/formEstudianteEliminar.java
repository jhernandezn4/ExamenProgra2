package umg.FORMS.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class formEstudianteEliminar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton btnCancelar;
    private JButton btnProcesar;
    private JTextField txtCarnet;
    private JButton buscarButton;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private EstudiantesDAO dao = new EstudiantesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();

    public formEstudianteEliminar(EstudiantesDTO e) {
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
        establecerDatos(e);
    }
    private void establecerDatos(EstudiantesDTO e){
        estudiante=e;
        setTitle("Eliminar "+estudiante.getCarnet());
        txtCarnet.setText(estudiante.getCarnet());
        txtNombre.setText(estudiante.getNombre());
        txtApellido.setText(estudiante.getApellido());
    }
    private void procesar() {
        dao.eliminar(estudiante);
        JOptionPane.showMessageDialog(null, "Estudiante Eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
