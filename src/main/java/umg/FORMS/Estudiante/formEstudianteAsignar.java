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
import java.util.List;

public class formEstudianteAsignar extends JDialog {
    private JPanel contentPane;

    private JButton btnCancelar;
    private JTextField txtEstudiante;
    private JTextField txtNombre;
    private JTable tblCursos;


    private EstudiantesDTO estudiante;
    private List<CursosDTO> cursos;

    private CursosDTO curso = new CursosDTO();
    private CursosDAO cursosDAO= new CursosDAO();

    private InscripcionesDTO inscripcion = new InscripcionesDTO();
    private InscripcionesDAO inscripcionesDAO = new InscripcionesDAO();

    private DefaultTableModel mdlTableCursos = new formInicio.TableModel();

    public formEstudianteAsignar(EstudiantesDTO e, List<CursosDTO> c) {
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
        cursos=c;
        establecerTablaCursos();
        cargarCursos();
        establecerDatos(e);
    }
    public void establecerTablaCursos(){
        mdlTableCursos.addColumn("Codigo");
        mdlTableCursos.addColumn("Nombre");
        mdlTableCursos.addColumn("Estudiantes Asignados");

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
                    }
                    procesar();
                }

            }
        });
    }
    public void cargarCursos() {

        for(CursosDTO curso : cursos){
            mdlTableCursos.addRow(new Object[]{
                    curso.getCodigo(),
                    curso.getNombre(),
                    curso.getEstudiantes().toArray().length+" Estudiantes"
            });
        }
    }
    private void establecerDatos(EstudiantesDTO e){
        estudiante=e;
        setTitle("Asignar "+estudiante.getCarnet());
        txtEstudiante.setText(estudiante.getCarnet()+" - "+estudiante.getNombre()+" "+estudiante.getApellido());

    }

    private void procesar() {
        InscripcionesDTO inscripcion = new InscripcionesDTO();
        inscripcion.setEstudiantesId(estudiante.getEstudianteId());
        inscripcion.setCursosId(curso.getCursosId());
        inscripcionesDAO.crear(inscripcion);
        JOptionPane.showMessageDialog(null, "Estudiante Asignado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    private void cerrar() {
        dispose();
    }

}
