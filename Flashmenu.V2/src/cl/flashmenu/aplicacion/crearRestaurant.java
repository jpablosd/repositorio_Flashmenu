package cl.flashmenu.aplicacion;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.EditText;

public class crearRestaurant extends Activity{
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText idRest;
		EditText nomRest;
		EditText tipoRest;
		EditText descripcionrest;
		EditText caracteristicasRest;
		EditText emilRest;

		// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de tu servidor 
		// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
		private static String url_create_Empleado = "http://192.168.50.10/PHP/FlashmenuPHP/nuevoRestaurant.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearrestaurant);

			// Edit Text
			idRest = (EditText) findViewById(R.id.restid);
			nomRest = (EditText) findViewById(R.id.restnombre);
			tipoRest = (EditText) findViewById(R.id.resttipo);
			descripcionrest = (EditText) findViewById(R.id.restdescripcion);
			caracteristicasRest = (EditText) findViewById(R.id.restcaracteristicas);
			emilRest = (EditText) findViewById(R.id.restemail);
			
			
			
			// Create button
			Button btnCreateEmpleado = (Button) findViewById(R.id.crearrest);

			// button click event
			btnCreateEmpleado.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					// creating new Empleado in background thread
					new CreandoRestaurant().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new Empleado
		 * */
		class CreandoRestaurant extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(crearRestaurant.this);
				pDialog.setMessage("Registrando el restaurant..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Creating Empleado
			 * */
			protected String doInBackground(String... args) {
				
				String id = idRest.getText().toString();
				String nom = nomRest.getText().toString();
				String tipo = tipoRest.getText().toString();
				String descrip = descripcionrest.getText().toString();
				String caract = caracteristicasRest.getText().toString();
				String email = emilRest.getText().toString();
				
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("idRestaurant", id));
				params.add(new BasicNameValuePair("Rest_nombre", nom));
				params.add(new BasicNameValuePair("Rest_tipo", tipo));
				params.add(new BasicNameValuePair("Rest_descripcion", descrip));
				params.add(new BasicNameValuePair("Rest_caracteristicas", caract));
				params.add(new BasicNameValuePair("Rest_email", email));

				// getting JSON Object
				// Note that create Empleado url accepts POST method
				JSONObject json = jsonParser.makeHttpRequest(url_create_Empleado,"POST", params);
				
				// check log cat fro response
				Log.d("Create Response", json.toString());

				// check for success tag
				try {
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						// successfully created Empleado
						Intent i = new Intent(getApplicationContext(), perfilRestaurant.class);
						startActivity(i);
						
						// closing this screen
						//finish();
					} else {
						// failed to create Empleado
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once done
				pDialog.dismiss();
			}
			
			

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
