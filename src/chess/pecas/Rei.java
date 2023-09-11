package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PartidaXadrez;
import chess.PecaXadrez;

public class Rei extends PecaXadrez{
    
    private PartidaXadrez partidaXadrez;
    
    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor()!= getCor();
    }
    
    private boolean testeTorreRoque(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
    }
    
    
    @Override
    public boolean[][] movimentosPossiveis() {
    boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    
    Posicao p = new Posicao(0,0);
    
    //acima
    p.setValor(posicao.getLinha() - 1, posicao.getColuna());
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //abaixo
    p.setValor(posicao.getLinha() + 1 , posicao.getColuna());
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //esquerda
    p.setValor(posicao.getLinha() , posicao.getColuna() - 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //direita
    p.setValor(posicao.getLinha() , posicao.getColuna() + 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //no
    p.setValor(posicao.getLinha() - 1 , posicao.getColuna() - 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //ne
    p.setValor(posicao.getLinha() - 1 , posicao.getColuna() + 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
      
    //so
    p.setValor(posicao.getLinha() + 1 , posicao.getColuna() - 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //se
    p.setValor(posicao.getLinha() + 1 , posicao.getColuna() + 1);
    if(getTabuleiro().existePosicao(p)&& podeMover(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    // movimento especial Roque
    if(getContadorMovimento() == 0 && !partidaXadrez.getCheck()){
        //roque pequeno
        Posicao posT1 = new Posicao (posicao.getLinha(), posicao.getColuna() + 3);
        if(testeTorreRoque(posT1)){
            Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
            Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
            if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) ==  null) {
                mat[posicao.getLinha()][posicao.getColuna() +  2] = true;
            }
        }
        //roque grande
    Posicao posT2 = new Posicao (posicao.getLinha(), posicao.getColuna() - 4);
        if(testeTorreRoque(posT2)){
            Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
            Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
            Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
            if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) ==  null && getTabuleiro().peca(p3) ==  null) {
                mat[posicao.getLinha()][posicao.getColuna() -  2] = true;
            }
        }
    }
    
    
    return mat;    
    }
    
}
