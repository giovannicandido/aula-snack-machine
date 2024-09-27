package br.com.targettrust;

import br.com.targettrust.view.MainMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao = -1;
        do {
            opcao = MainMenu.print();
        } while (opcao != 0);
    }
}