package cl.flashmenu.aplicacion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class perfilAdmRestaurant extends Activity {

	Button ingresarrestaurant, modificarAdm, haciaPerRest, salir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfiladmrestaurant);
		
		modificarAdm = (Button) findViewById(R.id.b5);
		modificarAdm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), modificarAdmRestaurant.class);
				startActivity(i);

				//finish();
			}
		});
		
		
		ingresarrestaurant = (Button) findViewById(R.id.b6);
		ingresarrestaurant.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), crearRestaurant.class);
				startActivity(i);

				//finish();
			}
		});
		
		
		haciaPerRest = (Button) findViewById(R.id.haciaPerfilRest);
		haciaPerRest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), perfilRestaurant.class);
				startActivity(i);

				//finish();
			}
		});
		
		salir = (Button) findViewById(R.id.salirPerfilAdm);
		salir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
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