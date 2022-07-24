package br.com.alura.linguagem.api;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRepository extends MongoRepository <Linguagem, String>{

  Optional<Linguagem> findById(long id);
  
}

