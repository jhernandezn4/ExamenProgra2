package umg.Vistas;

import umg.Vistas.Cursos.CursosEliminar;
import umg.Vistas.Cursos.CursosCrear;
import umg.Vistas.Cursos.CursosListar;
import umg.Vistas.Cursos.CursosModificar;
import umg.Vistas.Estudiante.EstudianteCrear;
import umg.Vistas.Estudiante.EstudianteEliminar;
import umg.Vistas.Estudiante.EstudianteModificar;
import umg.Vistas.Estudiante.EstudiantesVer;
import umg.Vistas.Inscripciones.InscripcionesCrear;
import umg.Vistas.Inscripciones.InscripcionesEliminar;
import umg.Vistas.Inscripciones.InscripcionesVer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Inicio extends JFrame {
    private JPanel contentPane;
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

    public Inicio() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setTitle("Examen Programacion Anthony Hernandez");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bEstudiantsCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteCrear form = new EstudianteCrear();
                form.setVisible(true);
            }
        });
        bEstudiantesActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteModificar form = new EstudianteModificar();
                form.setVisible(true);
            }
        });
        bEstudiantesEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteEliminar form = new EstudianteEliminar();
                form.setVisible(true);
            }
        });
        bEstudiantesListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudiantesVer form = new EstudiantesVer();
                form.setVisible(true);
            }
        });

        bCursosCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosCrear form = new CursosCrear();
                form.setVisible(true);
            }
        });
        bCursosActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosModificar form = new CursosModificar();
                form.setVisible(true);
            }
        });
        bCursosEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosEliminar form = new CursosEliminar();
                form.setVisible(true);
            }
        });
        bCursosListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosListar form = new CursosListar();
                form.setVisible(true);
            }
        });
        bInscripcionesCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesCrear form = new InscripcionesCrear();
                form.setVisible(true);
            }
        });
        bInscripcionesVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesVer form = new InscripcionesVer();
                form.setVisible(true);
            }
        });
        bInscripcionesEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesEliminar form = new InscripcionesEliminar();
                form.setVisible(true);
            }
        });




        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
        toFront();

    }

    public static void main(String[] args) {
        Inicio dialog = new Inicio();

        dialog.setVisible(true);


    }
}
