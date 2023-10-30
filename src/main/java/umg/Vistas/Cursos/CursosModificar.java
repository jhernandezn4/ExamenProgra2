package umg.Vistas.Cursos;

import umg.DAO.CursosDAO;
import umg.DTO.CursosDTO;

import javax.swing.*;
import java.awt.event.*;

public class CursosModificar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton butonactualizar;
    private JTextField textocodigo;
    private JButton buscarButton;
    private JTextField textonombre;
    private CursosDTO curso = new CursosDTO();
    private CursosDAO dao = new CursosDAO();

    public CursosModificar() {
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
        curso=dao.leer(textocodigo.getText());
        if (curso==null){
            JOptionPane.showMessageDialog(null, "Curso No Encontrado", "No Encontrado", JOptionPane.WARNING_MESSAGE);
        }else{
            textonombre.setText(curso.getNombre());
        }
    }

    private void onOK() {
        // add your code here
        curso.setNombre(textonombre.getText());
        dao.actualizar(curso);
        JOptionPane.showMessageDialog(null,"Curso Actualizado","Actualizado",2);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CursosModificar dialog = new CursosModificar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
