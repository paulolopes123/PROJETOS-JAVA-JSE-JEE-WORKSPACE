package model;

public class ArvoreBinaria {
	public class No {

		private Integer valor;
		private No noEsquerda;
		private No noDireita;

		public No() {
		}

		public No(Integer valor) {
			super();
			this.valor = valor;
		}

		public Integer getValor() {
			return valor;
		}

		public void setValor(Integer valor) {
			this.valor = valor;
		}

		public No getNoEsquerda() {
			return noEsquerda;
		}

		public void setNoEsquerda(No noEsquerda) {
			this.noEsquerda = noEsquerda;
		}

		public No getNoDireita() {
			return noDireita;
		}

		public void setNoDireita(No noDireita) {
			this.noDireita = noDireita;
		}
	}
	
	private No root;

    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }
    
    public int getAltura(){
        return getAltura(this.root);
    }
    
    private int getAltura(No root){
        if(root == null){
            return 0;
        }
        int altEsq = getAltura(root.getNoEsquerda());
        int altDir = getAltura(root.getNoDireita());
        if(altEsq > altDir){
            return altEsq + 1;
        } else {
            return altDir + 1;
        }
    }
    
    public int getQtdNo(){
        return getQtdNo(root);
    }
    
    private int getQtdNo(No root){
        if(root == null){
            return 0;
        }
        int qtdNoEsq = getQtdNo(root.getNoEsquerda());
        int qtdNoDireita = getQtdNo(root.getNoDireita());
        return qtdNoEsq + qtdNoDireita + 1;
    }
    
    public void listar(int tipo){
        if(this.root == null)
            System.out.println("Árvore vazia");
        else{
        	switch (tipo) {
			case 1:
				listarPos(this.root);
				break;
			case 2:
				listarPre(this.root);
				break;
			case 3:
				listarSimetrica(this.root);
				break;
			}
        	
        }        
            
    }
    
    private void listarPos(No No){
        if(No != null){
            listarPos(No.getNoEsquerda());
       
            listarPos(No.getNoDireita());
        
        System.out.println("Nó: " + No.getValor());
        }
    }
    private void listarPre(No No){
    	if(No != null){
    	System.out.println("Nó: " + No.getValor());
       
    
            listarPre(No.getNoEsquerda());
       
            listarPre(No.getNoDireita());
        }
        
    }
    
    public void listarArv(){
    	listarArvT(this.root);
    }
    public void listarArvT(No n){
    	if(n!=null){
    		System.out.println("Pai - "+n.valor);
    		if(n.noEsquerda!=null)
    		System.out.print(" Filho Esq - " +n.noEsquerda.valor);
    		if(n.noDireita!=null)
    		System.out.println(" Filho Dir - " +n.noDireita.valor );
    	
    	listarArvT(n.getNoEsquerda());
    	listarArvT(n.getNoDireita());
    	}
    }
    private void listarSimetrica(No No){    	      
    	if(No != null){
            listarSimetrica(No.getNoEsquerda());
        
    	
    	System.out.println("Nó: " + No.getValor());
    	
       
            listarSimetrica(No.getNoDireita());
        }
        
    }
    
    public void inserir(int valor){
    	 if(this.root == null){
             this.root = new No(valor);
         } 
    	 else inserir(this.root, valor);
    }
    
    public void inserir(No No, int valor) {
     
            if (valor <= No.getValor()) {
                if (No.getNoEsquerda() != null) { 
                    inserir(No.getNoEsquerda(), valor); 
                } else { 
                    //Se nodo esquerdo vazio insere o novo no aqui 
                    No.setNoEsquerda(new No(valor)); 
                } 
                //Verifica se o valor a ser inserido é maior que o no corrente da árvore, se sim vai para subarvore direita 
            } else if (valor > No.getValor()) { 
                //Se tiver elemento no no direito continua a busca 
                if (No.getNoDireita() != null) { 
                    inserir(No.getNoDireita(), valor); 
                } else {
                    //Se nodo direito vazio insere o novo no aqui 
                    No.setNoDireita(new No(valor)); 
                } 
            }
        }

    
    public No remover(int valor) throws Exception{
        return remover(this.root, valor);
    }
    
    private No remover(No No, int valor) throws Exception{
        if(this.root == null){
            throw new Exception("Árvore vazia");
        } else {            
            if(valor < No.getValor()){
                No.setNoEsquerda(remover(No.getNoEsquerda(), valor));
            } else if(valor > No.getValor()){
                No.setNoDireita(remover(No.getNoDireita(), valor));
            } else if (No.getNoEsquerda() != null && No.getNoDireita() != null) {
                /*2 filhos*/  
                System.out.println("  Removeu No " + No.getValor());
                No.setValor(encontraMinimo(No.getNoDireita()).getValor());
                No.setNoDireita(removeMinimo(No.getNoDireita()));
            } else {  
                System.out.println("  Removeu No " + No.getValor());  
                No = (No.getNoEsquerda() != null) ? No.getNoEsquerda() : No.getNoDireita();  
            }  
            return No;
        }
    }
    
    private No removeMinimo(No No) {  
        if (No == null) {  
            System.out.println("  ERRO ");  
        } else if (No.getNoEsquerda() != null) {  
            No.setNoEsquerda(removeMinimo(No.getNoEsquerda()));  
            return No;  
        } else {  
            return No.getNoDireita();  
        }  
        return null;  
    }  
  
    private No encontraMinimo(No No) {  
        if (No != null) {  
            while (No.getNoEsquerda() != null) {  
                No = No.getNoEsquerda();  
            }  
        }  
        return No;  
    }
}
