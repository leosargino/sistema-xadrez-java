package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXadrez;

public class Rei extends PecaXadrez{
    
    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
    boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    return mat;    
    }
    
}
