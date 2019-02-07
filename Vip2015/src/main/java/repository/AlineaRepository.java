package repository;

import model.Alinea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlineaRepository extends MongoRepository<Alinea, String>{
    public Alinea findByCodigo(String codigo);
}
