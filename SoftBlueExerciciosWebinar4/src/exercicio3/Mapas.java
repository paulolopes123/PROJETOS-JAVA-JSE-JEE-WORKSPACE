package exercicio3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Mapas {

	public static void main(String[] args) {

		Map<String, String> estados = new HashMap<>();

		estados.put("RJ", "Rio de Janeiro");
		estados.put("SP", "São Paulo");
		estados.put("MG", "Minas Gerais");
		estados.put("CE", "Ceará");
		estados.put("RO", "Rondônia");
		mostrarSiglas(estados);
		mostrarNomes(estados);
		mostrarSiglasNomes(estados);

	}

	private static void mostrarSiglas(Map<String, String> estados) {
		Set<String> siglas = estados.keySet();
		System.out.println("As Siglas são:");
		for (String sigla : siglas) {
			System.out.println(sigla);

		}

	}

	private static void mostrarNomes(Map<String, String> estados) {
		Collection<String> nomes = estados.values();
		System.out.println("\nOs Nomes são:");
		for (String nome : nomes) {
			System.out.println(nome);

		}

	}

	private static void mostrarSiglasNomes(Map<String, String> estados) {
		Set<Map.Entry<String, String>> entries = estados.entrySet();
		System.out.println("\nAs Siglas e os seus respectivos Nomes são:");
		for (Map.Entry<String, String> entry : entries) {
			{

				System.out.println(entry.getKey() + " => " + entry.getValue());
			}
		}

	}

}
