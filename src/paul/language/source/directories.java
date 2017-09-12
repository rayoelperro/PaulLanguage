package paul.language.source;

import java.io.File;

import android.os.Environment;

public class directories {
	public static void folder(String linea){
		if(linea.contains("folder new ")){
			String vl = linea.replace("folder new ", "");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					File fos = new File(syntax.a(vl));
					fos.mkdirs();
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
		
		else if(linea.contains("folder delete ")){
			String vl = linea.replace("folder delete ", "");
			try {
				String state = Environment.getExternalStorageState();
				 
				if (Environment.MEDIA_MOUNTED.equals(state)) {
					File fos = new File(syntax.a(vl));
					eliminar_carpetas(fos);
				} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				} else {
				}

			} catch (Exception e) {
			}
		}
	}
	
	public static void eliminar_carpetas(File carpeta){
		File[] ficheros = carpeta.listFiles();
		for (int x = 0; x < ficheros.length; x++){
			if (ficheros[x].isDirectory()){
				eliminar_carpetas(ficheros[x]);
			}
			ficheros[x].delete();
		}
		carpeta.delete();
	}
}