package paul.language.source;
 
import android.text.TextUtils;
 
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
 

public class httpWebServer implements Runnable {
    private final int port;
    private boolean serverOn;
    public int mode = 0;
    public static final int NAVIGATE = 0;
    public static final int HANDLE = 1;

    private ServerSocket ssocket;
    
    private ArrayList<String> handName;
    
    private ArrayList<String> handContent;
    
    public httpWebServer(int port) {
        this.port = port;
        handName = new ArrayList<String>();
        handContent = new ArrayList<String>();
    }
    
    public void start() {
        serverOn = true;
        new Thread(this).start();
    }
    
    public void stop() {
        try {
            serverOn = false;
            if (null != ssocket) {
                ssocket.close();
                ssocket = null;
            }
        } catch (IOException e) {
        	
        }
    }
 
    public int getPort() {
        return port;
    }
 
    @Override
    public void run() {
        try {
            ssocket = new ServerSocket(port);
            while (serverOn) {
                Socket socket = ssocket.accept();
                handle(socket);
                socket.close();
            }
        } catch (SocketException e) {
        } catch (IOException e) {
        	
        }
    }
    
    private void handle(Socket socket) throws IOException {
        BufferedReader reader = null;
        PrintStream output = null;
        try {
            String route = null;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (!TextUtils.isEmpty(line = reader.readLine())) {
                if (line.startsWith("GET /")) {
                    int start = line.indexOf('/') + 1;
                    int end = line.indexOf(' ', start);
                    route = line.substring(start, end);
                    break;
                }
            }
            output = new PrintStream(socket.getOutputStream());
            if (null == route) {
                writeServerError(output);
                return;
            }
            byte[] bytes = loadContent(route);
            if (null == bytes) {
                writeServerError(output);
                return;
            }
            output.println("HTTP/1.0 200 OK");
            if(mode == HANDLE)
            	output.println("Content-Type: text/html");
            else
            	output.println("Content-Type: " + detectMimeType(route));
            output.println("Content-Length: " + bytes.length);
            output.println();
            output.write(bytes);
            output.flush();
        } finally {
            if (null != output) {
                output.close();
            }
            if (null != reader) {
                reader.close();
            }
        }
    }
    
    private void writeServerError(PrintStream output) {
        output.println("HTTP/1.0 500 Internal Server Error");
        output.flush();
    }
    
    public void setNewHandle(String hname, String hcontent){
    	handName.add(hname);
    	handContent.add(hcontent);
    }
    
    private byte[] loadContent(String fileName) throws IOException {
    	if(mode == HANDLE)
    		if(handName.contains(fileName))
    			return handContent.get(handName.indexOf(fileName)).toString().getBytes();
        InputStream input = null;
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            input = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int size;
            while (-1 != (size = input.read(buffer))) {
                output.write(buffer, 0, size);
            }
            output.flush();
            return output.toByteArray();
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if (null != input) {
                input.close();
            }
        }
    }
    
    private String detectMimeType(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        } else if (fileName.endsWith(".html")) {
            return "text/html";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else {
            return "application/octet-stream";
        }
    }
 
}