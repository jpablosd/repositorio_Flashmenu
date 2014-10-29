package cl.flashmenu.aplicacion;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.flashmenu.aplicacion.infoRestaurantes.Loadrest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class perfilAdmRestaurant extends Activity {

	Button ingresarrestaurant, modificarAdm, haciaPerRest, salir;
	String usuario, id;
	
	
	private ProgressDialog pDialog;

	   JSONParser jParser = new JSONParser();
	   

	   private static String url_all_inforest = "http://10.40.3.149/PHP/FlashmenuPHP/perfilAdm.php";
	   //private static String url_all_inforest = "http://190.153.212.77/daniel_fernandez/perfilAdm.php"
		

	JSONArray rest = null;
	
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ID = "id";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfiladmrestaurant);
		new Loadrest().execute();
		
		//RECIBIR DATOS POR INTENT
		Bundle extras = getIntent().getExtras();
		        if (extras != null) {
		     	   usuario  = extras.getString("usuario");//usuario
		     	   
		        }else{
		     	   usuario="error";
		     	}///
		 
	
		
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
				 i.putExtra("usuario",usuario);
				 i.putExtra("id",id);
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
	
	
	///////
	
	class Loadrest extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(perfilAdmRestaurant.this);
			pDialog.setMessage("Cargando informacion. Espere porfavor...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All empleados from url
		 * */
		protected String doInBackground(String... args) {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("buscar", usuario));
			
			JSONObject json = jParser.makeHttpRequest(url_all_inforest, "POST", params);
			
			Log.d("All empleados: ", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					rest = json.getJSONArray(TAG_ID);

					for (int i = 0; i < rest.length(); i++) {
						JSONObject c = rest.getJSONObject(i);

						id = c.getString(TAG_ID);
						
					}
				} else {

			Intent i = new Intent(getApplicationContext(), perfilAdmRestaurant.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
	}
		
		
		
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			
			Toast toast1 = Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT);
		      toast1.show();
		}
	}
	///////
	
	
	
	

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