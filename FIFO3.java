public class QueueFIFO3
{
	private Element head;
	private Element tail;
	private int rozmiar;

	    public QueueFIFO3()
	    {
	        head = new Element(null, null);
	        tail = new Element(null, head);
	        rozmiar = 0;
	    }
	    
	    public void enqueue(Object o)
	    {
	    	Element el = new Element(o, null);
	        el.wstawZa(tail.pobierzNastepny());
	        rozmiar++;
	        tail.ustawNastepny(el);
	    }
	    
	    public Element dequeue() throws EmptyQueueException  {
	        if (isEmpty()) {
	            throw new EmptyQueueException();
	        }
	        return delete();
	    }
	    
	    public Element delete()
	    {
	    	Element temporary = head.pobierzNastepny();
	        head.ustawNastepny(head.pobierzNastepny().pobierzNastepny());
	               rozmiar--;
	               if(head.pobierzNastepny()==null) tail.ustawNastepny(head);
	               return temporary;
	    }
	    
	    public boolean isEmpty()
	    {
	        return (head.pobierzNastepny() == null);
	    }
	    
	    public void clear()
	    {
	        head = new Element(null, null);
	        tail = new Element(null, null);
	        rozmiar = 0;
	    }
	    
	    public int size()
	    {
	        return rozmiar;
	    }
	    
	    public String toString()
	    {
	    	String lista="";
	        System.out.println ("Odwolania: ");
	        if(!isEmpty())
	        {
	        	Element temporary = head.pobierzNastepny();
	        	while(temporary.pobierzNastepny() != null)
	        	{
	        		lista+=((Odwolanie)temporary.pobierzO()).toString();
	        		temporary = temporary.pobierzNastepny();
	        	}
	        	lista+=((Odwolanie)temporary.pobierzO()).toString();
	        }
	        return lista;
	    }
}
