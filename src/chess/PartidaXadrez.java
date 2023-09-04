/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import boardgame.Peca;
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
    
    public boolean [][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.toPosicao();
        validacaoPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }
    
    public PecaXadrez excecutaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        validacaoPosicaoOrigem(origem);
        validacaoPosicaoDestino(origem,destino);
        Peca pecaCapturada = move(origem,destino);
        return (PecaXadrez) pecaCapturada;
    }
    
    private Peca move (Posicao origem, Posicao destino){
        Peca p = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, destino);
        return pecaCapturada;
    }
    
    private void validacaoPosicaoOrigem(Posicao posicao){
        if(!tabuleiro.existePeca(posicao)){
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if(!tabuleiro.peca(posicao).existeMovimentoPossivel()){
            throw new XadrezException("Não há movimentos possíveis para a peça escolhida");
        }
    }
    
    public void validacaoPosicaoDestino(Posicao origem, Posicao destino){
        if(!tabuleiro.peca(origem).possivelMovimento(destino)){
            throw new XadrezException("A peça escolhida não pode ser movida para o destino");
        }
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
