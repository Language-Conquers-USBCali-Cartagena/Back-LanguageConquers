package com.example.demo.dao;

import com.example.demo.model.Articulos;
import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticulosDAO extends JpaRepository<Articulos, Long> {
    @Query(value = "select * from articulo where id_estado = ?1", nativeQuery = true)
    List<Articulos> findByIdEstado(Long idEstado) throws Exception;

    @Query(value = "select * from articulo where id_categoria = ?1", nativeQuery = true)
    List<Articulos> findByIdCategoria(Long idCategoria)throws Exception;

    @Query(value = "SELECT COUNT(*) FROM ARTICULO", nativeQuery = true)
    int articulosRegistrados() throws Exception;

    @Query(value = "SELECT * FROM articulo WHERE id_articulo NOT IN(SELECT id_articulo FROM articulo_adquirido WHERE id_estudiante = ?1 )",
            nativeQuery = true)
    List<Articulos> articulosNoObtenidos(Long idEstudiante) throws Exception;

    @Query(value = "SELECT a.id_articulo, a.usuario_modificador, a.imagen, a.fecha_creacion, a.fecha_modificacion, \n" +
            "a.usuario_creador, a.descripcion, a.nombre, a.nivel_valido, a.precio, a.id_categoria, a.id_estado from articulo a\n" +
            "INNER JOIN articulo_adquirido aa on (a.id_articulo = aa.id_articulo)\n" +
            "WHERE id_estudiante = ?1", nativeQuery = true)
    List<Articulos> articulosObtenidos(Long idEstudiante) throws Exception;
}
