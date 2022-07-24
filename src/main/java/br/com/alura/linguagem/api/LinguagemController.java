package br.com.alura.linguagem.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

 @Autowired
 private LinguagemRepository repositorio;
  

  @GetMapping("/linguagens")
    public List <Linguagem> obterLinguagem(){
      List <Linguagem> linguagens = repositorio.findAll();
      return linguagens;
    }

  @GetMapping("/linguagens/{id}")
    public  ResponseEntity<Optional<Linguagem>> obterLinguagemId(@PathVariable String id){
     Optional<Linguagem> linguagens = repositorio.findById(id);
      return ResponseEntity.status(HttpStatus.OK).body(linguagens);
      
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
      Linguagem linguagemSalva = repositorio.save(linguagem);
      return   ResponseEntity.status(HttpStatus.CREATED).body(linguagemSalva);
    }

    @PutMapping("/linguagens/{id}")
    public Linguagem editarLinguagem(@RequestBody Linguagem linguagens, @PathVariable String id){
     Linguagem linguagemAtulizada = repositorio.findById(id).get();
     BeanUtils.copyProperties(linguagens, linguagemAtulizada, id);      
    
     return repositorio.save(linguagemAtulizada);
    }


    @DeleteMapping("/linguagens/{id}")
      public ResponseEntity<String> deletarLinguagem(@PathVariable String id){
          repositorio.deleteById(id);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Excluído");
      }

}
