/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import static chess.Cor.BRANCO;
import chess.pecas.Bispo;
import chess.pecas.Cavalo;
import chess.pecas.Peao;
import chess.pecas.Rainha;
import chess.pecas.Rei;
import chess.pecas.Torre;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 155736
 */
public class PartidaXadrez {
    
    
    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;
    public PecaXadrez enPassantVulneravel;
    private PecaXadrez promocao;
    
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        configuracaoInicial();
    }

    public int getTurno() {
        return turno;
    }
    
    public Cor getJogadorAtual() {
        return jogadorAtual;
    }
    
    public boolean getCheck() {
	return check;
	}
    
    public boolean getCheckMate() {
        return checkMate;
    }
    
public PecaXadrez getEnPassantVulneravel() {
    return enPassantVulneravel;
}

    public PecaXadrez getPromocao() {
        return promocao;
    }

    public PecaXadrez[][]getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
                for(int i=0; i<tabuleiro.getLinhas(); i++){
                    for(int j=0; j<tabuleiro.getColunas(); j++){
                        mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
                    }
                } return mat;
    }
    
    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.toPosicao();
        validacaoPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }
    
    public PecaXadrez executaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        validacaoPosicaoOrigem(origem);
        validacaoPosicaoDestino(origem, destino);
        Peca pecaCapturada = move(origem, destino);

        if (testeCheck(jogadorAtual)) {
            desfazMovimento(origem ,destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em xeque");
        }
        
        PecaXadrez pecaMoveu = (PecaXadrez)tabuleiro.peca(destino);
        
        promocao = null;
        if(pecaMoveu instanceof Peao){
            if((pecaMoveu.getCor() == Cor.BRANCO && destino.getLinha() == 0 || pecaMoveu.getCor() == Cor.PRETO && destino.getLinha() == 7)){
                promocao = (PecaXadrez)tabuleiro.peca(destino);
                promocao = substituiPecaPromovida("D");
            }
        }
        
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;
        
        if (testeCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        }else{
        proximoTurno();
     }
        if(pecaMoveu instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)){
            enPassantVulneravel = pecaMoveu;
        }else{
            enPassantVulneravel = null;
        }
        
        return (PecaXadrez) pecaCapturada;
  }   
    
    public PecaXadrez substituiPecaPromovida (String type){
        if(promocao == null){
            throw new IllegalStateException("Não há peça para ser promovida");
        }
        if(!type.equals("B") && !type.equals("C") && !type.equals("T") & !type.equals("D")){
            return promocao;
        }
        Posicao pos = promocao.getPosicaoXadrez().toPosicao();
        Peca p = tabuleiro.removePeca(pos);
        pecasNoTabuleiro.remove(p);
        
        PecaXadrez novaPeca = novaPeca(type, promocao.getCor());
        tabuleiro.lugarPeca(novaPeca, pos);
        pecasNoTabuleiro.add(novaPeca);
        
        return novaPeca;
    }
    
    private PecaXadrez novaPeca(String type, Cor cor){
        if(type.equals("B")) return new Bispo(tabuleiro, cor);
        if(type.equals("C")) return new Cavalo(tabuleiro, cor);
        if(type.equals("D")) return new Rainha(tabuleiro, cor); 
        return new Torre(tabuleiro, cor);
    }
    
    private Peca move (Posicao origem, Posicao destino){
        PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem);
        p.aumentaContadorMovimento();
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, destino);
        
        if(pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        // roque pequeno 
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(torre, destinoT);
            torre.aumentaContadorMovimento();
        }
        // roque grande 
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(torre, destinoT);
            torre.aumentaContadorMovimento();
        }
        
        if(p instanceof Peao){
           if(origem.getColuna() != destino.getColuna() && pecaCapturada == null){
               Posicao posicaoPeao;
               if(p.getCor() == Cor.BRANCO){
                   posicaoPeao = new Posicao (destino.getLinha()+ 1, destino.getColuna());
               }
               else{
                   posicaoPeao = new Posicao (destino.getLinha() - 1, destino.getColuna());
               }
               pecaCapturada = tabuleiro.removePeca(posicaoPeao);
               pecasCapturadas.add(pecaCapturada);
               pecasNoTabuleiro.remove(pecaCapturada);
           } 
        }
        
        return pecaCapturada;
    }
    
    private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(destino);
        p.diminuiContadorMovimento();
        tabuleiro.lugarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.lugarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
        // roque pequeno 
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPeca(torre, origemT);
            torre.diminuiContadorMovimento();
        }
        // roque grande 
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPeca(torre, origemT);
            torre.diminuiContadorMovimento();
        }
        if(p instanceof Peao){
           if(origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel){
               PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(destino);
               Posicao posicaoPeao;
               if(p.getCor() == Cor.BRANCO){
                   posicaoPeao = new Posicao (3, destino.getColuna());
               }
               else{
                   posicaoPeao = new Posicao (4, destino.getColuna());
               }
               tabuleiro.lugarPeca(peao, posicaoPeao);
           } 
        }
    }
    
    private void validacaoPosicaoOrigem(Posicao posicao){
        if(!tabuleiro.existePeca(posicao)){
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new XadrezException("A peça escolhida não é sua");
        }
        if(!tabuleiro.peca(posicao).existeMovimentoPossivel()){
            throw new XadrezException("Não há movimentos possíveis para a peça escolhida");
        }
    }
    
    private void validacaoPosicaoDestino(Posicao origem, Posicao destino){
        if(!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new XadrezException("A peça escolhida não pode ser movida para o destino");
        }
    }
    
    
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO ;
    }
    
    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não há rei " + cor + " no tabuleiro");
    }

    private boolean testeCheck(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasOponente) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean testeCheckMate(Cor cor) {
        if(!testeCheck(cor)){
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for(Peca p : list){
            boolean[][] mat = p.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if(mat[i][j]){
                        Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i,j);
                        Peca pecaCapturada = move(origem,destino);
                        boolean testeCheck = testeCheck(cor);
                        desfazMovimento(origem, destino, pecaCapturada);
                        if(!testeCheck)
                        {
                            return false;
                        }}
                    
                }
                
            }
        }
        return true;
    }
    
    
    private void novoLugarPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasNoTabuleiro.add(peca);
    }
    
    private void configuracaoInicial(){   
       
        novoLugarPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        novoLugarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        novoLugarPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
        novoLugarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        novoLugarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        novoLugarPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        novoLugarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        novoLugarPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO,this));
        novoLugarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO,this));

        novoLugarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        novoLugarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        novoLugarPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
        novoLugarPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        novoLugarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        novoLugarPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        novoLugarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        novoLugarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO,this));
        novoLugarPeca('h', 7, new Peao(tabuleiro, Cor.PRETO,this));

        
        }
}
