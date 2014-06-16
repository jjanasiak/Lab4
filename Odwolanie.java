
public class Odwolanie {

	int numerstrony;
	int proces;
	
	public Odwolanie(int numerstrony, int proces)
	{
		this.proces=proces;
		this.numerstrony=numerstrony;
	}

	public int getProces()
	{
		return proces;
	}
	
	public int getNumer()
	{
		return numerstrony;
	}
	
	public String toString()
	{
		return "Proces "+proces+" odwołuje się do strony: " + numerstrony +"\n";
	}
}
