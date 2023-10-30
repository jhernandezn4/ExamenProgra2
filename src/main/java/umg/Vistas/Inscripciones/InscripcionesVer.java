package umg.Vistas.Inscripciones;

import umg.DAO.InscripcionesDAO;
import umg.DTO.InscripcionesDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesVer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tabla;
    private JButton cerrarButton;
    private JButton actualizarButton;

    private List<InscripcionesDTO> inscripciones = new ArrayList<InscripcionesDTO>();
    private InscripcionesDAO dao = new InscripcionesDAO();

    public InscripcionesVer() {
        setContentPane(contentPane);
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);

        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
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
        listar();
    }

    public void listar(){
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Carnet");
        modelo.addColumn("Estudiante");
        modelo.addColumn("Codigo Curso");
        modelo.addColumn("Nombre Curso");

        inscripciones = dao.listar();
        tabla.setModel(modelo);
        for(InscripcionesDTO inscripcion : inscripciones){
            modelo.addRow(new Object[]{
                    inscripcion.getEstudiante().getCarnet(),
                    inscripcion.getEstudiante().getNombre()+" "+inscripcion.getEstudiante().getApellido(),
                    inscripcion.getCurso().getCodigo(),
                    inscripcion.getCurso().getNombre()
            });
        }



    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        InscripcionesVer dialog = new InscripcionesVer();

        dialog.setVisible(true);
        System.exit(0);
    }
}
