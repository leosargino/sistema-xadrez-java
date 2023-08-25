/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import boardgame.Tabuleiro;

/**
 *
 * @author 155736
 */
public class PartidaXadrez {
    
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
    }
    
    public PecaXadrez[][]getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
                for(int i=0; i<tabuleiro.getLinhas(); i++){
                    for(int j=0; j<tabuleiro.getColunas(); j++){
                        mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
                    }
                } return mat;
    }
    
}
