package paul.language.source;

public class functions {
	public static void make_function(String name, String args){
		memory.func_name.add(name);
		if(args.contains(","))
			memory.func_params.add(args.substring(1, args.length() - 1).split(","));
		else if(args.length() == 2)
			memory.func_params.add("NON");
		else
			memory.func_params.add(args.substring(1, args.length() - 1));
		memory.func_lines.add("()");
		memory.func_loading = true;
	}
	
	public static void call_function(String name, String params){
		if(function_exists(name)){
			int index = memory.func_name.indexOf(name);
			if(params.contains(",")){
				String[] _params = params.substring(1,params.length() - 1).split(",");
				for(int ind = 0; ind < _params.length; ind++)
					variables.add_var(((String[])memory.func_params.get(index))[ind] + " = " + _params[ind], paul_values.normalize_var);
				entry.por_lineas(memory.func_lines.get(index).toString());
				for(int ind = 0; ind < _params.length; ind++)
					variables.v_remove(((String[])memory.func_params.get(index))[ind]);
			}else if(params.length() == 2){
				entry.por_lineas(memory.func_lines.get(index).toString());
			}else{
				variables.add_var((memory.func_params.get(index)) + " = " + params.substring(1,params.length() - 1), paul_values.normalize_var);
				entry.por_lineas(memory.func_lines.get(index).toString());
				variables.v_remove(memory.func_params.get(index).toString());
			}
		}else{
			IO.add_errorline("The function: " + name + " doesn't exist");
		}
	}
	
	public static boolean function_exists(String name){
		if(memory.func_name.contains(name))
			return true;
		return false;
	}
}
