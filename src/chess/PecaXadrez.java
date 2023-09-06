package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;


public abstract class PecaXadrez extends Peca {
    
    private Cor cor;
    private int contadorMovimento;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
        this.contadorMovimento = 0;
    }

    public Cor getCor() {
        return cor;
    }

    public int getContadorMovimento() {
        return contadorMovimento;
    }
    
    public void aumentaContadorMovimento(){
        contadorMovimento++;
    }
    
    public void diminuiContadorMovimento(){
        contadorMovimento--;
    }
    
    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.fromPosicao(posicao);
    }
    
    protected boolean existePecaOponente(Posicao posicao){
        PecaXadrez p =(PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }

}
