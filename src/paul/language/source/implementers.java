package paul.language.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.os.Environment;

public class implementers {
	public static void def(String linea){
		String valor = linea.replaceFirst("implements ", "");
		if(valor.equals("math")){
			imp_to_var("E_math",String.valueOf(Math.E));
			imp_to_var("PI_math",String.valueOf(Math.PI));
		}else if(valor.equals("path")){
			imp_to_var("SD_path",String.valueOf(Environment.getExternalStorageDirectory() + "/"));
			imp_to_var("DW_path",String.valueOf(Environment.getExternalStorageDirectory() + "/Download/"));
			imp_to_var("CA_path",String.valueOf(Environment.getExternalStorageDirectory() + "/DCIM/Camera/"));
			imp_to_var("MS_path",String.valueOf(Environment.getExternalStorageDirectory() + "/Music/"));
		}else if(valor.equals("kernel")){
			frm("implements from " + String.valueOf(Environment.getExternalStorageDirectory() + "/kernel/paul.k"));
		}else{
			IO.add_errorline("The implementer: " + valor + " doesn't exist");
		}
	}
	
	public static void frm(String linea){
		String valor = linea.replaceFirst("implements from ", "");
		try {
			String state = Environment.getExternalStorageState();
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				File check = new File(valor);
				if(check.exists()){
					FileReader fos = new FileReader(valor);
			  		BufferedReader arch = new BufferedReader(fos);
			  		String texto = "";
			  		String lectura = "";
			  		while((lectura = arch.readLine()) != null){
			  			texto += "\n" + lectura;
			  		}
			  		callout(texto);
				}else{
					IO.add_errorline("the implementer in " + valor + " doesn't exits");
					memory.encendido = false;
				}
			} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			} else {
			}

		} catch (Exception e) {
		}
	}
	
	public static void callout(String lineas){
		String[] lt = lineas.split("\n");
		for(int x = 0; x < lt.length; x++){
			String[] defs = lt[x].split(" ");
			if(defs[0].equals("var")){
				variables.add_var(lt[x].substring(4), paul_values.normalize_var);
			}else if(defs[0].equals("using")){
				usings.utilizar(lt[x]);
			}else if(defs[0].equals("implements")){
				if(defs[1].equals("from"))
					implementers.frm(lt[x]);
				else
					implementers.def(lt[x]);
			}
		}
	}
	
	public static void imp_to_var(String nombre, String valor){
		variables.add_var(nombre + " = " + valor, paul_values.normalize_var);
	}
}
