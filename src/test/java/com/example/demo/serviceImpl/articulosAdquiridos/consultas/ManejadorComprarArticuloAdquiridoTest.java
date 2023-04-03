package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.*;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.articulos.testDataBuilder.ArticuloTestDataBuilder;
import com.example.demo.serviceImpl.articulosAdquiridos.testDataBuilder.ArticulosAdquiridosTestDataBuilder;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ManejadorComprarArticuloAdquiridoTest {

    public static final String EL_ARTICULO_CON_ESE_ID_NO_EXISTE = "El articulo con ese id no existe.";
    public static final String EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE = "El estudiante con ese id no existe.";
    public static final String NO_TIENES_SUFICIENTES_MONEDAS_PARA_COMPRAR_ESTE_ARTICULO = "No tienes suficientes monedas para comprar este articulo.";
    public static final Long ID_ESTUDIANTE = 8347L;
    public static final Long ID_ARTICULO = 267367L;
    public static final Integer MONEDAS_OBTENIDAS = 340;
    public static final String NOMBRE_ESTUDIANTE = "Angela";
    public static final String NOMBRE_ARTICULO = "Martillo";
    public static final Double PRECIO = 233.4;
    public static final String USUARIO_CREADOR = "Angela Acosta";


    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;
    @MockBean
    private ArticulosDAO articulosDAO;

    @MockBean
    private AvatarDAO avatarDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @MockBean
    private GeneroDAO generoDAO;

    @MockBean
    private ProgramaDAO programaDAO;

    @MockBean
    private SemestreDAO semestreDAO;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Autowired
    EstudianteService estudianteService;

    @Autowired
    EstudianteMapper estudianteMapper;

    @Autowired
    ArticulosMapper articulosMapper;

    @Test
    @DisplayName("Deberia permitir comprar un articulo")
    void deberiaPoderComprarArticulo()throws Exception{

        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstudiante(ID_ESTUDIANTE)
                .conNombre(NOMBRE_ESTUDIANTE)
                .conFechaNacimiento(new Date(2001,07,31))
                .conMonedasObtenidas(MONEDAS_OBTENIDAS)
                .build();

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder()
                .conIdArticulo(ID_ARTICULO)
                .conNombre(NOMBRE_ARTICULO)
                .conPrecio(PRECIO)
                .build();
        //Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(estudianteDAO.findById(estudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));

        Mockito.when(avatarDAO.existsById(estudianteDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudianteDTO.getIdAvatar())).thenReturn(Optional.of(new Avatar()));

        Mockito.when(estadoDAO.existsById(estudianteDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.findById(estudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));

        Mockito.when(generoDAO.existsById(estudianteDTO.getIdGenero())).thenReturn(true);
        Mockito.when(generoDAO.findById(estudianteDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));

        Mockito.when(programaDAO.existsById(estudianteDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(programaDAO.findById(estudianteDTO.getIdPrograma())).thenReturn(Optional.of(new Programa()));

        Mockito.when(semestreDAO.existsById(estudianteDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.findById(estudianteDTO.getIdSemestre())).thenReturn(Optional.of(new Semestre()));

        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.findById(articulosDTO.getIdArticulo())).thenReturn(Optional.of(new Articulos()));

        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));

        //Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Double monedasEstudiante = Double.valueOf(MONEDAS_OBTENIDAS);
        Double precio = PRECIO;

        monedasEstudiante = monedasEstudiante-precio;
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticulos(articulosDTO.getIdArticulo())
                .conIdEstudiante(estudianteDTO.getIdEstudiante())
                .conFechaCreacion(new Date())
                .conUsuarioCreador(USUARIO_CREADOR)
                .build();


        estudianteService.actualizar(estudiante);ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        articulosAdquiridosService.registrar(articulosAdquiridos);



        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);

        Mockito.when(articulosAdquiridosDAO.findById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(Optional.of(articulosAdquiridos));

        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(true);
        //estudianteDTO.setIdEstudiante(ID_ESTUDIANTE);
        estudianteDTO.setMonedasObtenidas(monedasEstudiante.intValue());



        int monedasRestantes = articulosAdquiridosService.comprar(estudianteDTO.getIdEstudiante(), articulosDTO.getIdArticulo());


        assertEquals(monedasEstudiante, monedasRestantes);

    }
}
