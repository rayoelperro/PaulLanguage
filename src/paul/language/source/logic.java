package paul.language.source;

public class logic {
	public static boolean logica_analizar(String expresion){
		String sfinal = expresion;
        return evaluar(organizar(sfinal));
	}
	
	private static String organizar(String sfinal)
    {
        String reconstruir = "";
        String actual = "";
        for(char pt : sfinal.toCharArray())
        {
            if (pt == '(')
            {
                reconstruir += actual;
                actual = "";
                reconstruir += pt;
            }
            else if (pt == ')')
            {
                reconstruir += booleano(actual);
                reconstruir += pt;
                actual = "";
            }
            else
            {
                actual += pt;
            }
        }
        return reconstruir;
    }
	
	private static boolean evaluar(String expression)
    {
        expression = expression.toLowerCase();
        expression = expression.replace("false", "0");
        expression = expression.replace("true", "1");
        expression = expression.replace("not", "0&&");
        expression = expression.replace(" ", "");
        String temp;
        do
        {
            temp = expression;
            expression = expression.replace("(0)", "0");
            expression = expression.replace("(1)", "1");
            expression = expression.replace("0&&0", "0");
            expression = expression.replace("0&&1", "0");
            expression = expression.replace("1&&0", "0");
            expression = expression.replace("1&&1", "1");
            expression = expression.replace("0||0", "0");
            expression = expression.replace("0||1", "1");
            expression = expression.replace("1||0", "1");
            expression = expression.replace("1||1", "1");
        }
        while (temp != expression);
        if(expression.equals("0&&"))
        	expression = "0";
        if (expression.equals("0"))
            return false;
        if (expression.equals("1"))
            return true;
        IO.add_errorline("Is not a boolean expresion");
        return false;
    }
	
	private static String booleano(String expresion){
		String res = "";
		if(expresion.contains(" == ")){
			String[] vl = expresion.split(" == ");
			if(variables.v_revise(vl[0]).equals(variables.v_revise(vl[1]))){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" != ")){
			String[] vl = expresion.split(" != ");
			if(!variables.v_revise(vl[0]).equals(variables.v_revise(vl[1]))){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" ? ")){
			expresion = expresion.replace(" ? ", " p ");
			String[] vl = expresion.split(" p ");
			if(((String) variables.v_revise(vl[0])).contains(((String)variables.v_revise(vl[1])))){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" !? ")){
			expresion = expresion.replace(" !? ", " p ");
			String[] vl = expresion.split(" p ");
			if(!((String)variables.v_revise(vl[0])).contains((String)variables.v_revise(vl[1]))){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" <= ")){
			String[] vl = expresion.split(" <= ");
			float v1 = Float.parseFloat((String)variables.v_revise(vl[0]));
			float v2 = Float.parseFloat((String)variables.v_revise(vl[1]));
			if(v1 <= v2){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" >= ")){
			String[] vl = expresion.split(" >= ");
			float v1 = Float.parseFloat((String)variables.v_revise(vl[0]));
			float v2 = Float.parseFloat((String)variables.v_revise(vl[1]));
			if(v1 >= v2){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" < ")){
			String[] vl = expresion.split(" < ");
			float v1 = Float.parseFloat((String)variables.v_revise(vl[0]));
			float v2 = Float.parseFloat((String)variables.v_revise(vl[1]));
			if(v1 < v2){
				res = "True";
			}else{
				res = "False";
			}
		}else if(expresion.contains(" > ")){
			String[] vl = expresion.split(" > ");
			float v1 = Float.parseFloat((String)variables.v_revise(vl[0]));
			float v2 = Float.parseFloat((String)variables.v_revise(vl[1]));
			if(v1 > v2){
				res = "True";
			}else{
				res = "False";
			}
		}else if(variables.v_exists(expresion)){
			if(variables.v_revise(expresion).equals("True")){
				res = "True";
			}else if(variables.v_revise(expresion).equals("False")){
				res = "False";
			}else{
				IO.add_errorline(expresion + " is not boolean type");
			}
		}else if(functions.function_exists(expresion.split(" ")[0])){
			functions.call_function(expresion.split(" ")[0], expresion.substring(expresion.split(" ")[0].length() + 1));
			if(memory.func_return.equals("True")){
				res = "True";
			}else if(memory.func_return.equals("False")){
				res = "False";
			}else{
				IO.add_errorline(expresion + " is not boolean type");
			}
		}else if(expresion.equals("True")){
			res = "True";
		}else if(expresion.equals("False")){
			res = "False";
		}
		return res;
	}
}
