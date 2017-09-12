package paul.language.source;

public class syntax {
	public static String a(final String linea){
		String retornar = linea;
		try{
			for(int x = 0; x < memory.var_n.size(); x++){
				if(list.isList(memory.var_n.get(x).toString())){
					while(retornar.contains("!" + memory.var_n.get(x).toString())){
						String ary = "";
						for(int y = retornar.indexOf("!" + memory.var_n.get(x).toString()); y < retornar.length(); y++){
							if(retornar.charAt(y) == ')'){ary += retornar.charAt(y);break;}else{ary += retornar.charAt(y);}
						}
						retornar = retornar.replace(ary, list.operateList(ary.substring(1)));
					}
				}else
					retornar = retornar.replace("!" + memory.var_n.get(x).toString(), memory.var_d.get(x).toString());
			}
		}catch(Exception e){}
		try{
			for(int x = 0; x < memory.func_name.size(); x++){
				while(retornar.contains("!" + memory.func_name.get(x).toString())){
					String fnc = "";
					for(int y = retornar.indexOf("!" + memory.func_name.get(x).toString()); y < retornar.length(); y++){
						if(retornar.charAt(y) == ')'){fnc += retornar.charAt(y);break;}else{fnc += retornar.charAt(y);}
					}
					entry.clasificar(fnc.substring(1));
					retornar = retornar.replace(fnc, memory.func_return.toString());
				}
			}
		}catch(Exception e){}
		if(memory.logic_on){
			if(logic.logica_analizar(retornar))
				retornar = "True";
			else
				retornar = "False";
		}
		else if(memory.math_on){
			retornar = math.math_analizar(retornar);
		}
		memory.logic_on = false;
		memory.math_on = false;
		return retornar;
	}
}
