package paul.language.source;

import java.io.File;

import android.content.Context;

public class entry {
	public static void receptor(String leer, Context actual){
		memory.actual = actual;
		http.delete();
		por_lineas(leer);
		clear();
	}
	
	public static void por_lineas(String texto){
		String[] linea = texto.split("\n");
		for(int x = 0; x < linea.length; x++){
			if(linea[x].length() > 0){
				while(linea[x].charAt(0) == ' '){
					linea[x] = linea[x].substring(1);
				}
				if(memory.encendido){
					if(memory.while_leyendo == false){
						if(memory.if_bql == false && memory.else_bql == false){
							clasificar(linea[x]);
						}else if(linea[x].equals("endif") || linea[x].equals("endelse")){
							conditions.end_condicionante(linea[x]);
						}
					}else if(memory.while_leyendo){
						if(linea[x].equals("endwhile")){
							loop_while.reproducir();
						}else{
							loop_while.nueva_linea(linea[x]);
						}
					}
				}else{
					break;
				}
			}
		}
	}
	
	public static void clasificar(String linea){
		if(memory.comment)
			if(!linea.endsWith("'''"))
				linea = "()";
			else{
				memory.comment = false;
				linea = "()";
			}
		if(memory.func_loading && !linea.startsWith("+ ")){
			memory.func_loading = false;
		}
		String[] sep = linea.split(" ");
		if(sep[0].equals("print")){
			IO.add_outline(memory.linklast + syntax.a(linea.substring(6)));
			memory.linklast = "";
		}
		else if(sep[0].equals("screenfast")){
			IO.toast(memory.linklast + syntax.a(linea.substring(11)));
			memory.linklast = "";
		}
		else if(sep[0].equals("link") && sep[1].equals("print")){
			memory.linklast += syntax.a(linea.substring(11));
		}
		else if(sep[0].equals("var")){ 
			if(sep[1].equals(":") && !sep[2].equals("none")){
				for(paul_values p : paul_values.values()){
					if(p.archivo().equals(sep[2])){
						variables.add_var(linea.substring(7 + sep[2].length()), p);
					}
				}
			}else{
				variables.add_var(linea.substring(4), paul_values.normalize_var);
			}
		}
		else if(sep[0].equals("file")){
			files.file(linea);
		}
		else if(sep[0].equals("folder")){
			directories.folder(linea);
		}
		else if(sep[0].equals("out")){
			IO.out(linea);
		}
		else if(sep[0].equals("http")){
			http.httpuse(linea, sep);
		}
		else if(sep[0].equals("logic")){
			mode.logic(linea);
		}
		else if(sep[0].equals("math")){
			mode.math(linea);
		}
		else if(sep[0].equals("set")){
			variables.set_var(linea);
		}
		else if(variables.v_exists(sep[0])){
			operators.operate(linea, sep[0]);
		}
		else if(sep[0].equals("if") || sep[0].equals("else") || sep[0].equals("endif") || sep[0].equals("endelse")){
			conditions.condicionar(linea);
		}
		else if(sep[0].equals("while") || sep[0].equals("endwhile")){
			loop_while.while_m(linea);
		}
		else if(sep[0].equals("implements")){
			if(sep[1].equals("from"))
				implementers.frm(linea);
			else
				implementers.def(linea);
		}
		else if(sep[0].equals("using")){
			usings.utilizar(linea);
		}
		else if(sep[0].equals("protected")){
			mode.p(linea);
		}
		else if(sep[0].equals("caution")){
			mode.c(linea);
		}
		else if(sep[0].equals("shell")){
			mode.s(linea);
		}
		else if(sep[0].equals("prefab")){
			usings.import_params(linea.substring(7));
		}
		else if(sep[0].equals("fun")){
			functions.make_function(sep[1], linea.substring(sep[0].length() + sep[1].length() + 2));
		}
		else if(sep[0].equals("()")){
			//Se omite
		}
		else if(linea.startsWith("'''")){
			memory.comment = true;
		}
		else if(linea.equals("help")){
			IO.add_outline("Paul version: " + memory.version);
		}
		else if(sep[0].equals("intent") && linea.contains(" : ")){
			system.intent(linea.substring(7));
		}
		else if(sep[0].equals("runtime")){
			system.runtime(linea.substring(8));
		}
		else if(sep[0].equals("chdir")){
			memory.dir = new File(linea.substring(6));
		}
		else if(sep[0].equals("package")){
			memory.pack = sep[1];
		}
		else if(sep[0].equals("+")){
			if(memory.func_loading){
				memory.func_lines.set(memory.func_lines.size() - 1, memory.func_lines.get(memory.func_lines.size() - 1) + "\n" + linea.substring(2));
			}
		}
		else if(sep[0].equals("call")){
			system.call_method(sep[1],sep[2],syntax.a(sep[3]));
		}
		else if(sep[0].equals("return")){
			memory.func_return = syntax.a(linea.substring(7));
		}
		else if(sep[0].equals("objects")){
			if(sep[1].equals("prefabs")){
				for(Object p : memory.using_nombre)
					IO.add_outline((String)p);
			}else if(sep[1].equals("variables")){
				for(Object p : memory.var_n)
					IO.add_outline((String)p);
			}else if(sep[1].equals("functions")){
				for(Object p : memory.func_name)
					IO.add_outline((String)p);
			}else{
				IO.add_errorline(sep[1] + " is not a object type");
			}
		}
		else if(usings.using_exists(sep[0])){
			usings.call(sep[0], linea.substring(sep[0].length() + 1));
		}
		else if(functions.function_exists(sep[0])){
			functions.call_function(sep[0], linea.substring(sep[0].length() + 1));
		}
		else if(variables.v_exists(sep[0])){
			operators.operate(linea, sep[0]);
		}
		else{
			IO.add_errorline(linea);
		}
	}
	
	public static void clear(){
		memory.encendido = true;
		memory.actual = null;
		memory.pack = "";
		memory.var_f.clear();
		memory.var_d.clear();
		memory.var_n.clear();
		memory.using_ruta.clear();
		memory.using_nombre.clear();
		memory.using_params.clear();
		memory.func_name.clear();
		memory.func_lines.clear();
		memory.func_loading = false;
		memory.func_params.clear();
		memory.func_return = null;
		memory.if_bql = false;
		memory.pre_blq = false;
		memory.else_bql = false;
		memory.logic_on = false;
		memory.math_on = false; 
		memory.while_exp_revise = "";
		memory.while_lineas = "";
		memory.while_leyendo = false;
		memory.comment = false;
	}
}