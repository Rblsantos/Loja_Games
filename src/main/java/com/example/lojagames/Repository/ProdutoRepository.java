package com.example.lojagames.Repository;

import com.example.lojagames.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {  // o extends JpaRepository implementa os metodos que fazem a conversão dos dados para o banco de dados

    public List <Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome); // metodo para listar todos os produtos ignorando o case e passando o parametro

    public List<Produto> findByPrecoGreaterThanOrderByPreco(double preco);

    public List<Produto> findByPrecoLessThanOrderByPrecoDesc(double preco);



}
