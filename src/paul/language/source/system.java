package paul.language.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import android.content.Intent;
import android.net.Uri;

public class system {
	
	public static void intent(String vla){
		String[] vl = vla.split(" : ");
		Uri uri = Uri.parse(syntax.a(vl[1]));
        Intent intent = new Intent(syntax.a(vl[0]), uri);
        memory.actual.startActivity(intent);
	}
	
	public static void runtime(String orden) {
        try {
        	ProcessBuilder  pb = new ProcessBuilder().command(orden).directory(memory.dir);
        	Process p = pb.start();
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String linea = null;
            while ((linea = in.readLine()) != null) {
                IO.add_outline(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Object call_method(String _class, String _method, String _params){
		try{
			Class<?> c = Class.forName(_class);
			Method[] alls = c.getMethods();
			Method testMethod = null;
			for(Method inn : alls){
				if(inn.getName().equals(_method)){
					testMethod = inn;
					if(_params.contains("///"))
						return testMethod.invoke(c.newInstance(), _params.split("///"));
					else
						return testMethod.invoke(c.newInstance(), _params);
				}
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static boolean can_integer(String valor){
		try{
			Integer.parseInt(valor);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean can_float(String valor){
		try{
			Float.parseFloat(valor);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}