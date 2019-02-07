package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repository.BancoRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = BancoRepository.class)
public class DBConfig extends AbstractMongoConfiguration{
    @Override
    protected String getDatabaseName(){
        return "vip2015";
    }
    
    @Override
    public Mongo mongo() throws Exception{
        MongoClient client = new MongoClient("localhost");
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }
}
