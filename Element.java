package komplikacje;

public class Element 
{
	    private Object o;
	    private Element nastepny;

	    public Element(Object o, Element nastepny)
	    {
	        this.o = o;
	        this.nastepny = nastepny;
	    }
	    
	    public Element()
	    {
	        this(null,null);    
	    }
	    
	    public Element pobierzNastepny()
	    {
	        return nastepny;
	    }
	    
	    public void ustawNastepny(Element nastepny)
	    {
	        this.nastepny = nastepny;
	    }
	    
	    public Object pobierzO()
	    {
	        return o;
	    }
	    
	    public void ustawO(Object o)
	    {
	        this.o = o;
	    }
	    
	    public String toString()
	    {
	        return o.toString();
	    }
	      
	    public void wstawZa(Element el)
	    {
	        Element temp = el.pobierzNastepny();
	        el.ustawNastepny(this);
	        this.ustawNastepny(temp);
	    }

}
