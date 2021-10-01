/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Javi
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    List<Noticia>findByTituloContainingOrDescripcionContaining(String titulo, String descripcion);

}
