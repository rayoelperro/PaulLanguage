package paul.language.source;

import java.io.InputStream;

public class variables {
	public static void add_var(String linea, paul_values uso){
		String[] valor = linea.split(" = ");
		boolean f_notcan = false;
		try{
			Integer.parseInt(String.valueOf(valor[0].charAt(0)));
			f_notcan = true;
		}catch(Exception e){
			f_notcan = false;
		}finally{
			if(valor[0].contains(".") || valor[0].contains("<") || valor[0].contains("*") || valor[0].contains("/") || valor[0].contains(" ") || valor[0].contains(":"))
				f_notcan = true;
		}
		if(!f_notcan){
			if(uso == paul_values.normalize_var){
				memory.var_n.add(valor[0]);
				memory.var_d.add(syntax.a(valor[1]));
				memory.var_f.add("");
			}else{
				switch(uso){
					case file_var:
						memory.var_n.add(valor[0]);
						memory.var_d.add(files.file(valor[1]).getPath());
						memory.var_f.add("");
						break;
					case input_var:
						memory.var_n.add(valor[0]);
						memory.var_d.add(IO.input(valor[1]));
						memory.var_f.add("");
						break;
					default:
						break;
				}
			}
		}else{
			IO.add_errorline("The name: " + valor[0] + " is not allowed");
		}
	}
	
	public static void set_var(String linea){
		String[] valor = linea.replace("set ", "").split(" to ");
		if(memory.var_n.contains(valor[1])){
			int location = memory.var_n.indexOf(valor[1]);
			memory.var_d.set(location, valor[0]);
		}
	}
	
	public static void add_subvar(String inicial, String sub){
		int l = memory.var_n.indexOf(inicial);
		add_var(inicial + ">" + sub + " = null", paul_values.normalize_var);
		if(memory.var_f.get(l).toString().equals("")){
			memory.var_f.set(l, inicial + ">" + sub);
		}else{
			memory.var_f.set(l, "%%" + inicial + ">" + sub);
		}
	}
	
	public static String v_revise(String valor){
		if(memory.var_n.contains(valor)){
			int location = memory.var_n.indexOf(valor);
			return memory.var_d.get(location).toString();
		}
		return valor;
	}
	
	public static String v_prop(String variable, String subvariable){
		String res = "";
		if(subvariable.equals(".length")){
			res = String.valueOf(((String) v_revise(variable)).length());
		}
		return res;
	}
	
	public static void v_remove(String variable){
		int l = memory.var_n.indexOf(variable);
		if(!(memory.var_f.get(l).toString() == "")){
			String[] vs = memory.var_f.get(l).toString().split("%%");
			for (int i = 0; i < vs.length; i++) {
				try{
					v_remove(vs[i]);
				}catch(Exception v_rm){
					
				}
			}
		}
		memory.var_f.remove(l);
		memory.var_n.remove(l);
		memory.var_d.remove(l);
	}
	
	public static boolean v_exists(String valor){
		if(memory.var_n.contains(valor)){
			return true;
		}
		return false;
	}
	
	public static void v_revalue(String variable, String valor){
		if(memory.var_n.contains(variable)){
			int location = memory.var_n.indexOf(variable);
			memory.var_d.set(location, valor);
		}
	}
	
	public static InputStream input_revise(String name){
		if(memory.var_n.contains(name)){
			int location = memory.var_n.indexOf(name);
			return (InputStream)memory.var_d.get(location);
		}
		return null;
	}
}