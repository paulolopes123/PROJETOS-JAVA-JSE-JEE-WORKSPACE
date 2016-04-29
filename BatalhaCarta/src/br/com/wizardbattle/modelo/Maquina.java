/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle.modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author 8itoporcento
 * @classe Maquina 
 * @objetivo
 *
 */

public class Maquina extends Personagem
{
    
    public Maquina (String nome, String reino, ArrayList<Carta> cartas)
    {
        this.nome   = nome;
        this.reino  = reino;
        this.cartas = cartas;
        
        this.cartas.get(0).setSelecionada(true);
        
        this.forca_total = calcularForca();
        this.nivel_total = calcularNivel();
        
        this.vida  = MAXIMA_VIDA;
        this.poder = MAXIMO_PODER;  
        
        this.setX(1068);
        this.setY(45);
        
    }
    
    public void primeiraJogada ()
    {
        
        for ( int i = 0 ; i < this.cartas.size(); i++ )
        {
            
            if ( this.cartas.get(i).getNivel() == this.nivel_total
                    && this.cartas.get(i).isDisponivel()
                    && this.cartas.get(i).getTipoCarta() != TIPO_CARTAS.defesa)
            {
                usaCarta(i);                
                return;
            }
            
        }
        
        int nivel = MAXIMO_NIVEL;
        int selecionada = -1;
        
        for ( int i = 0 ; i < this.cartas.size(); i++ )
        {
            
            if ( this.cartas.get(i).getNivel() < nivel
                    && this.cartas.get(i).isDisponivel()
                    && this.cartas.get(i).getTipoCarta() != TIPO_CARTAS.defesa)
            {
                nivel = this.cartas.get(i).getNivel();
                selecionada = i;
            }
            
        }
        
        if ( selecionada != -1)
        {
            usaCarta(selecionada);   
            return;
        }
        
        nivel = 0;
        selecionada = -1;
        
        for ( int i = 0 ; i < this.cartas.size(); i++ )
        {
            
            if ( this.cartas.get(i).getNivel() > nivel
                    && this.cartas.get(i).isDisponivel()
                    && this.cartas.get(i).getTipoCarta() != TIPO_CARTAS.defesa)
            {
                nivel = this.cartas.get(i).getNivel();
                selecionada = i;
            }
            
        }
        
        if ( selecionada != -1)
        {
            usaCarta(selecionada);
            return;
        }
        
        for ( int i = 0 ; i < this.cartas.size(); i++ )
        {
            
            if ( this.cartas.get(i).isDisponivel() && this.cartas.get(i).getTipoCarta() != TIPO_CARTAS.defesa)
            {
                usaCarta(i);                
                return;
            }
            
        }
        
    }
    
    public void atuar (Carta jogador)
    {
        
        if (jogador.getTipoCarta() == TIPO_CARTAS.ataque)
        {
            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getNivel() > jogador.getNivel() && this.cartas.get(i).isDisponivel() )
                {
                    usaCarta(i);
                    return;
                }

            }

            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getNivel() == jogador.getNivel() && this.cartas.get(i).isDisponivel() )
                {
                    usaCarta(i);
                    return;
                }

            }

            int nivel    = -1;
            int escolher = -1;

            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.defesa && this.cartas.get(i).isDisponivel() )
                {
                    if ( this.cartas.get(i).getNivel() > nivel)
                    {
                        nivel = this.cartas.get(i).getNivel();
                        escolher = i;
                    }
                }

            }

            if ( nivel != -1 && escolher != -1 )
            {
                usaCarta(escolher);
                return;
            }

            nivel    = -1;
            escolher = -1;

            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.ataque && this.cartas.get(i).isDisponivel() )
                {
                    if ( this.cartas.get(i).getNivel() > nivel)
                    {
                        nivel = this.cartas.get(i).getNivel();
                        escolher = i;
                    }
                }

            }

            if ( nivel != -1 && escolher != -1 )
            {
                usaCarta(escolher);
                return;
            }

            nivel    = 6;
            escolher = 6;

            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.ataque && this.cartas.get(i).isDisponivel() )
                {
                    if ( this.cartas.get(i).getNivel() < nivel)
                    {
                        nivel = this.cartas.get(i).getNivel();
                        escolher = i;
                    }
                }

            }

            if ( nivel != 6 && escolher != 6 )
            {
                usaCarta(escolher);
            }
        }
        else
        {
            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.ataque 
                        && this.cartas.get(i).getNivel() > jogador.getNivel() 
                        && this.cartas.get(i).isDisponivel() )
                {
                    usaCarta(i);
                    return;
                }

            }
            
            int nivel    = 6;
            int escolher = 6;

            for ( int i = 0 ; i < this.cartas.size(); i++ )
            {

                if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.ataque && this.cartas.get(i).isDisponivel() )
                {
                    if ( this.cartas.get(i).getNivel() < nivel)
                    {
                        nivel = this.cartas.get(i).getNivel();
                        escolher = i;
                    }
                }

            }

            if ( nivel != 6 && escolher != 6 )
            {
                usaCarta(escolher);
            }
        }
    }
    
    public void usaCarta(int i)
    {
        if ( this.cartas.get(i).isDisponivel() && i != -1 )
        {
            this.cartas.get(i).setEmJogo(true);
            this.setAguardando(true);
            this.cartas.get(i).setDisponivel(false);

            if ( this.cartas.get(i).getTipoCarta() == TIPO_CARTAS.ataque )
            {
                this.cartas.get(i).emEspera();
            }
            else
            {
                this.cartas.get(i).setEspera(7);
            }
        }
    }
    
    public void desenharJogador (Graphics2D g)
    {
        Font estilo     = new Font("Microsoft Sans Serif", Font.LAYOUT_RIGHT_TO_LEFT, 38);
        Font estilo_med = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 22);
         Font estilo_min = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 14);

        g.setColor(Color.white);
        
        g.setFont(estilo);
        g.drawString(this.nome, this.getX(), this.getY()); 
        
        g.setFont(estilo_med);
        g.drawString(this.reino, 1018, 220);
        g.drawString(this.forca_total + "/" + this.nivel_total, 1018, 45);
        
        g.setFont(estilo_min);
        g.drawString(this.vida+"", 570, 120);
    }
    
    public void desenharStatus (Graphics2D g)
    {
        int vida  = (this.vida  * 175) / MAXIMA_VIDA;
        
        g.setColor(Color.YELLOW);
        
        g.fillRect(481, 129, vida, 26);
        
    }
    
    public void desenharCarta (Graphics2D g, JPanel tela)
    {
        for ( int i = 0; i < this.cartas.size(); i++ )
        {
            if ( this.cartas.get(i).isEmJogo() )
            { 
                g.drawImage(this.cartas.get(i).getImagem(), 
                                    1022, 
                                    262, 
                                    tela);  
            }
            
        }
    }
    
    public void desenharValoresCarta (Graphics2D g, JPanel tela)
    {
        String forca = "F";
        String nivel = "N";
        
        Font estilo_medi = new Font("Tahoma", Font.BOLD, 22);
        Font estilo_full = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 38);
        
        FontMetrics metrica_medi = tela.getFontMetrics(estilo_medi);
        FontMetrics metrica_full = tela.getFontMetrics(estilo_full);

        g.setColor(Color.white);
        
        int fator = 0;        
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        { 
            fator = i + 1;
            
            if ( this.cartas.get(i).isEmJogo() )
            {                
                g.setFont(estilo_medi);

                g.drawString(forca, 1047, 319); 
                g.drawString(nivel, 1047, 409); 
                g.drawString(this.cartas.get(i).getTipoCarta().toString().toUpperCase(), 1047, 350);

                g.setFont(estilo_full);

                g.drawString(this.cartas.get(i).getForca()+"", 1067, 321); 
                g.drawString(this.cartas.get(i).getNivel()+"", 1067, 411);
                
            }
            
        }
    }
    
}
