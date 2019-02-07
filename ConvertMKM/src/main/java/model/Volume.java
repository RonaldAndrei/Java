/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ronald
 */
public class Volume {
    
    private int idv;
    private String descricaoDevol;
    private String siglaDevol;
    private String descricaoParavol;
    private String siglaParavol;
    private double fatorvol;
    
    public Volume(String descricaoDevol, String siglaDevol, String descricaoParavol,String siglaParavol, double fatorvol){
            
            this.descricaoDevol = descricaoDevol;
            this.siglaDevol = siglaDevol;
            this.descricaoParavol = descricaoParavol;
            this.siglaParavol = siglaParavol;
            this.fatorvol = fatorvol;
            
    }
    public int getIdv() {
        return idv;
    }

    public void setId(int idv) {
        this.idv = idv;
    }

    public String getDescricaoDevol() {
        return descricaoDevol;
    }

    public void setDescricaoDevol(String descricaoDevol) {
        this.descricaoDevol = descricaoDevol;
    }

    public String getSiglaDevol() {
        return siglaDevol;
    }

    public void setSiglaDevol(String siglaDevol) {
        this.siglaDevol = siglaDevol;
    }

    public String getDescricaoParavol() {
        return descricaoParavol;
    }

    public void setDescricaoParavol(String descricaoParavol) {
        this.descricaoParavol = descricaoParavol;
    }

    public String getSiglaParavol() {
        return siglaParavol;
    }

    public void setSiglaParavol(String siglaParavol) {
        this.siglaParavol = siglaParavol;
    }

    public double getFatorvol() {
        return fatorvol;
    }

    public void setFatorvol(double fatorvol) {
        this.fatorvol = fatorvol;
    }
            
    @Override
    public String toString() {
        return descricaoDevol + " - " + descricaoParavol;
    }    

}
