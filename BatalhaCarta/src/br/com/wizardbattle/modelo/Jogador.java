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
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author 8itoporcento
 * @classe Jogador
 * @objetivo Controla ações do jogador
 *
 */

public class Jogador extends Personagem
{
    
    public Jogador (String nome, String reino, ArrayList<Carta> cartas)
    {
        this.nome   = nome;
        this.reino  = reino;
        this.cartas = cartas;
        
        this.cartas.get(0).setSelecionada(true);
        
        this.forca_total = calcularForca();
        this.nivel_total = calcularNivel();
        
        this.vida  = MAXIMA_VIDA;
        this.poder = MAXIMO_PODER;  
        
        this.setX(995);
        this.setY(720);
                
        posicionarCartas();
    }
    
    public void cartaEsquerda ()
    {
        int selecionada = -1;
        
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isSelecionada() )
            {
                selecionada = i;
                break;
            }
            
        }
        
        if ( selecionada != -1 )
        {
            this.cartas.get(selecionada).setSelecionada(false);
            
            selecionada -= 1;
            
            if ( selecionada < 0 )
            {
                selecionada = 5;
            }
            
            this.cartas.get(selecionada).setSelecionada(true);
        }
    }
    
    public void cartaDireita ()
    {
        
        int selecionada = -1;
        
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isSelecionada() )
            {
                selecionada = i;
                break;
            }
            
        }
        
        if ( selecionada != -1 )
        {
            this.cartas.get(selecionada).setSelecionada(false);
            
            selecionada += 1;
            
            if ( selecionada > 5 )
            {
                selecionada = 0;
            }
            
            this.cartas.get(selecionada).setSelecionada(true);
        }
    }
    
    public void usaCarta ()
    {
        int selecionada = -1;
        
        for ( int i = 0 ; i < this.cartas.size(); i++)
        {
            
            if ( this.cartas.get(i).isSelecionada() )
            {
                selecionada = i;
                break;
            }
            
        }
        
        if ( this.cartas.get(selecionada).isDisponivel() && selecionada != -1 )
        {
            this.cartas.get(selecionada).setEmJogo(true);
            this.setAguardando(true);
            this.cartas.get(selecionada).setDisponivel(false);

            if ( this.cartas.get(selecionada).getTipoCarta() == TIPO_CARTAS.ataque )
            {
                this.cartas.get(selecionada).emEspera();
            }
            else
            {
                this.cartas.get(selecionada).setEspera(7);
            }
        }
    }
        
    private void posicionarCartas ()
    {
        int y = 545;
        int x = 13;
        
        int fator = 0;
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        {
            fator = i + 1;
            
            this.cartas.get(i).setX(x);
            this.cartas.get(i).setY(y);
            
            x += 156;
        }
    }
    
    public void desenharJogador (Graphics2D g)
    {
        Font estilo     = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 38);
        Font estilo_med = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 22);
        Font estilo_min = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 14);

        g.setColor(Color.white);
        
        g.setFont(estilo);
        g.drawString(this.nome, this.getX(), this.getY()); 
        
        g.setFont(estilo_med);
        g.drawString(this.reino, 50, 220);
        g.drawString(this.forca_total + "/" + this.nivel_total, 1130, 720);
        
        g.setFont(estilo_min);
        g.drawString(this.vida+"", 1085, 575);
        g.drawString(this.poder+"", 1050, 630);
        
    }
    
    public void desenharStatus (Graphics2D g)
    {
        int vida  = (this.vida  * 175) / MAXIMA_VIDA;
        int poder = (this.poder * 175) / MAXIMO_PODER;
        
        g.setColor(Color.YELLOW);
        
        g.fillRect(992, 582, vida, 26);
        
        g.setColor(Color.RED);
        
        g.fillRect(992, 638, poder, 26);
        
    }
    
    public void desenharValoresCartas (Graphics2D g, JPanel tela)
    {
        String forca = "F";
        String nivel = "N";
        
        Font estilo_mini = new Font("Microsoft Sans Serif", Font.BOLD, 14);
        Font estilo_medi = new Font("Tahoma", Font.BOLD, 22);
        Font estilo_full = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 38);
        
        FontMetrics metrica_mini = tela.getFontMetrics(estilo_mini);
        FontMetrics metrica_medi = tela.getFontMetrics(estilo_medi);
        FontMetrics metrica_full = tela.getFontMetrics(estilo_full);

        g.setColor(Color.white);
        
        int x_medi = 35;
        int x_full = 58;
        
        int disponivel   = 42;
        int indisponivel = 36;
        
        int fator = 0;        
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        { 
            fator = i + 1;
            
            if ( this.cartas.get(i).isDisponivel() )
            {
                g.setColor(Color.white);
            }
            else
            {
                g.setColor(Color.yellow);
            }
            
            if ( this.cartas.get(i).isSelecionada() && this.cartas.get(i).isEmJogo() )
            {                
                g.setFont(estilo_medi);

                g.drawString(forca, 73, 319); 
                g.drawString(nivel, 73, 409); 
                
                g.drawString(this.cartas.get(i).getTipoCarta().toString().toUpperCase(), 73, 350);

                g.setFont(estilo_full);

                g.drawString(this.cartas.get(i).getForca()+"", 96, 321); 
                g.drawString(this.cartas.get(i).getNivel()+"", 96, 411); 
            }
            else
            {
                g.setFont(estilo_medi);

                g.drawString(forca, x_medi, 602); 
                g.drawString(nivel, x_medi, 692); 
                
                g.drawString(this.cartas.get(i).getTipoCarta().toString().toUpperCase(), x_medi, 660);

                g.setFont(estilo_full);

                g.drawString(this.cartas.get(i).getForca()+"", x_full, 604); 
                g.drawString(this.cartas.get(i).getNivel()+"", x_full, 694); 

            }
            
            g.setFont(estilo_mini);
            
            if ( this.cartas.get(i).isDisponivel() )
            {
                g.drawString("DISPONÍVEL", disponivel, 750); 
            }
            else
            {
                g.drawString("INDISPONÍVEL", indisponivel, 750); 
            }
            
            x_medi       += 156;
            x_full       += 156;
            disponivel   += 156;
            indisponivel += 156;
        }
    }
    
    public void desenharCartas (Graphics2D g, JPanel tela)
    {
        
        for ( int i = 0; i < this.cartas.size(); i++ )
        {            
            if ( this.cartas.get(i).isSelecionada() )
            { 
                if ( this.cartas.get(i).isEmJogo() )
                {
                    g.drawImage(this.cartas.get(i).getImagem(), 
                                51, 
                                262, 
                                tela); 
                }
                else
                {
                    g.drawImage(this.cartas.get(i).getImagem(), 
                                this.cartas.get(i).getX(), 
                                this.cartas.get(i).getY(), 
                                tela); 
                }
            }
        }
        
    }
    
    public void keyPressed (KeyEvent e)
    {
       if ( !emJogo() && !isAguardando())
       {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT)
            { cartaEsquerda(); }

            if (key == KeyEvent.VK_RIGHT)
            { cartaDireita(); }

            if (key == KeyEvent.VK_ENTER)
            { usaCarta(); }
       }
    }

    public void keyReleased (KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
        { }

        if (key == KeyEvent.VK_RIGHT)
        { }
       
        if (key == KeyEvent.VK_ENTER)
        { }
    }
}
