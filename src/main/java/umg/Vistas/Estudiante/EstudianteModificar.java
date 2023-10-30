package umg.Vistas.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class EstudianteModificar extends JDialog {
    private JPanel contentPane;
    private JButton butonactualizar;
    private JButton buttonCancel;
    private JTextField textoCarnet;
    private JButton buscarButton;
    private JTextField textonombre;
    private JTextField textoapellido;
    private EstudiantesDAO dao = new EstudiantesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();

    public EstudianteModificar() {
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(butonactualizar);

        butonactualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
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
    private void buscar(){
        String carnet = textoCarnet.getText();
        estudiante = dao.leer(carnet);
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiante No Encontrado", "Exito", JOptionPane.WARNING_MESSAGE);
        }else{
            textonombre.setText(estudiante.getNombre());
            textoapellido.setText(estudiante.getApellido());
        }


    }
    private void onOK() {
        String nombre =textonombre.getText();
        String apellido=textoapellido.getText();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        dao.actualizar(estudiante);
        JOptionPane.showMessageDialog(null, "Estudiante Actualizado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EstudianteModificar dialog = new EstudianteModificar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
