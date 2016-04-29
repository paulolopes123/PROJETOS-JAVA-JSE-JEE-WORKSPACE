/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wizardbattle.modelo;

import javax.swing.ImageIcon;

/**
 *
 * @author 8itoporcento
 * @classe Cartas
 * @objetivo Representa uma carta do jogo
 *
 */

public class Carta extends Desenho
{
    
    private String nome;
    
    private int forca;
    private int nivel;
    
    private boolean disponivel;
    private boolean selecionada;
    private boolean em_jogo;
    
    private int espera;
    
    private final String imagem_normal = "sprites/carta.png";
    
    private TIPO_CARTAS tipo_carta;
    
    public Carta (String nome, int forca, int nivel, TIPO_CARTAS tipo_carta)
    {
        this.nome  = nome;
        this.forca = forca;
        this.nivel = nivel;
        
        this.disponivel  = true;
        this.selecionada = false;
        this.em_jogo     = false;
        
        this.espera = 0;
        
        this.tipo_carta = tipo_carta;
        
        ImageIcon carta = new ImageIcon(getClass().getResource(imagem_normal));
        setImagem(carta.getImage());
    }

    public String getNome() 
    {
        return nome;
    }

    public int getForca() 
    {
        return forca;
    }

    public int getNivel() 
    {
        return nivel;
    }
    
    public TIPO_CARTAS getTipoCarta()
    {
        return tipo_carta;
    }
    
    public boolean isDisponivel()
    {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel)
    {
        this.disponivel = disponivel;
    }
    
    public boolean isSelecionada()
    {
        return selecionada;
    }
    
    public void setSelecionada(boolean selecionada)
    {
        this.selecionada = selecionada;
    }
    
    public boolean isEmJogo()
    {
        return em_jogo;
    }
    
    public void setEmJogo(boolean em_jogo)
    {
        this.em_jogo = em_jogo;
    }
    
    public void emEspera()
    {
        this.espera = this.nivel + 1;
    }
    
    public int getEspera()
    {
        return espera;
    }
    
    public void setEspera(int espera)
    {
        this.espera = espera;
    }

}
