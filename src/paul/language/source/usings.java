package paul.language.source;

import java.io.*;

import android.os.Environment;

public class usings {
	public static void utilizar(String linea){
		String valor = linea.substring(6);
		File rta = new File(valor);
		if(rta.exists()){
			memory.using_ruta.add(rta.getPath());
			if(rta.getName().contains("."))
				memory.using_nombre.add(rta.getName().substring(0,rta.getName().lastIndexOf(".")));
			else
				memory.using_nombre.add(rta.getName());
		}
		else{
			IO.add_errorline("The class doesn't exist");
		}
	}
	
	public static void call(String nombre, String parametros){
		int l = memory.using_nombre.indexOf(nombre);
		String srl = parametros.substring(1).replace(")", "");
		if(srl.contains(",")){
			String[] _parametros = srl.split(",");
			try{
				memory.using_params.clear();
				for(String p : _parametros)
					memory.using_params.add(syntax.a(p));
			}catch(Exception e){
				
			}
		}else{
			try{
				memory.using_params.clear();
				memory.using_params.add(srl);
			}catch(Exception e){
				
			}
		}
		try {
			String state = Environment.getExternalStorageState();
			 
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				FileReader fos = new FileReader(memory.using_ruta.get(l).toString());
			  	BufferedReader arch = new BufferedReader(fos);
			  	String texto = "()";
			  	String lectura = "";
			  	while((lectura = arch.readLine()) != null){
			  		texto += "\n" + lectura;
			  	}
			  	entry.por_lineas(texto);
			} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			} else {
			}

		} catch (Exception e) {
		}
		memory.using_params.clear();
	}
	
	public static void import_params(String valores){
		String[] _variables = valores.substring(1).split(",");
		if(_variables[_variables.length - 1].contains(")")){
			_variables[_variables.length - 1] = _variables[_variables.length - 1].replace(")", "");
			for(int x = 0; x < _variables.length; x++){
				if(variables.v_exists(_variables[x]))
					variables.v_revalue(_variables[x], memory.using_params.get(x).toString());
				else
					variables.add_var(_variables[x] + " = " + memory.using_params.get(x).toString(), paul_values.normalize_var);
			}
		}
		memory.using_params.clear();
	}
	
	public static boolean using_exists(String valor){
		if(memory.using_nombre.contains(valor))
			return true;
		return false;
	}
}
