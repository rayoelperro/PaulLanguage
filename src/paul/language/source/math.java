package paul.language.source;

import org.mariuszgromada.math.mxparser.Expression;

public class math {
	public static String math_analizar(String expresion) {
		Expression e = new Expression(expresion);
		String res = String.valueOf(e.calculate());
		if(res.equals("NaN"))
			res = "!</>";
		return res;
	}
}
