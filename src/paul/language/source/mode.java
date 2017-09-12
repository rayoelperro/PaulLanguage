package paul.language.source;

public class mode {
	
	public static void p(String linea){
		try{
		entry.clasificar(linea.substring(10));
		}catch(Exception nula){
			
		}
	}
	
	public static void c(String linea){
		try{
		entry.clasificar(linea.substring(8));
		}catch(Exception adv){
			IO.add_outline(adv.getMessage());
		}
	}
	
	public static void s(String linea){
		entry.clasificar(syntax.a(linea.substring(6)));
	}
	
	public static void logic(String linea){
		memory.logic_on = true;
		entry.clasificar(linea.substring(6));
	}
	
	public static void math(String linea){
		memory.math_on = true;
		entry.clasificar(linea.substring(5));
	}
}