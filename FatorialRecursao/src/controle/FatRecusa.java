package controle;

public class FatRecusa {

	public static void main(String[] args) {

		
		
		public int fatorial(int num){
			int resu;
			
			if(num == 0 || num == 1)
				
				resu = 1;
			
			else
			resu = num * fatorial(num - 1);
			
			return resu;
			
		}
		
		
		
		
		
	}

}
