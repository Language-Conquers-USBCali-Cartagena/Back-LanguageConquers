package com.example.demo.service;

import java.util.List;

public interface CRUD<T> {
    T registrar(T t) throws Exception;
    T actualizar (T t) throws Exception;
    void eliminar(Long id);
    T listarPorId(Long id);
    List<T> listar();
}
