
public class Postfix {


	private DynamicArrayStack<String> Stack;
	private LinkedQueue<String> queue = new LinkedQueue<>();
	
	public Postfix(){
		Stack = new DynamicArrayStack<>();
	}
	public void reader(String CurrentLine){
		int spaces = CurrentLine.length() - CurrentLine.replaceAll(" ", "").length();

		String inFix = CurrentLine.replaceAll("\\s","");//removes all spaces from string

		if(spaces == 0 || checkForParenthesis(inFix) == true){//spaces or get rid of spaces
			read(inFix,null, true);
		}else{//spaces are needed/fine
			String[] split = CurrentLine.split(" ");
			read(inFix, split, false);
		}
	}
	private void read(String inFix, String [] arrayinFix,boolean noSpaces){
		
		System.out.println("InFix: "+ inFix.length());
		System.out.println(inFix);
		
		System.out.println("PostFix:");
		
		
			String tmp;
			
			if(noSpaces == true){
				for(int i = 0; i < inFix.length(); i++){//no spaces
				tmp = Character.toString(inFix.charAt(i));
				pusher(tmp);
				}
			}else{
				for(int i =0; i<arrayinFix.length; i++){
					tmp  = arrayinFix[i];
					pusher(tmp);
				}
				
			}
		emptytheStack();//if for loop ends and stack isEmpty == false then empty stack
		
		System.out.println();
		ArithmeticExpression AE = new ArithmeticExpression();
		AE.displayQueue(queue);
		emptyQueue(queue);
		System.out.println("\n");
		
	}
	
	private void pusher(String tmp){
		
		switch(tmp)
		{
		case "(":
			Stack.push(tmp);
			break;

		case "+":
		case "-": 
			add_sub(tmp);
			break;
		
		case "*":
		case "/":
			mult_div(tmp);
			break;
		
		case ")":
			endOfParenthesis();
			break;
			
		default:
			System.out.print(tmp);
			queue.enqueue(tmp);
			break;

		}
	}
	 private void emptyQueue(LinkedQueue<String> queue){
		  while(queue.isEmpty() == false){
			  queue.dequeue();
		  }
	  }
	
	private void add_sub(String tmp){//same method for either + or -
		
		if(Stack.isEmpty() == false){//checks if stack is empty so next if statement can work
			
			if(Stack.top().equalsIgnoreCase("*") == true || Stack.top().equalsIgnoreCase("/") == true ){
			emptytheStack();//empty the stack if a higher precedence is at topOfStack
			Stack.push(tmp);
			}
			
			else{Stack.push(tmp);}
			}
		else{Stack.push(tmp);}
	}
	
	private void mult_div(String tmp){// / or * does not require anything since they are "highest" precedence
		Stack.push(tmp);	
	}
	
	private void endOfParenthesis(){//if end of the '(' then empty everthing between the parenthesis
		
		while(Stack.top().equalsIgnoreCase("(") != true){ 
			String pop = Stack.pop();
			queue.enqueue(pop);
			System.out.print(pop);
		}
		
		if(Stack.top().equalsIgnoreCase("(") == true){ Stack.pop();}//user doent see (
	}
	
	private static boolean checkForParenthesis(String check){
		for(int i=0; i<check.length(); i++ ){
			if(check.charAt(i) == '('){
				return true;
			}
		}
		return false;
	}
	
	private void emptytheStack(){//emptys stack
		
		while(Stack.isEmpty() == false){
			String pop = Stack.pop();
			queue.enqueue(pop);
			System.out.print(pop);
		}
	}
}