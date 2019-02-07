
package model;


public class UF {
    private String descricaoUf;
    private double fator;
    
    public UF(String descricaoUf, double fator){
        
        this.descricaoUf = descricaoUf;
        this.fator = fator;
    }
    
    public String getDescricaoUf() {
        return descricaoUf;
    }

    public void setDescricaoUf(String descricaoUf) {
        this.descricaoUf = descricaoUf;
    }
    
    public double getFator() {
        return fator;
    }

    public void setFator(double fator) {
        this.fator = fator;
    }
    
    @Override
    public String toString(){
        return descricaoUf;
    }
}
