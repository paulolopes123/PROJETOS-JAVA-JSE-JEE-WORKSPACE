/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle;

import br.com.wizardbattle.visao.*;
import br.com.wizardbattle.modelo.*;
import javax.swing.JFrame;

/**
 *
 * @author caique
 */
public class Principal extends JFrame implements Comuns
{
    
    public Principal ()
    {
        add(new Tela());

        setTitle("Wizard Battle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(LARGURA, ALTURA);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args)
    {
        new Principal();
    }
    
}