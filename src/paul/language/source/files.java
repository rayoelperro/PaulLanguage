package paul.language.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;

import android.os.Environment;

public class files {
	public static File file(String linea){
		if(linea.contains("file new ")){
			String vl = linea.replaceFirst("file new ", "");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					File fos = new File(syntax.a(vl));
					FileOutputStream file = new FileOutputStream(fos);
				    file.close();
				    return fos;
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		else if(linea.contains("file write ")){
			String[] vl = linea.replaceFirst("file write ", "").split(",");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					File fos = new File(syntax.a(vl[0]));
					FileOutputStream file = new FileOutputStream(fos);
				    file.write(syntax.a(vl[1]).getBytes());
					file.close();
				    return fos;
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		else if(linea.contains("file read ")){
			String[] vl = linea.replaceFirst("file read ", "").split(",");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					FileReader fos = new FileReader(syntax.a(vl[0]));
					BufferedReader a = new BufferedReader(fos);
				  	String texto = "";
				  	String lectura = "";
				  	while((lectura = a.readLine()) != null){
				  		texto += lectura;
				  	}
				  	variables.v_revalue(vl[1], texto);
					return new File(syntax.a(vl[0]));
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		else if(linea.contains("file show ")){
			String vl = linea.replaceFirst("file show ", "");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					FileReader fos = new FileReader(syntax.a(vl));
					BufferedReader a = new BufferedReader(fos);
				  	String texto = "";
				  	String lectura = "";
				  	while((lectura = a.readLine()) != null){
				  		texto += lectura;
				  	}
				  	IO.add_outline(texto);
					return new File(syntax.a(vl));
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		else if(linea.contains("file delete ")){
			String vl = linea.replaceFirst("file delete ", "");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					File f = new File(syntax.a(vl));
					f.delete();
					return f;
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		else if(linea.contains("file bytes ")){
			String[] vl = linea.replaceFirst("file bytes ", "").split(",");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					InputStream is = IO.input(vl[1]);
					FileOutputStream fos = new FileOutputStream(syntax.a(vl[0]));
					byte[] array = new byte[1000];
					int leido = is.read(array);
					while (leido > 0) {
						fos.write(array, 0, leido);
						leido = is.read(array);
					}
					is.close();
					fos.close();
					return new File(syntax.a(vl[0]));
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public static InputStream input(String archivo){
		File fl = new File(archivo);
		if(fl.exists()){
			try {
				return new FileInputStream(fl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}