package br.com.targettrust.view;

import br.com.targettrust.controller.ManutecaoController;

import java.util.Scanner;

public class ManutencaoView {
    private Scanner scanner = new Scanner(System.in);
    private static final String SENHA = "123";
    public void print() {
        System.out.println("Entrando em modo de manutenção");
        System.out.println("Digite a senha de manutenção");
//        String line = scanner.nextLine();
//        scanner.nextLine();
//        System.out.println(line);
//        line = line.replace("\\n", "");
//        line = line.replace("\\n\\r", "");
//        if(SENHA.equals(line.trim())) {
//            System.out.println("Senha incorreta");
//            return;
//        }
        System.out.println("Plugue o pendrive...");
        scanner.nextLine();
        ManutecaoController controller = new ManutecaoController();
        controller.iniciarManutecao();
    }
}
