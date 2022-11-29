package com.example.lojagames.Repository;

import com.example.lojagames.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {

    public List <Categoria>  findAllByGeneroContainingIgnoreCase(@Param("genero") String genero);
}
