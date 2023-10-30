package umg.Vistas.Inscripciones;

import umg.DAO.CursosDAO;
import umg.DAO.EstudiantesDAO;
import umg.DAO.InscripcionesDAO;
import umg.DTO.CursosDTO;
import umg.DTO.EstudiantesDTO;
import umg.DTO.InscripcionesDTO;

import javax.swing.*;
import java.awt.event.*;

public class InscripcionesEliminar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField campocarnet;
    private JTextField campocodigo;
    private JButton buttonEliminar;
    private InscripcionesDTO inscripcion = new InscripcionesDTO();
    private InscripcionesDAO inscripcionesDAO = new InscripcionesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();
    private EstudiantesDAO estudiantesDAO = new EstudiantesDAO();
    private CursosDAO cursosDAO = new CursosDAO();
    private CursosDTO curso= new CursosDTO();

    public InscripcionesEliminar() {
        setContentPane(contentPane);
        setModal(true);

        setSize(300, 200);
        setLocationRelativeTo(null);

        buttonEliminar.addActionListener(new ActionListener() {
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
        estudiante= estudiantesDAO.leer(campocarnet.getText());
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiante No Encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
        curso= cursosDAO.leer(campocodigo.getText());
        if (curso==null){
            JOptionPane.showMessageDialog(null, "Curso No Encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
        inscripcion.setInscripcionesId(estudiante.getEstudianteId());
        inscripcion.setCursosId(curso.getCursosId());
        inscripcionesDAO.eliminar(inscripcion);
        JOptionPane.showMessageDialog(null, "Inscripcion Eliminada", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        InscripcionesEliminar dialog = new InscripcionesEliminar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
