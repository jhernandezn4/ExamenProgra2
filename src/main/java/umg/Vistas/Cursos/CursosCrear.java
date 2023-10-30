package umg.Vistas.Cursos;

import umg.DAO.CursosDAO;
import umg.DTO.CursosDTO;

import javax.swing.*;
import java.awt.event.*;

public class CursosCrear extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField campocodigo;
    private JTextField camponombre;

    private CursosDTO curso = new CursosDTO();
    private CursosDAO dao = new CursosDAO();

    public CursosCrear() {
        setContentPane(contentPane);
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
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
        curso.setCodigo(campocodigo.getText());
        curso.setNombre(camponombre.getText());
        dao.crear(curso);
        JOptionPane.showMessageDialog(null, "Curso Creado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CursosCrear dialog = new CursosCrear();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
