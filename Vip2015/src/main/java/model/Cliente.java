package model;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cliente {

    @Id
    private String id;
    @Indexed(unique = true)//o proximo sera sempre unico no banco
    private String cnpj;
    private String nome;
    private String fone;

    public Cliente() {

    }

    public Cliente(String cnpj, String nome, String fone) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.fone = fone;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}