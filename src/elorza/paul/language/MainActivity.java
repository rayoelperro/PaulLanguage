package elorza.paul.language;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import paul.language.source.*;

public class MainActivity extends Activity{

	//Elementos
	public static ListView list;
	public static EditText msg;
	public static ArrayList<String> vars = new ArrayList<String>();
    public static ArrayAdapter<String> adaptador;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
    	list=(ListView)findViewById(R.id.listView1);
    	msg = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_run) {
			entry.receptor(msg.getText().toString(), this);
		}else if (id == R.id.action_save) {
			
		}
		return super.onOptionsItemSelected(item);
	}
}
