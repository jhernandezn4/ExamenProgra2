package umg.FORMS.Cursos;

import umg.DAO.CursosDAO;
import umg.DTO.CursosDTO;
import umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.*;

public class formCursoEditar extends JDialog {
    private JPanel contentPane;
    private JButton btnCancelar;
    private JButton btnProcesar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private CursosDTO curso = new CursosDTO();
    private CursosDAO dao = new CursosDAO();

    public formCursoEditar(CursosDTO e) {
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
    private boolean validar(){
        return (!txtCodigo.getText().isBlank() && !txtNombre.getText().isBlank());
    }
    private void procesar() {
        if (!validar()){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();

        curso.setCodigo(codigo);
        curso.setNombre(nombre);
        dao.actualizar(curso);
        JOptionPane.showMessageDialog(null, "Curso Actualizado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }
    public String getCodigo(){
        return this.txtCodigo.getText();
    }
}
