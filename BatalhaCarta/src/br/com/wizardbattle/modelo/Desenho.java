/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle.modelo;

import java.awt.Image;

/**
 *
 * @author 8itoporcento
 * @classe Desenho
 * @objetivo Controlar propriedades gráficas dos objetos
 *
 */

public class Desenho implements Comuns
{
    
    private Image imagem;
    
    private boolean visibilidade;
    private boolean destruicao;
    
    private int altura_desenho;
    private int largura_desenho;
    
    private int x;
    private int y;
    
    private int direcao;
    
    public Desenho()
    {
        this.visibilidade = true;
        this.destruicao   = false;        
    }

    public Image getImagem() 
    {
        return imagem;
    }

    public void setImagem(Image imagem) 
    {
        this.imagem = imagem;
    }

    public boolean isVisivel() 
    {
        return visibilidade;
    }
    
    public void setVisibilidade(boolean visibilidade) 
    {
        this.visibilidade = visibilidade;
    }

    public boolean isDestruido() 
    {
        return destruicao;
    }

    public void setDestruicao(boolean destruicao) 
    {
        this.destruicao = destruicao;
    }

    public int getAlturaDesenho() 
    {
        return altura_desenho;
    }

    public void setAlturaDesenho(int altura_desenho) 
    {
        this.altura_desenho = altura_desenho;
    }

    public int getLarguraDesenho() 
    {
        return largura_desenho;
    }

    public void setLarguraDesenho(int largura_desenho) 
    {
        this.largura_desenho = largura_desenho;
    }

    public int getX() 
    {
        return x;
    }

    public void setX(int x) 
    {
        this.x = x;
    }

    public int getY() 
    {
        return y;
    }

    public void setY(int y) 
    {
        this.y = y;
    }

    public int getDirecao() 
    {
        return direcao;
    }

    public void setDirecao(int direcao) 
    {
        this.direcao = direcao;
    }
    
}
