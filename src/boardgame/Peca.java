
package boardgame;

public abstract class Peca {
    
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    public abstract boolean[][] movimentosPossiveis();
            
    public boolean possivelMovimento(Posicao posicao){
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }
    
    public boolean existeMovimentoPossivel(){
        boolean[][] mat = movimentosPossiveis();
        for (boolean[] mat1 : mat) {
            for (int j = 0; j < mat.length; j++) {
                if (mat1[j]) {
                    return true;
                }
            }
        }
    return false;
 }
    
}
