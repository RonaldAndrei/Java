package repository;

import model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
    public Cliente findByNome(String nome);
}
