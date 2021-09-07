/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 *
 * @author W7
 */
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
}
