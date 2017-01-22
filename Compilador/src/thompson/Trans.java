package thompson;
/*
 * @since 0.1
 */
public class Trans {
	private String step;

	private int next;
	
	public Trans (Trans T){
		step=T.getStep();
		next=T.getNext();

	}
	
	public Trans (String S, int N) {
		step = S;

		next = N;
	}
	
	public Trans (){
		step=null;
		next=0;
		
	}
	
	/*
	 * Retorna el string con el cual hace transición.
	 */
	
	public void addCont (int cont) {
		next = next + cont;
	}
	
	public String getStep (){
		return step;
	}
	
	public int getNext (){
		return next;
	}
	
	public void setStep (String c){
		step=c;
	}
	
	public void setNext (int i){
		next=i;
	}
}

