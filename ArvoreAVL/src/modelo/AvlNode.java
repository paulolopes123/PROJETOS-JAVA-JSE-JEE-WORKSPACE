package modelo;

public class AvlNode {
	protected int altura;       
	protected int chave;
    protected AvlNode esq, dir;
    public AvlNode ( int elemento ) {
        this( elemento, null, null );
    }
    public AvlNode ( int elemento, AvlNode lt, AvlNode rt ) {
        chave = elemento;
        esq = lt;
        dir = rt;
        altura   = 0;
    }
}