package umg.FORMS.Cursos;

import umg.DAO.CursosDAO;
import umg.DTO.CursosDTO;

import javax.swing.*;
import java.awt.event.*;

public class formCursoEliminar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnProcesar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JButton btnCancelar;
    private CursosDTO curso = new CursosDTO();
    private CursosDAO dao = new CursosDAO();

    public formCursoEliminar(CursosDTO e) {
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
    private void establecerDatos(CursosDTO e){
        curso=e;
        setTitle("Editar "+curso.getCodigo());
        txtCodigo.setText(curso.getCodigo());
        txtNombre.setText(curso.getNombre());
    }

    private void procesar() {
        dao.eliminar(curso);
        JOptionPane.showMessageDialog(null, "Curso Eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }
    public String getCodigo(){
        return this.txtCodigo.getText();
    }
}
