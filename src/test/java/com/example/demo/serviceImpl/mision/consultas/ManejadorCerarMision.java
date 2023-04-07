package com.example.demo.serviceImpl.mision.consultas;

import com.example.demo.dao.MisionDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.service.MisionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ManejadorCerarMision {

    @Autowired
    MisionMapper misionMapper;
    @Autowired
    MisionService misionService;
    @MockBean
    MisionDAO misionDAO;

    @Test
    @DisplayName("Deberia crear la mision")
    void testDeberiaCrearMision() throws Exception{

    }
}
