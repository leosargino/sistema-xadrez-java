package application;

import boardgame.Posicao;
import chess.PartidaXadrez;
import chess.PecaXadrez;
import chess.PosicaoXadrez;
import chess.XadrezException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> capturada = new ArrayList<>();
        

        while (!partidaXadrez.getCheckMate()) {
            try {

                UI.limpaConsole();
                UI.printPartida(partidaXadrez, capturada);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
                
                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
                UI.limpaConsole();
                UI.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.executaMovimentoXadrez(origem, destino);
                
                if(pecaCapturada != null){
                    capturada.add(pecaCapturada);
                }
                if(partidaXadrez.getPromocao() != null){
                    System.out.print("Escolha para promoção (B/C/T/D): ");
                    String type = sc.nextLine();
                    partidaXadrez.substituiPecaPromovida(type);
                }
            }catch (XadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.limpaConsole();
        UI.printPartida(partidaXadrez, capturada);
    }

}
