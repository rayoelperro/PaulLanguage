package paul.language.source;

public class conditions {
	public static void condicionar(String linea){
		if(linea.contains("if ")){
			if(logic.logica_analizar(linea.substring(3))){
				memory.if_bql = false;
				memory.pre_blq = true;
			}
			else{
				memory.if_bql = true;
				memory.pre_blq = false;
			}
		}
		else if(linea.equals("else")){
			if(memory.pre_blq == true){
				memory.else_bql = true;
				memory.pre_blq = false;
			}
			else{
				memory.else_bql = false;
				memory.pre_blq = false;
			}
		}
	}
	
	public static void end_condicionante(String linea){
		if(linea.contains("endif")){
			memory.if_bql = false;
		}
		if(linea.contains("endelse")){
			memory.else_bql = false;
		}
	}
}
