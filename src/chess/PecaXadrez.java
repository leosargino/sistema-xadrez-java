package chess;

import boardgame.Peca;
import boardgame.Tabuleiro;


public abstract class PecaXadrez extends Peca {
    
    private Cor cor;

    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

}
