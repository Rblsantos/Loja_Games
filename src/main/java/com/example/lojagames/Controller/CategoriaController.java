package com.example.lojagames.Controller;


import com.example.lojagames.Model.Categoria;
import com.example.lojagames.Repository.CategoriaRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {


    @Autowired //Injeção de dependência que gera o construtor
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() { // ResponseEntity = Resposta que deve ser feita <List<Produto>> Cria uma lista com as categorias
        return ResponseEntity.ok(categoriaRepository.findAll());
    }


    @GetMapping("/categoria/{genero}")
    public ResponseEntity<List<Categoria>> getGenero(@PathVariable String genero) { // ResponseEntity = Resposta que deve ser feita <List<Categoria>> Cria uma lista com as categorias definidas
        if (getGenero(genero).equals(genero)) {
            return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    public ResponseEntity <Categoria> getCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity <Categoria> updateCategoria(@Valid @RequestBody Categoria categoria) {
        if(categoria.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscaId (@PathVariable Long id) {
        Optional<Categoria> buscaId = categoriaRepository.findById(id);
        return buscaId
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deleteCategoria (@PathVariable Long id){
        try {
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(204).build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }

}
