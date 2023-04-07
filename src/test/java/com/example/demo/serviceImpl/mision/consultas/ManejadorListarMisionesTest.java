package com.example.demo.serviceImpl.mision.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.MisionEstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import com.example.demo.serviceImpl.mision.testDataBuilder.MisionTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ManejadorListarMisionesTest {
    @Autowired
    MisionMapper misionMapper;
    @Autowired
    MisionService misionService;
    @MockBean
    MisionDAO misionDAO;
    @MockBean
    CursoDAO cursoDAO;
    @MockBean
    MisionEstudianteDAO misionEstudianteDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Deberia listar las misiones")
    void deberiaListarLasMisiones() throws Exception{
        List<MisionDTO> misionDTOList = new ArrayList<>();
        misionDTOList.add(new MisionTestDataBuilder().build());
        misionDTOList.add(new MisionTestDataBuilder().conIdMision(158L).build());
        List<Mision> misions = misionMapper.toEntityList(misionDTOList);
        Mockito.when(misionDAO.findAll()).thenReturn(misions);
        List<Mision> resp = misionService.ListarMisiones();
        assertEquals(resp, misions);
    }
}
