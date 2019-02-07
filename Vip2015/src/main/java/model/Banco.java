package model;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Banco {
    @Id
    private String id;
    @Indexed(unique = true)//o proximo sera sempre unico no banco
    private String codigo;
    private String nome;
    
    public Banco(){
        
    }

    public Banco(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
