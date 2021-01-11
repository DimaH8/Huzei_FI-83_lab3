public class Polinomial {
	

	int[] array = new int[233]; // Because dimension of field in my variant is 233 => 233 cells
	
	public Polinomial() 
	{		
	}
	
	public Polinomial(Polinomial value)
	{
	    this.Replace(value);
	}
	
	public Polinomial(String stroka)
	{
	    Read(stroka);
	}
	
	void Replace(Polinomial value)
	{
	    for (int i = 0; i < array.length; i++) {
	        this.array[i] = value.array[i];
	    }
	}
	
	Polinomial Null() {
		
	Polinomial result = new Polinomial();
	    for(int i = 0; i < array.length; i++) {
	        this.array[i] = 0;
	    }
	    return result;
	}
	
	Polinomial One() {
		
		Polinomial result = new Polinomial();
		result = this.Null();
		result.array[array.length-1] = 1; 
		return result;
	}
	
	Polinomial GeneratorField() {
		
		Polinomial result = new Polinomial();
		result = this.Null();
		result.array[array.length - 1]  = 1;
		result.array[array.length - 2]  = 1;
		result.array[array.length - 5]  = 1;
		result.array[array.length - 10] = 1;
	    
	    return result;
	}

	public String toString()
	{
		String out = "";
	    for(int i = 0; i < array.length; i++) {
	    	out += Integer.toString(array[i]);
	    }
	    return out;
	}
	
	void Read(String number_srt)
	{
	    if (number_srt.length() > array.length)
	    {
	    	throw new IllegalArgumentException("Error: Length of input bigger than m");
	    }
	    
	    char[] temp = new char[array.length];   
	    for (int i = 0; i < number_srt.length(); i++)
	    {
	    	char c = number_srt.charAt(i);
	    	if (c != '0' && c != '1')
	    	{
	    		throw new IllegalArgumentException("Error: Unknown char - '" + c + "'");
	    	}
	        temp[i] = c;
	    }
	
	    for (int i = 0; i < array.length; i++) {
	        if (temp[i] == '0') this.array[i] = 0;
	        if (temp[i] == '1') this.array[i] = 1;
	    }
	}
	
	void Add(Polinomial number1, Polinomial number2)
	{
	    Polinomial result = new Polinomial();
	
	    for (int i = 0; i < array.length; i++)
	    {
	    	result.array[i] = number1.array[i] ^ number2.array[i];
	    }
	
	    this.Replace(result);
	}
	
	void ShiftRight()
	{
	    Polinomial temp = new Polinomial();
	    
	    for(int i = 0; i < array.length - 1; i++) {
	        temp.array[i+1] = this.array[i];
	    }
	    temp.array[0] = 0;
	    
	    for(int i = 0; i < array.length; i++) {
	        this.array[i] = temp.array[i];
	    }
	}
	
	void ShiftLeft()
	{
	    Polinomial temp = new Polinomial();
	    for(int i = 1; i < array.length; i++) {
	        temp.array[i-1] = this.array[i];
	    }
	    temp.array[array.length - 1] = 0;
	    
	    for(int i = 0; i < array.length; i++) {
	        this.array[i] = temp.array[i];
	    }
	}
	
	void Mul(Polinomial number1, Polinomial number2)
	{
	     Polinomial first = new Polinomial(number1);
	     Polinomial second = new Polinomial(number2);
	     Polinomial chastka = new Polinomial();
	     Polinomial polinom = new Polinomial();
	     polinom = polinom.GeneratorField();
	
	     for(int i = array.length - 1; i >= 0; i--)
	     {
	        if (second.array[i] == 1) 
	        {
	        	chastka.Add(chastka, first);
	        }
	        if (first.array[0] == 1)
	        {
	            first.ShiftLeft();
	            first.Add(first, polinom);
	        }
	        else 
	        {
	        	first.ShiftLeft();
	        }
	     }
	     this.Replace(chastka);
	}
	
	void Pow(Polinomial number, Polinomial u)
	{
	   Polinomial a = new Polinomial(number);
	   Polinomial r = new Polinomial();
	   r = r.One();
	
	   for (int i = 0; i < array.length; i++)
	   {
		   if (u.array[array.length - 1 - i] != 0)
		   {
			   r.Mul(r,a);
	       }
	       a.Mul(a,a);
	   }
	
	   this.Replace(r);
	
	}
	
	void Inverse(Polinomial value)
	{
	    Polinomial a = new Polinomial(value);
	    Polinomial u = new Polinomial();

	    for(int i = 0; i < array.length - 1; i++) {
	        u.array[i] = 1; 
	        }
	     u.array[array.length - 1] = 0;
	
	    this.Pow(a,u);
	}
	
	void Test10 (Polinomial a, Polinomial b, Polinomial c) {
		Polinomial result = new Polinomial();
		this.Add(a, b);
		result.Mul(this, c);
		this.Replace(result);
		
	}

	void Test11 (Polinomial a, Polinomial b, Polinomial c) {
		Polinomial result = new Polinomial();
		this.Mul(b, c);
		result.Mul(c, a);
		result.Add(this, result);
		this.Replace(result);	
	}
	
	void Test2 (Polinomial a, Polinomial test) {
		this.Pow(a, test);
	}
}
