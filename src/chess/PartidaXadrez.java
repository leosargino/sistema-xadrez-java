/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import static chess.Cor.BRANCO;
import chess.pecas.Rei;
import chess.pecas.Torre;

/**
 *
 * @author 155736
 */
public class PartidaXadrez {
    
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        configuracaoInicial();
    }
    
    public PecaXadrez[][]getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
                for(int i=0; i<tabuleiro.getLinhas(); i++){
                    for(int j=0; j<tabuleiro.getColunas(); j++){
                        mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
                    }
                } return mat;
    }
    
    private void novoLugarPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
    }
    
    private void configuracaoInicial(){   
       
        novoLugarPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        novoLugarPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
        
        }
}
