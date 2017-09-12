package paul.language.source;

public class list {
	public static String operateList(String line){
		memory.math_on = true;
		return getElement(line.split(" ")[0],Float.parseFloat(syntax.a(line.substring(line.split(" ")[0].length() + 2, line.length()-1))));
	}
	
	public static String getElement(String name, float ind){
		int index = Integer.parseInt(String.valueOf(ind).substring(0,String.valueOf(ind).indexOf(".")));
		if(memory.var_d.get(memory.var_n.indexOf(name)).toString().contains(","))
			return memory.var_d.get(memory.var_n.indexOf(name)).toString().substring(6,memory.var_d.get(memory.var_n.indexOf(name)).toString().length() - 1).split(",")[index];
		else if(index == 0)
			return memory.var_d.get(memory.var_n.indexOf(name)).toString().substring(6,memory.var_d.get(memory.var_n.indexOf(name)).toString().length() - 1);
		return null;
	}
	
	public static boolean isList(String name){
		if(memory.var_d.get(memory.var_n.indexOf(name)).toString().startsWith("list (") && memory.var_d.get(memory.var_n.indexOf(name)).toString().endsWith(")"))
			return true;
		return false;
	}
	
	public static int listLenght(String name){
		if(memory.var_d.get(memory.var_n.indexOf(name)).toString().contains(","))
			return memory.var_d.get(memory.var_n.indexOf(name)).toString().substring(6,memory.var_d.get(memory.var_n.indexOf(name)).toString().length() - 1).split(",").length;
		return 1;
	}
}
