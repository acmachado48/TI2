package exercicio2;

import java.util.Scanner;

import exercicio2.Carros;
import exercicio2.DAO2;


public class Principal2 {
	
    private static DAO2 dao2 = new DAO2();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dao2.conectar();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1) Inserir Carro");
            System.out.println("2) Listar Carros de 2015");
            System.out.println("3) Atualizar Carro");
            System.out.println("4) Excluir Carro");
            System.out.println("5) Listar Todos os Carros");
            System.out.println("6) Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    inserirCarro();
                    break;
                case 2:
                    listarCarros2015();
                    break;
                case 3:
                    atualizarCarro();
                    break;
                case 4:
                    excluirCarro();
                    break;
                case 5:
                    listarTodosCarros();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        dao2.close();
    }

    private static void listarTodosCarros() {
        Carros[] carross = dao2.getCarross();
        if (carross != null && carross.length > 0) {
            System.out.println("Lista de todos os carros:");
            for (Carros carro : carross) {
                System.out.println(carro.toString());
            }
        } else {
            System.out.println("Nenhum carro encontrado.");
        }
    }


    private static void excluirCarro() {
        System.out.print("Digite o ano do carro a ser excluído: ");
        int ano = scanner.nextInt();
        if (dao2.excluirCarros(ano)) {
            System.out.println("Carro excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir o carro.");
        }
    }

    private static void atualizarCarro() {
        System.out.println("Digite os novos detalhes do carro:");
        System.out.print("Marca: ");
        String marca = scanner.next();
        System.out.print("Cor: ");
        String cor = scanner.next();
        System.out.print("Placa: ");
        String placa = scanner.next();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();

        Carros carro = new Carros(marca, cor, placa, ano);
        if (dao2.atualizarCarros(carro)) {
            System.out.println("Carro atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o carro.");
        }
    }

    private static void listarCarros2015() {
        Carros[] carross2015 = dao2.getCarrossBefore2015();
        System.out.println("Lista de carros fabricados antes de 2015:");
        for (Carros carro : carross2015) {
            System.out.println(carro.toString());
        }
    }

    private static void inserirCarro() {
        System.out.println("Digite os detalhes do novo carro:");
        System.out.print("Marca: ");
        String marca = scanner.next();
        System.out.print("Cor: ");
        String cor = scanner.next();
        System.out.print("Placa: ");
        String placa = scanner.next();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();

        Carros carro = new Carros(marca, cor, placa, ano);
        if (dao2.inserirCarros(carro)) {
            System.out.println("Carro inserido com sucesso!");
        } else {
            System.out.println("Falha ao inserir o carro.");
        }
    }
}
