import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	static int iloscramek = 30;
	static int iloscstron = 500;
	static int iloscprocesow=10;
	static int maxiloscodwolanprocesu = 20;
	static int maxiloscodwolanwsekwencji = 5;
	static double d = 0.3;
	static double g = 0.5;
	static QueueFIFO3 odwolaniaprop;
	static QueueFIFO3 odwolaniarown;
	static QueueFIFO3 odwolaniaczest;
	static QueueFIFO3 odwolaniastref;
	
	static String WYNIK="";
	
	public static void main(String[] args) {
		
		///////////////////////////////Pobieranie danych/////////////////////////////////////////////
		
		odwolaniaprop = new QueueFIFO3();
		odwolaniarown = new QueueFIFO3();
		odwolaniaczest = new QueueFIFO3();
		odwolaniastref = new QueueFIFO3();
		
		try
        {
            FileReader file = new FileReader("config.txt");
            StreamTokenizer t = new StreamTokenizer(file);
            int wartosc = 0;
            int licz = 0;
            
            while((wartosc = t.nextToken())!= StreamTokenizer.TT_EOF)
            {
            	if(wartosc==StreamTokenizer.TT_NUMBER)
	            	{
	            	licz++;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==1)      
	                	iloscramek=(int)t.nval;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==2)      
	                	iloscstron=(int)t.nval;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==3)
	                	maxiloscodwolanprocesu=(int)t.nval;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==4)
	                	maxiloscodwolanwsekwencji=(int)t.nval;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==5)
	                	g=(double)t.nval;
	                if(wartosc == StreamTokenizer.TT_NUMBER && licz==6)
	                	d=(double)t.nval;
	            	}
            }
        } 
        catch(Exception ex){
            System.out.println("Błąd przy odczycie config.txt");}
            
                       //generowanie
        
		Proces[] t = new Proces[iloscprocesow];
		//tablica procesów
		for(int i = 0; i<iloscprocesow; i++)
			t[i]= new Proces(iloscstron, i);
		
       //generowanie sekwencji
		int ilosc[] = new int[iloscprocesow];//tablica wielkosci procesów
		for(int i = 0; i<iloscprocesow; i++){
			ilosc[i]= (int)(Math.random()*maxiloscodwolanprocesu);
		System.out.print(ilosc[i]+" ");
		}
		System.out.print("\n");
		
		int iloscwygenerowanych[] = new int[iloscprocesow];//tablica iloci wygenerowanych
        for(int i = 0; i<maxiloscodwolanprocesu*iloscprocesow; i++)
		{
			int proces =(int) (Math.random()*iloscprocesow);
			if(iloscwygenerowanych[proces]<ilosc[proces])
			t[proces].generuj((int)(Math.random()*maxiloscodwolanwsekwencji), odwolaniaprop, odwolaniaczest, odwolaniarown, odwolaniastref);
			iloscwygenerowanych[proces]=t[proces].getIloscOdwolan();
		}
        //wyswietlenie
        //System.out.println(odwolaniarown.toString());
