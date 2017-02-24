import java.util.Iterator;
import java.util.Scanner;

public class ArithmeticExpression {

	private LinkedQueue<String> queue = new LinkedQueue<>();
	private LinkedStack<Double> stackFinal = new LinkedStack<>();
	
	public ArithmeticExpression(){//empty constructor	
	}
	
	public void displayQueue(LinkedQueue<String> queue){

		BinaryTree<String> treeFinal = createTree(queue);
		double result = evaluateTree(treeFinal);
		System.out.println("THE FINAL ANSWER IS " + result);
	}
	
	private double evaluateTree(BinaryTree<String> treeFinal){

		Iterator<String> it = treeFinal.iterator();
		double result = 0;
		
		while(treeFinal.iterator().hasNext()){//iterate through tree
			if(it.hasNext() == false){break;}
			String element = it.next();
			
			switch(element){
			
				case "*":
				case "/":
				case "+":
				case "-":
					//four cases used to initialize n1 and n2, so we wont have to keep re writing code
					double n1 = stackFinal.pop();
					double n2 = stackFinal.pop();
					//if statement to calculate based on their operator
					if(element.equalsIgnoreCase("*") == true){result = n1*n2;System.out.println(n1+"*"+n2);}
					if(element.equalsIgnoreCase("/") == true){result = n1/n2;System.out.println(n1+"/"+n2);}
					if(element.equalsIgnoreCase("+") == true){result = n1+n2;System.out.println(n1+"+"+n2);}
					if(element.equalsIgnoreCase("-") == true){result = n1-n2;System.out.println(n1+"-"+n2);}
					
					stackFinal.push(result);//push the double result onto stack
					break;
						
				default:
					check(element);
			}
		}
		return result;
	}
	
	private BinaryTree<String>createTree(LinkedQueue<String> queue){//create the tree by
		
		LinkedStack<BinaryTree<String>> createdStack = new LinkedStack<>();
		
		System.out.println("|||||QUEUE||||||");
		while(queue.isEmpty() == false){
			String element = queue.dequeue();
			switch(element){
			
			case "*":
			case "/":
			case "+":
			case "-":
				//do something for operator 
				BinaryTree<String> T = new BinaryTree<>(element);
				BinaryTree<String> rightChild = createdStack.pop();//assign popped element to right child
				BinaryTree<String> leftChild  = createdStack.pop();//assign popped element to left child			
				
				T.attach(leftChild, rightChild);//join the binary trees to T
				createdStack.push(T);//push the tree onto the stack
				break;
				
			default:
				//do something for operand
				BinaryTree<String> T1 = new BinaryTree<>(element);
				createdStack.push(T1);
				break;		
			}
		}
		return createdStack.pop();//returns the final Binarytree 
		
	}
	
	private void check(String element){//checks if the default is a variable or a number
		
		if(isNumeric(element) == true){//is a number, push it into stack
			//System.out.println(element + " is a number");
			double number = Integer.parseInt(element);
			stackFinal.push(number);
		}
		else{//is not a number, is a variable, ask user input and push it into stack
			System.out.println("please enter a number for variable "+ element);
			Scanner reader = new Scanner(System.in); 
			double number = reader.nextInt(); 
			stackFinal.push(number);
		}	
	}
	
	private boolean isNumeric(String s) {  //returns true if it is a number
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
}