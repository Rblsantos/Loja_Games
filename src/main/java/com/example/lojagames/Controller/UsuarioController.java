package com.example.lojagames.Controller;

import com.example.lojagames.Model.Usuario;
import com.example.lojagames.Model.UsuarioLogin;
import com.example.lojagames.Repository.UsuarioRepository;

import com.example.lojagames.Service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
 @RestController
 @RequestMapping("/usuarios")
 @CrossOrigin(origins = "*", allowedHeaders = "*")
    public class UsuarioController {

        @Autowired
        private UsuarioServices usuarioService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @PostMapping("/cadastrar")
        public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
            return usuarioService.cadastrarUsuario(usuario)
                    .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                    .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        }

        @PutMapping("/atualizar")
        public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
            return usuarioService.atualizarUsuario(usuario)
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        @PostMapping("/logar")
        public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
            return usuarioService.autenticarUsuario(usuarioLogin)
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        @GetMapping("/listartodos")
        public ResponseEntity<List<Usuario>> listarTodos() {
            return ResponseEntity.ok(usuarioRepository.findAll());
        }

        @GetMapping("buscarporid/{id}")
        public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
            return usuarioRepository.findById(id)
                    .map(resposta -> ResponseEntity.ok(resposta))
                    .orElse(ResponseEntity.notFound().build());
        }
    }

