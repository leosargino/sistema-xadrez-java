package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXadrez;

public class Rei extends PecaXadrez{
    
    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor()!= getCor();
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
    
    return mat;    
    }
    
}
