package com.example.lojagames.Controller;


import com.example.lojagames.Model.Produto;
import com.example.lojagames.Repository.ProdutoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {



    @Autowired //Injeção de dependência que gera o construtor
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() { // ResponseEntity = Resposta que deve ser feita <List<Produto>> Cria uma lista com os produtos
        return ResponseEntity.ok(produtoRepository.findAll());
    }


    @GetMapping("/produto/{nome}") // ás chaves definem que será um valor digitado pelo usuário
    public ResponseEntity<List<Produto>> getNome(@PathVariable String nome) { // ResponseEntity = Resposta que deve ser feita <List<Produto>> Cria uma lista com os produtos
        if (getNome(nome).equals(nome)) {
            return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    public ResponseEntity <Produto> getProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity <Produto> updateProduto(@Valid @RequestBody Produto produto) {
        if(produto.getId() == null) { //aqui ele verifica se o produto digitado é encontrado se não for retorna um notfound
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaId (@PathVariable Long id) {
        Optional<Produto> buscaId = produtoRepository.findById(id);
            return buscaId
                    .map(resposta -> ResponseEntity.ok(resposta))
                    .orElse(ResponseEntity.notFound().build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto (@PathVariable Long id){
        try {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(204).build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/maior/{preco}")
    public ResponseEntity <List<Produto>> listarProdutoMaior (@PathVariable double preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));

    }
    @GetMapping("/menor/{preco}")
    public ResponseEntity <List<Produto>> listarProdutoMenor (@PathVariable double preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));

    }





}
