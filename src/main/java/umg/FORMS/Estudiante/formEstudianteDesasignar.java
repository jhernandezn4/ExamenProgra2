package umg.FORMS.Estudiante;

import umg.DAO.CursosDAO;
import umg.DAO.InscripcionesDAO;
import umg.DTO.CursosDTO;
import umg.DTO.EstudiantesDTO;
import umg.DTO.InscripcionesDTO;
import umg.FORMS.formInicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class formEstudianteDesasignar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnCancelar;
    private JTextField txtEstudiante;
    private JTable tblCursos;

    private EstudiantesDTO estudiante;
    private Collection<InscripcionesDTO> cursos;

    private CursosDTO curso = new CursosDTO();
    private CursosDAO cursosDAO= new CursosDAO();

    private InscripcionesDTO inscripcion = new InscripcionesDTO();
    private InscripcionesDAO inscripcionesDAO = new InscripcionesDAO();


    private DefaultTableModel mdlTableCursos = new formInicio.TableModel();

    public formEstudianteDesasignar(EstudiantesDTO e ){
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


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
        cursos=e.getCursos();
        establecerTablaCursos();
        cargarCursos();
        establecerDatos(e);
    }
    public void establecerTablaCursos(){
        mdlTableCursos.addColumn("Codigo");
        mdlTableCursos.addColumn("Nombre");

        tblCursos.setColumnSelectionAllowed(false);
        tblCursos.setModel(mdlTableCursos);
        tblCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int r = tblCursos.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < tblCursos.getRowCount()) {
                        tblCursos.setRowSelectionInterval(r, r);
                    } else {
                        tblCursos.clearSelection();
                    }

                    int rowindex = tblCursos.getSelectedRow();
                    String codigo = (String)tblCursos.getModel().getValueAt(rowindex, 0);
                    if (rowindex < 0)
                        return;
                    curso= cursosDAO.leer(codigo);
                    if (curso==null){
                        JOptionPane.showMessageDialog(null, "No se encuentra el curso", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    procesar();
                }

            }
        });
    }
    public void cargarCursos() {

        for(InscripcionesDTO inscripcion : cursos){
            mdlTableCursos.addRow(new Object[]{
                    inscripcion.getCurso().getCodigo(),
                    inscripcion.getCurso().getNombre(),
            });
        }
    }
    private void establecerDatos(EstudiantesDTO e){
        estudiante=e;
        setTitle("Quitar Cursos "+estudiante.getCarnet());
        txtEstudiante.setText(estudiante.getCarnet()+" - "+estudiante.getNombre()+" "+estudiante.getApellido());

    }

    private void procesar() {
        System.out.println(curso.getCursosId()+" "+estudiante.getEstudianteId());
        InscripcionesDTO inscripcion = inscripcionesDAO.leer(curso.getCursosId(), estudiante.getEstudianteId());
        if (inscripcion==null){
            JOptionPane.showMessageDialog(null, "No se encuentra", "Exito", JOptionPane.WARNING_MESSAGE);
            return;
        }
        inscripcionesDAO.eliminar(inscripcion);
        JOptionPane.showMessageDialog(null, "Estudiante Asignado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }
}
