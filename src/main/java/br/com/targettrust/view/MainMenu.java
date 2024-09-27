package br.com.targettrust.view;

import java.util.Scanner;

public class MainMenu {
    public static int print() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("| Snack machine           |");
        System.out.println("| Selecione uma opção     |");
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("1. Venda de produtos      ");
        System.out.println("2. Manutenção             ");
        System.out.println("0. Sair                   ");
        System.out.println();
        int opcao = scanner.nextInt();

        VendaView vendaView = new VendaView();
        ManutencaoView manutencaoView = new ManutencaoView();

        switch (opcao) {
            case 1:
                vendaView.print();
                return 1;
            case 2:
                manutencaoView.print();
                return 1;
            case 0:
                System.out.println("Saindo do sistema");
                return 0;
            default:
                return 0;
        }
    }
}
