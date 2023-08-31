package application;

import boardgame.Posicao;
import chess.PartidaXadrez;
import chess.PecaXadrez;
import chess.PosicaoXadrez;
import chess.XadrezException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            try {

                UI.limpaConsole();
                UI.printTabuleiro(partidaXadrez.getPecas());
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.excecutaMovimentoXadrez(origem, destino);
            }catch (XadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

}
