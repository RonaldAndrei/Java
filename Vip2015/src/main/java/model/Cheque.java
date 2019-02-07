package model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({ @CompoundIndex(name = "cheque_idx", def="{'banco':1,'agencia':1,'conta':1,'numero':1}", unique = true) })

public class Cheque {
    @Id
    private String id;
    @DBRef
    private Cliente cliente;
    @DBRef
    private Alinea alinea;
    @DBRef
    private Banco banco;
    
    private String agencia;
    private String conta;
    private String numero;
    private Double valor;
    private LocalDate emissao;
    private String pago;
    private LocalDate pagamento;
    
    public Cheque(){        
    }

    public Cheque(Cliente cliente, Alinea alinea, Banco banco, String agencia, String conta, String numero, Double valor, LocalDate emissao, String pago, LocalDate pagamento) {
        this.cliente = cliente;
        this.alinea = alinea;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.numero = numero;
        this.valor = valor;
        this.emissao = emissao;
        this.pago = pago;
        this.pagamento = pagamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Alinea getAlinea() {
        return alinea;
    }

    public void setAlinea(Alinea alinea) {
        this.alinea = alinea;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }
    
    
}
