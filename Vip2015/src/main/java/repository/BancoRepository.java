package repository;

import model.Banco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BancoRepository extends MongoRepository<Banco, String>{
    public Banco findByNome(String nome);
}
