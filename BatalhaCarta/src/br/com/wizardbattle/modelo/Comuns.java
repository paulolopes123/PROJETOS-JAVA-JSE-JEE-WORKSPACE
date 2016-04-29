/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wizardbattle.modelo;

/**
 *
 * @author 8itoporcento
 * @classe Comuns
 * @objetivo Possuir os atributos fixos da aplicação
 *
 */

public interface Comuns 
{
    
    public enum TIPO_CARTAS { ataque , defesa };
    
    public static final int MAXIMO_NIVEL = 5;
    public static final int MAXIMA_FORCA = 100;
    public static final int MAXIMA_VIDA  = 2000;
    public static final int MAXIMO_PODER = 500;
    
    public static final boolean FIM_JOGO           = false;
    public static final boolean CARREGAMENTO_PODER = false;
    
    public static final int ALTURA = 810;
    public static final int LARGURA = 1226;
    
    public static final String CAMINHO_GRAFICOS = "/sprites/";
    
    public static final int delay = 17;

}
