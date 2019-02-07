package repository;

import model.Cheque;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChequeRepository extends MongoRepository<Cheque, String>{
    
}
