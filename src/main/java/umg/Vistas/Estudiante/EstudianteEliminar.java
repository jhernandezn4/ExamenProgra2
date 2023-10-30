package umg.Vistas.Estudiante;

import umg.DAO.EstudiantesDAO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class EstudianteEliminar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton butoneliminar;
    private JTextField textoCarnet;
    private JButton buscarButton;
    private JTextField textonombre;
    private JTextField textoapellido;
    private EstudiantesDAO dao = new EstudiantesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();

    public EstudianteEliminar() {
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(butoneliminar);

        butoneliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
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
        // add your code here
        dao.eliminar(estudiante);
        JOptionPane.showMessageDialog(null, "Estudiante Eliminar", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary


        dispose();
    }

    public static void main(String[] args) {
        EstudianteEliminar dialog = new EstudianteEliminar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
