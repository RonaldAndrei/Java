
package model;


public class UF {
    private String descricaoUf;
    
    public UF(String descricaoUf){
        
        this.descricaoUf = descricaoUf;
    }
    
    public String getDescricaoUf() {
        return descricaoUf;
    }

    public void setDescricaoUf(String descricaoUf) {
        this.descricaoUf = descricaoUf;
    }
    
    @Override
    public String toString(){
        return descricaoUf;
    }
}
