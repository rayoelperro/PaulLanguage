package paul.language.source;

import java.io.File;
import java.util.ArrayList;
import android.content.Context;


public class memory {
	//Variables
	public static ArrayList var_n = new ArrayList();
	public static ArrayList var_d = new ArrayList();
	public static ArrayList var_f = new ArrayList();
	//While
	public static boolean while_leyendo = false;
	public static String while_lineas = "";
	public static String while_exp_revise = "";
	//Using
	public static ArrayList using_ruta = new ArrayList();
	public static ArrayList using_nombre = new ArrayList();
	public static ArrayList using_params = new ArrayList();
	//Function
	public static ArrayList func_name = new ArrayList();
	public static ArrayList func_params = new ArrayList();
	public static ArrayList func_lines = new ArrayList();
	public static boolean func_loading = false;
	public static Object func_return = null;
	//Condicionantes
	public static boolean if_bql = false;
	public static boolean pre_blq = false;
	public static boolean else_bql = false;
	//Others
	public static httpWebServer http = null;
	public static boolean logic_on = false;
	public static boolean math_on = false;
	public static String linklast = "";
	public static File dir;
	public static String pack = "";
	public static boolean comment = false;
	//Aplication
	public static boolean encendido = true;
	public static Context actual = null;
	public static final String version = "1.9.4.6s";
}