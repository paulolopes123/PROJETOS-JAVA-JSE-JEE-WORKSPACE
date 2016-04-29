/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle.modelo;

import java.util.ArrayList;

/**
 *
 * @author 8itoporcento
 * @classe Personagem
 * @objetivo Manipular propriedades comuns entre personagens
 *
 */

public class Personagem extends Desenho
{
        
    protected String nome;
    protected String reino;

    protected int forca_total;
    protected int nivel_total;

    protected int vida;
    protected int poder;
    
    protected boolean aguardando;

    protected ArrayList<Carta> cartas;
        
    public int calcularForca ()
    {
        int forca = 0;
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        {
            forca += this.cartas.get(i).getForca();
        }
        
        forca = forca / this.cartas.size();
        
        return forca;
    }
    
    public int calcularNivel ()
    {
        int nivel = 0;
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        {
            nivel += this.cartas.get(i).getNivel();
        }
        
        nivel = nivel / this.cartas.size();
        
        return nivel;
    }
    
    public void liberarCarta ()
    {
        boolean disponivel = false;
        
        int menor_nivel = MAXIMO_NIVEL;
        int index       = 0;
        
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).getNivel() <= menor_nivel )
            {
                menor_nivel = this.cartas.get(i).getNivel();
                index       = i;
            }
            
            if ( this.cartas.get(i).isDisponivel() )
            {
                disponivel = true;
                break;
            }
            
        }
        
        if ( !disponivel )
        {
            this.cartas.get(index).setDisponivel(true);
        }
        
    }
    
    public void liberarTodasCarta ()
    {
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            this.cartas.get(i).setEspera(0);
            this.cartas.get(i).setDisponivel(true);            
        }
        
    }
    
    public void diminuirEspera()
    {
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).getEspera() > 0 && this.cartas.get(i).getEspera() != 7)
            {
                this.cartas.get(i).setEspera( this.cartas.get(i).getEspera() - 1 );
            }
            
            if ( this.cartas.get(i).getEspera()== 0 )
            {
                this.cartas.get(i).setDisponivel(true);
            }
            
        }
    }
    
    public boolean emJogo ()
    {
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isEmJogo() )
            {
                return true;
            }
            
        }
        
        return false;
    }
    
    public boolean resetar ()
    {
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isEmJogo() )
            {
                this.cartas.get(i).setEmJogo(false);
            }
            
        }
        
        return false;
    }
    
    public Carta cartaEmJogo ()
    {
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isEmJogo() )
            {
                return this.cartas.get(i);
            }
            
        }
        
        return null;
    }

    public String getNome() 
    {
        return nome;
    }

    public String getReino() 
    {
        return reino;
    }

    public int getForcaTotal() {
        
        return forca_total;
    }

    public int getNivelTotal() 
    {
        return nivel_total;
    }

    public int getVida() 
    {
        return vida;
    }

    public void setVida(int forca) 
    {
        this.vida -= forca;
    }
    
    public void zeraVida() 
    {
        this.vida = 0;
    }

    public int getPoder() 
    {
        return poder;
    }

    public void setPoder(int forca) 
    {
        this.poder -= forca;
    }
    
    public void setAumentoDePoder(int poder) 
    {
        this.poder = poder;
    }

    public ArrayList<Carta> getCartas() 
    {
        return cartas;
    }
    
    public boolean isAguardando() 
    {
        return aguardando;
    }

    public void setAguardando(boolean aguardando) 
    {
        this.aguardando = aguardando;
    }

}
