package paul.language.source;

public class operators {
	public static void operate(String linea, final String variable){
		String valor = linea.replaceFirst(variable + " ", "");
		int location = memory.var_n.indexOf(variable);
		if(valor.charAt(0) == '='){
			if(valor.charAt(1) == ':'){
				memory.var_d.set(location, logic.logica_analizar(syntax.a(valor.replaceFirst("=: ", ""))));
			}else if(valor.charAt(1) == '+'){
				memory.var_d.set(location, memory.var_d.get(location) + syntax.a(valor.replace("=+ ", "")));
			}else if(valor.charAt(1) == '-'){
				if(valor.charAt(2) == '-'){
					memory.var_d.set(location, memory.var_d.get(location).toString().replaceAll(syntax.a(valor.replace("=-- ", "")), ""));
				}else if(valor.charAt(2) == ' '){
					memory.var_d.set(location, memory.var_d.get(location).toString().replaceFirst(syntax.a(valor.replace("=- ", "")), ""));
				}
			}else if(valor.charAt(1) == ' '){
				memory.var_d.set(location, syntax.a(valor.replaceFirst("= ", "")));
			}else if(valor.charAt(1) == '?'){
				if(logic.logica_analizar(valor.substring(3))){
					memory.var_d.set(location, "True");
				}else if(logic.logica_analizar(valor.substring(3))){
					memory.var_d.set(location, "False");
				}
			}
		}else if(valor.charAt(0) == '-'){
			if(valor.charAt(1) == ';'){
				variables.v_remove(variable);
			}else if(valor.charAt(1) == '>'){
				variables.add_subvar(variable, valor.replace("-> ", ""));	
			}else if(valor.charAt(1) == '='){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) - Float.parseFloat(valor.replace("-= ", "")));
			}else if(valor.charAt(1) == '-'){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) - 1);
			}
		}else if(valor.charAt(0) == '+'){
			if(valor.charAt(1) == '='){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) + Float.parseFloat(valor.replace("+= ", "")));
			}else if(valor.charAt(1) == '+'){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) + 1);
			}
		}else if(valor.charAt(0) == '*'){
			if(valor.charAt(1) == '='){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) * Float.parseFloat(valor.replace("*= ", "")));
			}else if(valor.charAt(1) == '*'){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) * Float.parseFloat(memory.var_d.get(location).toString()));
			}else if(valor.charAt(1) == ':'){
				String[] toSep = valor.substring(3).split(" ");
				memory.var_d.set(location, system.call_method(toSep[0], toSep[1], syntax.a(toSep[2])));
			}
		}else if(valor.charAt(0) == '/'){
			if(valor.charAt(1) == '='){
				memory.var_d.set(location, Float.parseFloat(memory.var_d.get(location).toString()) / Float.parseFloat(valor.replace("/= ", "")));
			}else if(valor.charAt(1) == '/'){
				memory.var_d.set(location, Math.sqrt(Float.parseFloat(memory.var_d.get(location).toString())));
			}
		}else if(valor.charAt(0) == ':'){
			if(valor.charAt(1) == '='){
				memory.var_d.set(location, memory.using_params.get(Integer.parseInt(valor.replaceFirst(":= ", ""))));
			}else if(valor.charAt(1) == '<'){
				String pre = memory.var_d.get(location).toString();
				String des = "";
			    for (int x=pre.length()-1;x>=0;x--)
		    		des = des + pre.charAt(x);
				memory.var_d.set(location, des);
			}else if(valor.charAt(1) == '-'){
				String pre = memory.var_d.get(location).toString();
				String des = "";
				for(int c = 0; c < pre.length(); c++){
					if(!(Integer.parseInt(valor.replaceFirst(":- ", "")) == c))
						des += pre.charAt(c);
				}
				memory.var_d.set(location, des);
			}else if(valor.charAt(1) == '?'){
				if(memory.var_d.get(location).toString().equals("True")){
					entry.clasificar(valor.substring(3));
				}
			}
		}else if(valor.charAt(0) == '<'){
			if(valor.charAt(1) == '<'){
				memory.var_d.set(location, memory.var_d.get(location) + syntax.a(valor.replaceFirst("<< ", "")));
			}
		}else if(valor.charAt(0) == '>'){
			if(valor.charAt(1) == '<'){
				memory.var_d.set(location, syntax.a(valor.replaceFirst(">< ", "")) + memory.var_d.get(location));
			}else if(valor.charAt(1) == '>'){
				functions.call_function("(" + valor.replaceFirst(">> ", "") + ")", memory.var_d.get(location).toString());
			}
		}
	}
}
