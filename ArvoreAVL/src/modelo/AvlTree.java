package modelo;

public class AvlTree {
    private AvlNode root = null;
    public AvlTree( ) {
        root = null;
    }
    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public AvlNode getRootNode (){
        return root;
    }
    /** Retorna a altura da árvore */
    private static int altura( AvlNode t ) {
        return t == null ? -1 : t.altura;
    }
     /**
     * Retorna o maior valor ente lhs e rhs.
     */
    private static int max( int lhs, int rhs ) {
        return lhs > rhs ? lhs : rhs;
    }
    /** Retorna o fator de balanceamento da árvore com raiz t */ 
    private int getFactor (AvlNode t) {
        return altura( t.esq ) - altura( t.dir );
    }
    public boolean insert (int x) {
        root = insert (x, root);
        return true;
    }
    private AvlNode insert (int x, AvlNode t) {
        if( t == null )
            t = new AvlNode( x, null, null );
        else if( x<t.chave ) t.esq = insert( x, t.esq );
        else if( x>t.chave) t.dir = insert( x, t.dir );
        t = balance (t);
        return t;
    }
    public AvlNode balance (AvlNode t) {
        if ( getFactor(t) == 2 ) {
                if (getFactor (t.esq)>0) t = dodirRotation( t );
                else t = doDoubledirRotation( t );
        }
        else if ( getFactor(t) == -2 ) {
                if ( getFactor(t.dir)<0 ) t = doesqRotation( t );
                else t = doDoubleesqRotation( t );
        }
        t.altura = max( altura( t.esq ), altura( t.dir ) ) + 1;
        return t;
    }
    /** Faz Rotação simples a direita */
    private static AvlNode dodirRotation( AvlNode k2 ) {
        AvlNode k1 = k2.esq;
        k2.esq = k1.dir;
        k1.dir = k2;
        k2.altura = max( altura( k2.esq ), altura( k2.dir ) ) + 1;
        k1.altura = max( altura( k1.esq ), k2.altura ) + 1;
        return k1;
    }
    /** Rotação simples à esquerda */
    private static AvlNode doesqRotation( AvlNode k1 ) {
        AvlNode k2 = k1.dir;
        k1.dir = k2.esq;
        k2.esq = k1;
        k1.altura = max( altura( k1.esq ), altura( k1.dir ) ) + 1;
        k2.altura = max( altura( k2.dir ), k1.altura ) + 1;
        return k2;
    }
    /** Rotação dupla à direita */
    private static AvlNode doDoubledirRotation( AvlNode k3 ) {
        k3.esq = doesqRotation( k3.esq );
        return dodirRotation( k3 );
   }
    /** Rotação dupla à esquerda */
    private static AvlNode doDoubleesqRotation( AvlNode k1 ) {
        k1.dir = dodirRotation( k1.dir );
        return doesqRotation( k1 );
    }
    public AvlNode search(int el) {
        return search(root,el);
    }
    protected AvlNode search(AvlNode p, int el) {
        while (p != null) {
            /* se valor procuradp == chave do nó retorna referência ao nó */ 
            if (el==p.chave) return p;
            /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
            else if (el<p.chave) p = p.esq;
            /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
            else p = p.dir;
        }
        // caso chave não foi achada, retorna null
        return null;
    }
    public void inorder() {
        inorder(root);
    }
    protected void inorder(AvlNode p) {
        if (p != null) {
             inorder(p.esq);
             System.out.print(p.chave+" - ");
             inorder(p.dir);
        }
    }
    public void preorder() {
        preorder(root);
    }
    protected void preorder(AvlNode p) {
        if (p != null) {
             System.out.print(p.chave + " ");
             preorder(p.esq);
             preorder(p.dir);
        }
    }
    public void postorder() {
        postorder(root);
    }
    protected void postorder(AvlNode p) {
        if (p != null) {
             postorder(p.esq);
             postorder(p.dir);
             System.out.print(p.chave + " ");
        }
    }
protected AvlNode searchFather (int el) {
    AvlNode p = root;
    AvlNode prev = null;
    while (p != null && !(p.chave==el)) {  // acha o nó p com a chave el
        prev = p;                           
        if (p.chave<el)
              p = p.dir;
        else p = p.esq;
    }
    if (p!=null && p.chave==el) return prev;
    return null;
}
/* método de autoria de Leonardo Zandoná - 2006/2 */
public void displayTree() {
	if (isEmpty()){
		System.out.println("Árvore vazia!");
		return;
	}    		
	String separator = String.valueOf("  |__");
	System.out.println(this.root.chave+"("+root.altura+")");
	displaySubTree(root.esq,  separator);
	displaySubTree(root.dir, separator);
}
private void displaySubTree(AvlNode node, String separator) {
	if (node != null) {
		AvlNode father = this.searchFather(node.chave);
		if (node.equals(father.esq) == true) {
			System.out.println(separator+node.chave+"("+node.altura+")"+" (ESQ)");
		}else{
			System.out.println(separator+node.chave+"("+node.altura+")"+" (DIR)");
		}			
		displaySubTree(node.esq,  "     "+separator);
		displaySubTree(node.dir, "     "+separator);
	}
}
    public static void main (String args[]){
        AvlTree t = new AvlTree();
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);
        t.insert(6);
        t.insert(7);
        t.insert(8);
        t.insert(9);
        t.insert(10);
        t.insert(11);
        t.insert(12);
        t.insert(13);
        t.insert(14);
        t.insert(15);
        t.displayTree();
    }
} // class
