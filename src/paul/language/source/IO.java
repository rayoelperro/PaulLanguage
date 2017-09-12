package paul.language.source;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.widget.ArrayAdapter;
import android.widget.Toast;
import elorza.paul.language.*;

public class IO {
	public static void add_outline(String texto){
		MainActivity.vars.add(texto);
		MainActivity.adaptador = new ArrayAdapter<String>(memory.actual,android.R.layout.simple_list_item_1,MainActivity.vars);
		MainActivity.list.setAdapter(MainActivity.adaptador);
		MainActivity.list.setSelection(MainActivity.adaptador.getCount() - 1);
	}
	
	public static void add_errorline(String texto){
		MainActivity.vars.add("Error: " + texto);
		MainActivity.adaptador = new ArrayAdapter<String>(memory.actual,android.R.layout.simple_list_item_1,MainActivity.vars);
		MainActivity.list.setAdapter(MainActivity.adaptador);
		MainActivity.list.setSelection(MainActivity.adaptador.getCount() - 1);
		memory.encendido = false;
	}
	
	public static void toast(String texto){
		Toast t = Toast.makeText(memory.actual, texto, Toast.LENGTH_LONG);
		t.show();
	}
	
	public static InputStream input(String check){
		if(check.startsWith("url ")){
			return url_input(syntax.a(check.substring(4)));
		}
		else if(check.startsWith("file ")){
			return files.input(syntax.a(check.substring(5)));
		}
		return variables.input_revise(check);
	}
	
	public static InputStream url_input(String rta){
		try{
			URL url = new URL(syntax.a(rta));
			URLConnection urlCon = url.openConnection();
			return urlCon.getInputStream();
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static void out(String linea){
		if(linea.equals("out no")){
			memory.encendido = false;
		}else if(linea.equals("out clear")){
			MainActivity.vars.clear();
			MainActivity.adaptador = new ArrayAdapter<String>(memory.actual,android.R.layout.simple_list_item_1,MainActivity.vars);
			MainActivity.list.setAdapter(MainActivity.adaptador);
			MainActivity.list.setSelection(MainActivity.adaptador.getCount() - 1);
		}else if(linea.equals("out cut")){
			add_outline("");
		}else{
			IO.add_errorline(linea);
		}
	}
}