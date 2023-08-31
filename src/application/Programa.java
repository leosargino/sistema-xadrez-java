package application;

import boardgame.Posicao;
import chess.PartidaXadrez;
import chess.PecaXadrez;
import chess.PosicaoXadrez;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
            
            System.out.println();
            System.out.print("Destino: ");
            PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
            
            PecaXadrez pecaCapturada = partidaXadrez.excecutaMovimentoXadrez(origem, destino);
        }
    }

}
