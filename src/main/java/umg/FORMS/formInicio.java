package umg.FORMS;

import umg.DAO.CursosDAO;
import umg.DAO.EstudiantesDAO;
import umg.DTO.CursosDTO;
import umg.DTO.EstudiantesDTO;
import umg.FORMS.Cursos.formCursoCrear;
import umg.FORMS.Cursos.formCursoEditar;
import umg.FORMS.Cursos.formCursoEliminar;
import umg.FORMS.Estudiante.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class formInicio extends JFrame {
    private JPanel contentPane;
    private JTable tblEstudiantes;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnInscribir;
    private JTabbedPane tabbedPane1;
    private JTable tblCursos;
    private JTextField txtCursoSearch;
    private JButton btnCursoSearch;
    private JButton btnCursoCrear;
    private JButton bEstudiantesListar;
    private JButton bCursosEliminar;
    private JButton bInscripcionesVer;
    private JButton bInscripcionesCrear;
    private JButton bInscripcionesEliminar;
    private JButton bEstudiantsCrear;
    private JButton bEstudiantesActualizar;
    private JButton bEstudiantesEliminar;
    private JButton bCursosListar;
    private JButton bCursosCrear;
    private JButton bCursosActualizar;
    private JMenu menu = new JMenu();
    private DefaultTableModel mdlTableEstudiantes = new TableModel();
    private DefaultTableModel mdlTableCursos = new TableModel();

    private EstudiantesDAO estudiantesDAO = new EstudiantesDAO();
    private EstudiantesDTO estudiante = new EstudiantesDTO();
    private List<EstudiantesDTO> estudiantes = new ArrayList<EstudiantesDTO>();

    private CursosDAO cursosDAO = new CursosDAO();
    private CursosDTO curso = new CursosDTO();
    private List<CursosDTO> cursos = new ArrayList<CursosDTO>();


    public static class TableModel extends DefaultTableModel {
        public TableModel(){
            super();
        }
        public boolean isCellEditable(int row, int column){
            return false;
        }
    }
    public formInicio() {
        setContentPane(contentPane);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Control de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        //FUNCIONALIDADES A BOTONES
        btnBuscar.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {cargarEstudiantes();}});
        btnCursoSearch.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {cargarCursos();}});
        btnInscribir.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {estudianteCrear();}});
        btnCursoCrear.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {cursoCrear();}});
        txtBuscar.addActionListener(e -> btnBuscar.doClick());
        //SETEAR INFORMACION
        establecerTablaEstudiantes();
        establecerTablaCursos();
        cargarEstudiantes();
        cargarCursos();

    }
    public void asignar(String carnet){
        estudiante = estudiantesDAO.leer(carnet);
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiantes no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formEstudianteAsignar form = new formEstudianteAsignar(estudiante, cursos);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarEstudiantes();
            }
        });
        form.setVisible(true);
    }
    public void desasignar(String carnet){
        estudiante = estudiantesDAO.leer(carnet);
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiantes no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formEstudianteDesasignar form = new formEstudianteDesasignar(estudiante);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarEstudiantes();
            }
        });
        form.setVisible(true);
    }

    /**
     * PROCESOS PARA ESTUDIANTES
     */
    public void establecerTablaEstudiantes(){
        mdlTableEstudiantes.addColumn("Carnet");
        mdlTableEstudiantes.addColumn("Nombre");
        mdlTableEstudiantes.addColumn("Apellido");
        mdlTableEstudiantes.addColumn("Cursos Asignados");

        tblEstudiantes.setColumnSelectionAllowed(false);
        tblEstudiantes.setModel(mdlTableEstudiantes);
        tblEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = tblEstudiantes.rowAtPoint(e.getPoint());
                if (r >= 0 && r < tblEstudiantes.getRowCount()) {
                    tblEstudiantes.setRowSelectionInterval(r, r);
                } else {
                    tblEstudiantes.clearSelection();
                }

                int rowindex = tblEstudiantes.getSelectedRow();
                String carnet = (String)tblEstudiantes.getModel().getValueAt(rowindex, 0);
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem asignar = new JMenuItem();
                    asignar.setText("Asignar Materias");
                    asignar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            asignar(carnet);
                        }
                    });
                    JMenuItem desasignar = new JMenuItem();
                    desasignar.setText("Quitar Materias");
                    desasignar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            desasignar(carnet);
                        }
                    });

                    JMenuItem editar = new JMenuItem();
                    editar.setText("Editar");
                    editar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            estudianteEditar(carnet);
                        }
                    });

                    JMenuItem eliminar = new JMenuItem();
                    eliminar.setText("Eliminar");
                    eliminar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            estudianteEliminar(carnet);
                        }
                    });


                    JMenuItem t = new JMenuItem("Acciones");
                    t.setEnabled(false);
                    popup.add(t);
                    popup.add(asignar);
                    popup.add(desasignar);
                    popup.add(editar);
                    popup.addSeparator();
                    popup.add(eliminar);

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    public void cargarEstudiantes() {
        String searchText = txtBuscar.getText();
        if (searchText.isBlank()){
            estudiantes = estudiantesDAO.listar();
            System.out.println("clanbo");
        }else{
            estudiantes = estudiantesDAO.listar(searchText);
            System.out.println("buscar");
        }
        mdlTableEstudiantes.setRowCount(0);
        for(EstudiantesDTO estudiante : estudiantes){
            mdlTableEstudiantes.addRow(new Object[]{
                    estudiante.getCarnet(),
                    estudiante.getNombre(),
                    estudiante.getApellido(),
                    estudiante.getCursos().toArray().length+" Cursos"
            });
        }
        tblEstudiantes.repaint();
    }
    public void estudianteCrear(){

        formEstudianteCrear form = new formEstudianteCrear();
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                txtBuscar.setText(form.getCarnet());
                cargarEstudiantes();
            }
        });
        form.setVisible(true);
    }
    public void estudianteEditar(String carnet){
        estudiante = estudiantesDAO.leer(carnet);
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiantes no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formEstudianteEditar form = new formEstudianteEditar(estudiante);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                txtBuscar.setText(form.getCarnet());
                cargarEstudiantes();
            }
        });
        form.setVisible(true);
    }
    public void estudianteEliminar(String carnet){
        estudiante = estudiantesDAO.leer(carnet);
        if (estudiante==null){
            JOptionPane.showMessageDialog(null, "Estudiantes no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formEstudianteEliminar form = new formEstudianteEliminar(estudiante);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarEstudiantes();
            }
        });
        form.setVisible(true);
    }

    /**
     * PROCESO PARA CURSOS
     */
    public void establecerTablaCursos(){
        mdlTableCursos.addColumn("Codigo");
        mdlTableCursos.addColumn("Nombre");
        mdlTableCursos.addColumn("Estudiantes Asignados");

        tblCursos.setColumnSelectionAllowed(false);
        tblCursos.setModel(mdlTableCursos);
        tblCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();


                    JMenuItem editar = new JMenuItem();
                    editar.setText("Editar");
                    editar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cursoEditar(codigo);
                        }
                    });

                    JMenuItem eliminar = new JMenuItem();
                    eliminar.setText("Eliminar");
                    eliminar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cursoEliminar(codigo);
                        }
                    });


                    JMenuItem t = new JMenuItem("Acciones");
                    t.setEnabled(false);
                    popup.add(t);
                    popup.addSeparator();
                    popup.add(editar);
                    popup.add(eliminar);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    public void cargarCursos() {
        String searchText = txtCursoSearch.getText();
        if (searchText.isBlank()){
            cursos = cursosDAO.listar();
        }else{
            cursos = cursosDAO.listar(searchText);
        }
        mdlTableCursos.setRowCount(0);
        for(CursosDTO curso : cursos){
            mdlTableCursos.addRow(new Object[]{
                    curso.getCodigo(),
                    curso.getNombre(),
                    curso.getEstudiantes().toArray().length+" Estudiantes"
            });
        }
        tblEstudiantes.repaint();
    }
    public void cursoCrear(){
        formCursoCrear form = new formCursoCrear();
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarCursos();
            }
        });
        form.setVisible(true);
    }
    public void cursoEditar(String codigo){
        curso=cursosDAO.leer(codigo);
        if (curso==null){
            JOptionPane.showMessageDialog(null, "curso no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formCursoEditar form = new formCursoEditar(curso);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarCursos();
            }
        });
        form.setVisible(true);
    }
    public void cursoEliminar(String codigo){
        curso=cursosDAO.leer(codigo);
        if (curso==null){
            JOptionPane.showMessageDialog(null, "curso no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        formCursoEliminar form = new formCursoEliminar(curso);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarCursos();
            }
        });
        form.setVisible(true);
    }




    public static void main(String[] args) {
        formInicio dialog = new formInicio();
        dialog.setVisible(true);
    }
}
