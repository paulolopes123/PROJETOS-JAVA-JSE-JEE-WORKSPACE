package controle;

import dominio.ListaDupEncCirc;

public class Programa {

	public static void main(String[] args) {
		
		//Criar o objeto ListaDupEncCirc;
		ListaDupEncCirc lista = new ListaDupEncCirc();
		lista.insereParticipante("paulo");
		lista.insereParticipante("lopes");
		lista.insereParticipante("souza");
		lista.listar();
		/* Enquanto for desejo do usu�rio, solicitar um novo participante (nome)
			 1-Crio um objeto Pessoa a cada novo participante
			 2-Insiro o novo objeto em ListaDupEncCirc 
		*/
		
		/*
		 Enquanto n�o existir um vencedor:
		  1 - solicitar um k v�lido (validar a entrada de dados)
		  2 - remover o k-�simo n�
		  	OBS: N�o esquecer de testar o retorno do m�todo
		*/
		
	}

}
