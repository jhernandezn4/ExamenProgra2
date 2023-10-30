package umg.Vistas.Cursos;

import umg.DAO.CursosDAO;
import umg.DTO.CursosDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CursosListar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tabla;
    private JButton cerrarButton;
    private JButton actualizarButton;

    private CursosDTO curso = new CursosDTO();
    private CursosDAO dao = new CursosDAO();

    private List<CursosDTO> cursos = new ArrayList<CursosDTO>();

    public CursosListar() {
        setContentPane(contentPane);
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);




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
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });
        listar();
    }
    public void listar(){
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");


        cursos=dao.listar();
        tabla.setModel(modelo);
        for(CursosDTO curso : cursos){
            modelo.addRow(new Object[]{curso.getCodigo(), curso.getNombre()});
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
        CursosListar dialog = new CursosListar();

        dialog.setVisible(true);
        System.exit(0);
    }
}
