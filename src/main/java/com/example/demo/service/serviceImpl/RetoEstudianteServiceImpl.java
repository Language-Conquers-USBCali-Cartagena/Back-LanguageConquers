package com.example.demo.service.serviceImpl;

import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.service.RetoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class RetoEstudianteServiceImpl implements RetoEstudianteService {
    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;
    @Override
    public int promedioRetosCompletadosEstudiantes() throws Exception {
        int promedioRetos = retoEstudianteDAO.promedioRetosCompletadosEstudiantes();
        return promedioRetos;
    }
    //TODO: IMPLEMETAR SERVICIOS BASICOS
}
