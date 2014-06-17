public class PamiecProp implements Pamiec{

	int size;
	int clock;
	int errors=0;
	int podzial;
	int iloscprocesow;
	QueueFIFO3 odwolania;
	Ramka[][] memory;
	int[] perrors;
	Proces[] t;
	
	public PamiecProp(int memsize, QueueFIFO3 odwolania, int iloscprocesow, Proces[] t)
	{
		size = memsize;
		this.t=t;
		this.odwolania = odwolania;
		memory = new Ramka[iloscprocesow][];
		//suma potrzeb
		int suma=0;
		for(int i=0; i<iloscprocesow; i++)
			suma+=t[i].getIloscOdwolan();
		for(int i=0; i<iloscprocesow; i++)
		{
			podzial = (int)Math.floor(t[i].getIloscOdwolan()*memsize/suma);
			memory[i]=new Ramka[podzial];
			double p=((double)(t[i].getIloscOdwolan()*memsize)/suma);
			if(podzial==0 && p>0.0)
				memory[i]=new Ramka[1];
		}
		this.iloscprocesow=iloscprocesow;
		clock=0;
		for(int i=0; i<iloscprocesow; i++)
		for(int j=0; j<memory[i].length; j++)
		{memory[i][j]=new Ramka();}
		perrors = new int[iloscprocesow];
	}
	
	public void run()
	{
	//	System.out.println(wyswietl());
		if(!odwolania.isEmpty())
		{
			Odwolanie o = (Odwolanie) (odwolania.dequeue().pobierzO());
			int l=o.getProces();
	//		System.out.println(o.toString());
			if(isInMemory(o)==-1)
			{
	//			System.out.println("B³¹d - "+o);
				insertPage(o, clock);
				errors++;
				perrors[o.getProces()]+=1;
			}
			else{
				memory[l][isInMemory(o)].getStrona().setCzas(clock);
			}
		}
		clock++;
	//	System.out.print(toString());
	}
	
	public int isInMemory(Odwolanie o)
	{
		int l=o.getProces();
		for(int i=0; i<memory[l].length; i++)
		{
			if(memory[l][i].getStrona()!=null)
			if(memory[l][i].getStrona().getNumer()==o.getNumer())
				return i;
		}
		return -1;
	}
	
	public boolean isFull(int l)
	{
		for(int i=0; i<memory[l].length; i++)
		{
			if(memory[l][i].getStrona()==null)
				return false;
		}
		return true;
	}
	
	public void insertPage(Odwolanie o, int clock)
	{
		if(!isFull(o.getProces()))
		{
			int i=0; boolean w=false;
			int l=o.getProces();
			while(i<memory[l].length && w==false)
			{
				if(memory[l][i].getStrona()==null)
				{
					memory[l][i].wstawStrone(new Strona(o.getNumer(), clock));
					w=true;
				}
				i++;	
			}
		}
		else
		{
			int pom=clock; int temp=0;
			int i=0;
			int l=o.getProces();
			for(i=0; i<memory[l].length; i++)
			{
				if(memory[l][i].getStrona().getCzas()<pom)
				{
					pom=memory[l][i].getStrona().getCzas();
					temp=i;
				}
			}
			memory[l][temp].wstawStrone(new Strona(o.getNumer(), clock));
		}
	}
	
	public String getError()
	{
		return "B³êdów stron: " + errors;// + " Czas: "+ clock;
	}
	
	public String toString()
	{
		String s = "";
		for(int i=0; i<iloscprocesow; i++)
		{
		s+="Proces " + i + ": ";
		for(int j=0; j<memory[i].length; j++)
			if(memory[i][j].getStrona()!=null)
			s+=" "+memory[i][j].getStrona().toString()+" ";
			else
			s+=" ";
		s+="\n";
		}
		s+= "B³êdy: " + errors + "\n";
		for(int i=0;i<perrors.length;i++)
			s+= "B³êdów dla procesu "+i+": "+perrors[i] +"\n";
		return (s+"\n");
	}
	
	public String wynik()
	{
		String s = "\n";
		s+= "Ilosc odwolan dla procesow: \n";
		for(int i=0;i<t.length;i++)
		s+= "Odwolan dla procesu "+i+": "+t[i].getIloscOdwolan()+" Przydzial: "+memory[i].length+"\n";
		s+= "Bledy: " + errors + "\n";
		for(int i=0;i<perrors.length;i++)
			s+= "Bledow dla procesu "+i+": "+perrors[i] +"\n";
		return (s+"\n");
	}
	
	
}
