public class DynamicArrayStack<AnyType> implements Stack<AnyType>
{
  public static final int DEFAULT_CAPACITY = 256;
  AnyType[] data;
  int topOfStack;

  public DynamicArrayStack() { this(DEFAULT_CAPACITY); }

  public DynamicArrayStack(int capacity)
  {
    topOfStack = -1;
    data = (AnyType[]) new Object[capacity];
  }

  public int size()
  {
    return (topOfStack + 1);
  }

  public boolean isEmpty()
  {
    return (topOfStack <= -1);
  }

  public void push(AnyType newValue)
  {
	if (size() == data.length/4) resize(data.length/2);//resize
    
	topOfStack+=1; //increment stack
    
    data[topOfStack] = newValue; //add newValue to new topOfStack
  }

  public AnyType top() 
  {
	  if(topOfStack < 0){
		  System.out.println("Stack is empty, No Top");
		  return null;
	  }

	  return data[topOfStack];
  }

  public AnyType pop() throws IndexOutOfBoundsException
  {
    if(size() < 0 ) throw new IndexOutOfBoundsException("Stack is Empty already");
    
    if (size() == data.length) resize(2 * data.length);//if full, resize
	
    AnyType temp = data[topOfStack];//store in temp for return value
    data[topOfStack] = null; // set prev position of topOfStack to null
    
    topOfStack-=1;//decrement topOfStack
    
    return temp;
    
  }

  protected void resize(int newCapacity)
  {
    int n = size();

    AnyType[] temp = (AnyType[]) new Object[newCapacity];
    for (int i=0; i < n; i++)
      temp[i] = data[i];
    data = temp;
  }
}