package cl.flashmenu.aplicacion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class perfilRestaurant extends Activity {

	Button ingresarmenu, modificarmenu, modificarrest, volver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfilrestaurant);
		
		modificarmenu = (Button) findViewById(R.id.b10);
		modificarmenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);

				//finish();
			}
		});
		
		ingresarmenu = (Button) findViewById(R.id.b11);
		ingresarmenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), crearMenuRestaurant.class);
				startActivity(i);

			//	finish();
			}
		});
		
		modificarrest = (Button) findViewById(R.id.ModifRest);
		modificarrest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), modificarRestaurant.class);
				startActivity(i);

				//finish();
			}
		});
		
		volver = (Button) findViewById(R.id.volver);
		volver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), perfilAdmRestaurant.class);
				startActivity(i);

				//finish();
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