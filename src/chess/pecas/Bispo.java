/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXadrez;

/**
 *
 * @author 155736
 */
public class Bispo extends PecaXadrez{
    
    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "B";
    }
    
        @Override
    public boolean[][] movimentosPossiveis() {
    boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    
    Posicao p = new Posicao(0,0);
    
    //no
    p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
    while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValor(p.getLinha() - 1, p.getColuna() - 1);
    }
    if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //ne
     p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
    while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValor(p.getLinha() - 1, p.getColuna() + 1);
    }
    if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //se
     p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
    while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
        mat[p.getLinha()][p.getColuna()] = true;
        p.setValor(p.getLinha() + 1, p.getColuna() + 1);
    }
    if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    //so
    p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
    while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
        mat[p.getLinha()][p.getColuna()] = true;
          p.setValor(p.getLinha() + 1, p.getColuna() - 1);
    }
    if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
        mat[p.getLinha()][p.getColuna()] = true;
    }
    
    return mat;    
    }
    
    
}
