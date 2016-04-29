/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle.visao;

// Importações necessárias para a classe
import br.com.wizardbattle.modelo.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 *
 * @author 8itoporcento
 * @classe Tela 
 * @objetivo
 *
 */

public class Tela extends JPanel implements Runnable, Comuns
{
    
    private Dimension dimensao;

    private boolean esta_jogando = true;
    private boolean fimdejogo    = false;

    private final String background_imagem = "bg.png";
    private ImageIcon background;
    
    private Jogador jogador;
    private Maquina maquina;
    
    private Thread animador;
    
    private boolean resultado = false;
    private String resultado_etapa = "";
    
    private int ultimo = 0;

    public Tela ()
    {
        addKeyListener(new TAdapter());
        
        setFocusable(true);
        
        this.dimensao = new Dimension(LARGURA, ALTURA);
        
        background = new ImageIcon(getClass().getResource(background_imagem));
        
        initJogo();
    }
    
    public void initJogo ()
    {
        Random gerar = new Random();
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        
        cartas.add(new Carta("Patron", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.defesa));
        cartas.add(new Carta("Forgus Folis", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.defesa));
        cartas.add(new Carta("Geatus Stronen ", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas.add(new Carta("Firenzo", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas.add(new Carta("Azterobawer", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas.add(new Carta("Maximum Gladius", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        
        jogador = new Jogador("Kalash", "Reino de Gelo", cartas);
        jogador.setAguardando(false);
        
        ArrayList<Carta> cartas_maquina = new ArrayList<Carta>();
        
        cartas_maquina.add(new Carta("Lebutus", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.defesa));
        cartas_maquina.add(new Carta("Pardion Getus", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.defesa));
        cartas_maquina.add(new Carta("Griatus Fonds", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas_maquina.add(new Carta("Incantatum Fairon", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas_maquina.add(new Carta("Asgaptus Solen", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        cartas_maquina.add(new Carta("Motefus Gotsfai", gerar.nextInt(99)+1, gerar.nextInt(4)+1, TIPO_CARTAS.ataque));
        
        maquina = new Maquina("Tabov", "Reino de Fogo", cartas_maquina);
        maquina.setAguardando(true);
                
        if (animador == null || !esta_jogando)
        {
            animador = new Thread(this);
            animador.start();
        }
    }
    
    @Override
    public void addNotify()
    {
        super.addNotify();
        initJogo();
    }
    
    public void desenharStatus (Graphics2D g)
    {
        Font estilo     = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 58);

        g.setColor(Color.white);
        
        g.setFont(estilo);
        
        FontMetrics metrica = this.getFontMetrics(estilo);
        
        if (resultado)
        {
            g.drawString(resultado_etapa, (LARGURA - metrica.stringWidth(resultado_etapa))/2, ALTURA / 2);
            return;
        }
        
        if (jogador.isAguardando())
        {
            resultado_etapa = "Aguarde a sua vez!";
            g.drawString("Aguarde a sua vez!", (LARGURA - metrica.stringWidth(resultado_etapa))/2, ALTURA / 2);
        }
        else
        {
            resultado_etapa = "Aguarde a sua vez!";
            g.drawString("Sua vez de jogar!", (LARGURA - metrica.stringWidth(resultado_etapa))/2, ALTURA / 2);
        }
        
    }
    
    public void desenharFimDeJogo (Graphics2D g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, LARGURA, ALTURA);
        
        Font estilo = new Font("Microsoft Sans Serif", Font.CENTER_BASELINE, 38);
        
        FontMetrics metrica = this.getFontMetrics(estilo);
        
        g.setColor(Color.BLACK);
        
        g.setFont(estilo);
        
        
        
        if (fimdejogo)
        {
            g.drawString("Você perdeu :(", (LARGURA - metrica.stringWidth("Você perdeu :("))/2, ALTURA / 2); 
        }
        else
        {
            g.drawString("Você ganhou :D", (LARGURA - metrica.stringWidth("Você ganhou :D"))/2, ALTURA / 2);
        }
    }
    
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (esta_jogando)
        {
            g2.drawImage(background.getImage(), 0, 0, this);
            
            maquina.desenharCarta(g2, this);
            jogador.desenharCartas(g2, this);
            jogador.desenharJogador(g2);
            jogador.desenharValoresCartas(g2, this);
            jogador.desenharStatus(g2);
            
            maquina.desenharJogador(g2);
            maquina.desenharStatus(g2);
            maquina.desenharValoresCarta(g2, this);
            
            desenharStatus(g2);
        }
        else
        {
            desenharFimDeJogo(g2);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    @Override
    public void run ()
    {
        long antesTempo, tempoDiferente, sleep;
        antesTempo = System.currentTimeMillis();
        
        while (esta_jogando)
        {
            repaint();
            
            ciclo();
            
            if ( resultado )
            {
                try
                {
                    Thread.sleep(2500);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Erro de interrupção!");
                }
                
                resultado = false;
            }
                        
            tempoDiferente = System.currentTimeMillis() - antesTempo;
            sleep = delay - tempoDiferente;

            if (sleep < 0)
            { sleep = 2; }

            try
            {
                Thread.sleep(sleep);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }

            antesTempo = System.currentTimeMillis();
            
        }
        
        repaint();
    }
    
    public void ciclo ()
    {
        jogador.liberarCarta();
        maquina.liberarCarta();
                
        if ( jogador.emJogo() && jogador.isAguardando() && !maquina.emJogo())
        {
            try
            {
                Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }
            
            maquina.atuar(jogador.cartaEmJogo());
            
            jogador.setAguardando(false);
            
        }
        else if ( !jogador.emJogo() && jogador.isAguardando() && !maquina.isAguardando() && !maquina.emJogo())
        {
            try
            {
                Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }
            
            maquina.primeiraJogada();
            
            jogador.setAguardando(false);
            
        }
        
        if ( jogador.emJogo() && maquina.emJogo() )
        {
            repaint();
            
            resultado       = true;
            resultado_etapa = jogador.cartaEmJogo().getNome();
            
            try
            {
                Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }
            
            Carta carta_jogador = jogador.cartaEmJogo();
            Carta carta_maquina = maquina.cartaEmJogo();
            
            int forca;
            
            jogador.setPoder(carta_jogador.getForca());
            maquina.setPoder(carta_maquina.getForca());
            
            if ( carta_jogador.getTipoCarta() == TIPO_CARTAS.ataque 
                    && carta_maquina.getTipoCarta() == TIPO_CARTAS.ataque)
            {
                
                if ( carta_jogador.getNivel() > carta_maquina.getNivel() )
                {
                    forca = ((carta_jogador.getNivel() - 1) * 100) + carta_jogador.getForca();
                    
                    maquina.setVida(forca);
                    
                    forca = ((carta_maquina.getNivel() - 1) * 100) + 
                            ( ( carta_maquina.getForca() / MAXIMO_NIVEL ) * carta_maquina.getNivel() );
                    
                    jogador.setVida(forca);
                    
                    resultado_etapa = "Você se saiu melhor!";
                }
                else if ( carta_jogador.getNivel() < carta_maquina.getNivel() )
                {
                    forca = ((carta_maquina.getNivel() - 1) * 100) + carta_maquina.getForca();
                    
                    jogador.setVida(forca);
                    
                    forca = ((carta_jogador.getNivel() - 1) * 100) + 
                            ( ( carta_jogador.getForca() / MAXIMO_NIVEL ) * carta_jogador.getNivel() );
                    
                    maquina.setVida(forca);
                    
                    resultado_etapa = maquina.getNome() + " se saiu melhor!";
                }
                else if ( carta_jogador.getNivel() == carta_maquina.getNivel() )
                {
                    forca = ((carta_jogador.getNivel() - 1) * 100) + carta_jogador.getForca();
                    
                    maquina.setVida(forca);
                    
                    forca = ((carta_maquina.getNivel() - 1) * 100) + carta_maquina.getForca();
                                        
                    jogador.setVida(forca);
                    
                    resultado_etapa = "Ambos foram bem!";
                }
                
            }
            else if ( carta_jogador.getTipoCarta() == TIPO_CARTAS.defesa 
                    && carta_maquina.getTipoCarta() == TIPO_CARTAS.ataque )
            {
                if ( carta_jogador.getNivel() >= carta_maquina.getNivel())
                {
                    if (carta_jogador.getForca() > carta_maquina.getForca())
                    {
                        forca = carta_jogador.getForca() - carta_maquina.getForca();
                        
                        maquina.setVida(forca);
                    }
                    
                    resultado_etapa = "Você se saiu melhor!";
                }
                else
                {
                    int forca_defesa = ((carta_jogador.getNivel() - 1) * 100) + carta_jogador.getForca();
                    int forca_ataque = ((carta_maquina.getNivel() - 1) * 100) + carta_maquina.getForca();
                    
                    jogador.setVida(forca_ataque - forca_defesa);
                    
                    resultado_etapa = maquina.getNome() + " se saiu melhor!";
                }
            }
            else if ( carta_jogador.getTipoCarta() == TIPO_CARTAS.ataque 
                    && carta_maquina.getTipoCarta() == TIPO_CARTAS.defesa )
            {
                if ( carta_maquina.getNivel() >= carta_jogador.getNivel())
                {
                    if (carta_maquina.getForca() > carta_jogador.getForca())
                    {
                        forca = carta_maquina.getForca() - carta_jogador.getForca();
                        
                        jogador.setVida(forca);
                    }
                    
                    resultado_etapa = maquina.getNome() + " se saiu melhor!";
                }
                else
                {
                    int forca_defesa = ((carta_maquina.getNivel() - 1) * 100) + carta_maquina.getForca();
                    int forca_ataque = ((carta_jogador.getNivel() - 1) * 100) + carta_jogador.getForca();
                    
                    maquina.setVida(forca_ataque - forca_defesa);
                    
                    resultado_etapa = "Você se saiu melhor!";
                }
            }
            
            maquina.resetar();
            jogador.resetar();
            
            if ( jogador.isAguardando() && maquina.isAguardando() )
            {
                jogador.setAguardando(false);
                maquina.setAguardando(true);
            }
            else
            {
                jogador.setAguardando(!jogador.isAguardando());
                maquina.setAguardando(!maquina.isAguardando());
            }
            
            jogador.diminuirEspera();
            maquina.diminuirEspera();
            
            if (jogador.getVida() <= 0)
            {
                jogador.zeraVida();
            }
            
            if (maquina.getVida() <= 0)
            {
                maquina.zeraVida();
            }
            
            if (jogador.getPoder() <= 0)
            {
                jogador.setAumentoDePoder(0);
            }
            
            if (maquina.getPoder() <= 0)
            {
                maquina.setAumentoDePoder(0);
            }
                        
            repaint();
            
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }
            
        }
        
        if (jogador.getVida() <= 0)
        {
            esta_jogando = false;
            fimdejogo    = true;
        }
        else if (maquina.getVida() <= 0)
        {
            esta_jogando = false;
        }
        
        if (jogador.getPoder() <= 0)
        {
            jogador.setVida(50);
            jogador.setAumentoDePoder(0);
            jogador.liberarTodasCarta();
            
            if (jogador.getVida() <= 0)
            {
                jogador.zeraVida();
            }
                        
            resultado_etapa = "Carregando seu poder!";
            
            int poder = 0;
            
            while ( jogador.getPoder() != 500 )
            {
                poder++;
                
                jogador.setAumentoDePoder(poder);
                
                repaint();
            
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Erro de interrupção!");
                }
            }
        }
        else if (maquina.getPoder() <= 0)
        {
            maquina.setVida(50);
            maquina.setAumentoDePoder(0);
            maquina.liberarTodasCarta();
            
            resultado_etapa = "Carregando o poder de " + maquina.getNome() + "!";

            int poder = 0;
            
            while ( maquina.getPoder() != 500 )
            {
                poder++;
                
                maquina.setAumentoDePoder(poder);
                
                repaint();
            
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Erro de interrupção!");
                }
                
            }
        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e)
        {
            jogador.keyReleased(e);
        }

        // DEFINE O MÉTODO DE TECLA PRESSIONADA DO JOGADOR
        @Override
        public void keyPressed(KeyEvent e)
        {
            // O JOGADOR SE MOVIMENTA
            jogador.keyPressed(e);
        }
    }
        

}
