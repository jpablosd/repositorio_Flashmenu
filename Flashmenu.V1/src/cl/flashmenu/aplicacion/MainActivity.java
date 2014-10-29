package cl.flashmenu.aplicacion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private Button ver, admRestCrear, iniciar; 
	private TextView crearAdm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	   ver = (Button) findViewById(R.id.b1);
	   ver.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(getApplicationContext(), verMapa.class);
			startActivity(i);

			finish();
		}
	});
	   
	   admRestCrear = (Button) findViewById(R.id.b2);
	   admRestCrear.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(getApplicationContext(), crearAdmRestaurant.class);
			startActivity(i);

			finish();
		}
	});
	   
	   iniciar = (Button) findViewById(R.id.b3);
	   iniciar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(getApplicationContext(), iniciarSesion.class);
			startActivity(i);

			finish();
		}
	});
	   
	   	   
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
