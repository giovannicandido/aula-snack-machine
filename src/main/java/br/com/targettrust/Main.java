package br.com.targettrust;

import br.com.targettrust.controller.ProdutoController;
import br.com.targettrust.model.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoController produtoController = new ProdutoController();
        System.out.println("===============================");
        System.out.println("| Qual opção deseja executar? |");
        System.out.println("| 1. Carregar maquina         |");
        System.out.println("| 2. Ver estoque atual        |");
        System.out.println("| 3. Comprar                  |");
        System.out.println("| 4. Sair                     |");
        System.out.println("===============================");

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                produtoController.refill();
                break;
            case 2:
                List<Produto> produtos = produtoController.listAvailable();
                System.out.println(produtos);
                break;
            case 3:
                System.out.println("Qual a forma de pagamento?");
                scanner.nextLine();
                break;
            default:
        }

    }
}