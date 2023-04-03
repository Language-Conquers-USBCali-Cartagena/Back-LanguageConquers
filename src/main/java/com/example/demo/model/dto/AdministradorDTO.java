package com.example.demo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO implements Serializable {
    private static final long serialVersionUID = -1275528017512034379L;
    private Long idAdmin;
    private String nombre;
    private String correo;
}
