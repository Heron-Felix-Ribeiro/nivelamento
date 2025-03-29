package com.senac.controle_financeiro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ControleFinanceiroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ControleFinanceiroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int opcaoEscolhida = 0;


		while (opcaoEscolhida != 5) {
			exibirMenu();
			String textoDigitado = new Scanner(System.in).nextLine();
			opcaoEscolhida = Integer.parseInt(textoDigitado);
			String nomeUsuario;
			List<String> listaUsuario = new ArrayList<>();

			if (opcaoEscolhida == 1) {
				System.out.println("Consultando usuários: ");
				for (int i = 0; i < listaUsuario.size(); i++) {
					System.out.println("Usuario" + i + ": " + listaUsuario.get(i));
				}
			} else if (opcaoEscolhida == 2) {
				nomeUsuario = obterNomeUsuario();
				listaUsuario.add(nomeUsuario);
			} else if (opcaoEscolhida == 5) {
				System.out.println("Encerrando");
			}

		}


	}


	private static void exibirMenu() {
		System.out.println("Digite a operação desejada: ");
		System.out.println("1 - Listar");
		System.out.println("2 - Digitar novo usuario: ");
		System.out.println("5 - Sair");

	}

	private static String obterNomeUsuario() {
		System.out.println("Digite o nome do usuario: ");
		String nomeUsuario = new Scanner(System.in).nextLine();

		return nomeUsuario;
	}
}
