package paul.language.source;

public class http {
	public static void httpuse(String line, String[] sep){
		if(sep[1].equals("=") && sep.length == 3)
			memory.http = new httpWebServer(Integer.parseInt(sep[2]));
		else if(sep[1].equals("start") && sep.length == 2)
			memory.http.start();
		else if(sep[1].equals("stop") && sep.length == 2)
			memory.http.stop();
		else if(sep[1].equals("handler"))
			memory.http.setNewHandle(sep[2], syntax.a(line.substring(14 + sep[2].length())));
		else if(sep[1].equals("mode") && sep.length == 3){
			if(sep[2].equals("handle"))
				memory.http.mode = httpWebServer.HANDLE;
			else if(sep[2].equals("navigate"))
				memory.http.mode = httpWebServer.NAVIGATE;
			else
				IO.add_errorline("http mode " + sep[2] + " doesn't exist");
		}else
			IO.add_errorline(line);
				
	}
	
	public static void delete(){
		if(memory.http != null){
			memory.http.stop();
			memory.http = null;
		}
	}
}
