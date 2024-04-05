package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Carro implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String DESCRICAO_PADRAO = "Novo Carro";
    
    private String cor;
    private String placa;
    private int ano;
    private String marca;

    public Carro() {
        cor = "Branco";
        placa = "AAA-0000";
        ano = LocalDate.now().getYear();
        marca = "Sem Marca";
    }

    public Carro(String cor, String placa, int ano, String marca) {
        setCor(cor);
        setPlaca(placa);
        setAno(ano);
        setMarca(marca);
    }   

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public String toString() {
        return "Carro: " + marca + " " + ano + ", Cor: " + cor + ", Placa: " + placa;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Carro carro = (Carro) obj;
        return ano == carro.ano && 
               cor.equals(carro.cor) && 
               placa.equals(carro.placa) && 
               marca.equals(carro.marca);
    }
}