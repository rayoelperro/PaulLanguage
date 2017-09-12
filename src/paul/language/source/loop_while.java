package paul.language.source;

public class loop_while {
	public static void while_m(String linea){
		if(linea.startsWith("while ")){
			String valor = linea.substring(6);
			memory.while_leyendo = true;
			memory.while_exp_revise = valor;
		}
	}
	
	public static void nueva_linea(String linea){
		if(memory.while_lineas.equals("")){
			memory.while_lineas += linea;
		}else{
			memory.while_lineas += "\n" + linea;
		}
	}
	
	public static void reproducir(){
		memory.while_leyendo = false;
		String texto = memory.while_lineas;
		memory.while_lineas = "";
		String exp = memory.while_exp_revise;
		memory.while_exp_revise = "";
		while(logic.logica_analizar(exp)){
			entry.por_lineas(texto);
		}
	}
}
