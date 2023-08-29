/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXadrez;

/**
 *
 * @author 155736
 */
public class Torre extends PecaXadrez{
    
    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }
    
    
}
