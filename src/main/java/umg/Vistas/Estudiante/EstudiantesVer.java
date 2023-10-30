package umg.Vistas.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class EstudiantesVer extends JDialog {
    private JPanel contentPane;

    private JTable tabla;
    private JButton actualizarButton;
    private JButton cerrarButton;

    private EstudiantesDAO dao = new EstudiantesDAO();

    List<EstudiantesDTO> estudiantes = new ArrayList<EstudiantesDTO>();

    public EstudiantesVer() {
        setContentPane(contentPane);
        setModal(true);

        setSize(600, 500);
        setLocationRelativeTo(null);


        // call onCancel() when cross is clicked

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)  {

                int row = tabla.getSelectedRow();
                String carnet = (String)tabla.getModel().getValueAt(row, 0);
                JOptionPane.showMessageDialog(null, carnet, "Exito", JOptionPane.WARNING_MESSAGE);
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");

        estudiantes = dao.listar();
        tabla.setModel(modelo);
        for(EstudiantesDTO estudiante : estudiantes){
            modelo.addRow(new Object[]{estudiante.getCarnet(), estudiante.getNombre(), estudiante.getApellido()});
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
        EstudiantesVer dialog = new EstudiantesVer();

        dialog.setVisible(true);
        System.exit(0);
    }
}
