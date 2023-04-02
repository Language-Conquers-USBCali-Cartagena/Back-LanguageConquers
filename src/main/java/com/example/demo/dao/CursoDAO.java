package com.example.demo.dao;

import com.example.demo.model.Curso;
import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoDAO extends JpaRepository<Curso,Long> {

    @Query(value = "select * from curso where id_estado = ?1", nativeQuery = true)
    List<Curso> findByIdEstado(Long idEstado) throws Exception;

    @Query(value = "select * from curso where id_profesor = ?1", nativeQuery = true)
    List<Curso> findByIdProfesor(Long idProfesor) throws Exception;

    @Query(value = "select c.id_curso, c.cantidad_estudiantes, c.fecha_creacion, c.fecha_modificacion, c.fin_curso, c.inicio_curso, c.nombre, c.password, c.progreso, c.usuario_creador, c.usuario_modificador,\n" +
            "\t\t\tc.id_estado, c.id_profesor\n" +
            "from curso c\n" +
            "inner join curso_estudiante ce on (c.id_curso = ce.id_curso)\n" +
            "inner join estudiante e on (ce.id_estudiante = e.id_estudiante)\n" +
            "where e.correo = 'juancaro2010@hotmail.com'", nativeQuery = true)
    List<Curso> findByCorreoEstudiante(String correoEstudiante) throws Exception;

}
