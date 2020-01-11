package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	
	private int quantidadeLinhas;
	private int quantidadeColunas;
	private int quantidadeMinas;
	
	
	private final List<Campo> campos = new ArrayList<>();


	public Tabuleiro(int quantidadeLinhas, int quantidadeColunas, int quantidadeMinas) {
		this.quantidadeLinhas = quantidadeLinhas;
		this.quantidadeColunas = quantidadeColunas;
		this.quantidadeMinas = quantidadeMinas;
		
		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		for (int linha = 0; linha < quantidadeLinhas; linha++) {
			for (int coluna = 0; coluna < quantidadeColunas; coluna++) {
				campos.add(new Campo(linha,coluna));
				
			}
		}
	}
	
	private void associarOsVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
		
	}

	private void sortearMinas() {
		
		long minasArmadas = 0;
		Predicate<Campo> minado = c-> c.isMinado();
		
		do {
			
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int)(Math.random()* campos.size());
			campos.get(aleatorio).minar();
			
		}while(minasArmadas < quantidadeMinas);
	}
	
	public boolean objetivoAlcançado() {
		return campos.stream().allMatch(c -> c.objetivoAlcançado());
	}
	
	public void reiniciar () {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	
	

	
	
	
}
