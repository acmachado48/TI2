package exercicio2;

public class Carros {

	private String marca;
	private String cor;
	private String placa;
	private int ano;

	 public Carros() {
	        this("", "", "", -1);
	    }

	    public Carros(String marca, String cor, String placa, int ano) {
	        this.marca = marca;
	        this.cor = cor;
	        this.placa = placa;
	        this.ano = ano;
	    }

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	@Override
	public String toString() {
		return "Carros [marca=" + marca + ", cor=" + cor + ", placa=" + placa + ", ano=" + ano + "]";
	}

}
