/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.CuerpoTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Javi
 */
@Repository
public interface CuerpoTecnicoRepositorio extends JpaRepository<CuerpoTecnico, String> {
    List<CuerpoTecnico> findByNombreCompletoContainingOrNacionalidadContainingOrPuestoContaining(String
    nombre, String nacionalidad, String puesto);
}
