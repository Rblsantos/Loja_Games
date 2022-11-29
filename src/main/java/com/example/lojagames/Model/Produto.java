package com.example.lojagames.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "tb_produto")
public class Produto {
    @ManyToOne
@JsonIgnoreProperties ("produto")
private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }




    @Id // define a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // define o auto increment
    @JsonIgnoreProperties ("produto")

    private Long id ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @NotBlank
    @Size( min = 3, max = 50, message = "O nome do jogo deve ter no mínimo 3 e no máximo 50 caracteres!") // definição do tamanho do texto inserido
    public String nome ;

    public String getNome() {
        return nome ;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    public double preco;


    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }


}
