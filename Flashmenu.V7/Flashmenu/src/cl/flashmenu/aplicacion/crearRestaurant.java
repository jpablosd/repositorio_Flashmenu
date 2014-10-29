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
import android.widget.Toast;

public class crearRestaurant extends Activity{
	
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText nomRest;
		EditText tipoRest;
		EditText descripcionRest;
		EditText caracteristicasRest;
		EditText emilRest;
		EditText direccionRest;
		
		String usu, id, idd;

		//private static String url_create_rest = "http://190.153.212.77/daniel_fernandez/nuevoRestaurant.php";
		private static String url_create_rest = "http://10.40.3.149/PHP/FlashmenuPHP/nuevoRestaurant.php";

		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearrestaurant);
			
			
			
			
			//RECIBIR DATOS POR INTENT
			Bundle extras = getIntent().getExtras();
			        if (extras != null) {
			     	   usu  = extras.getString("usuario");//usuario
			     	  id  = extras.getString("id");//usuario
			     	   
			        }else{
			     	   usu="error";
			     	   id = "error";
			     	}///
			 
			
			
		

     		nomRest = (EditText) findViewById(R.id.restnombre);
			tipoRest = (EditText) findViewById(R.id.resttipo);
			descripcionRest = (EditText) findViewById(R.id.restdescripcion);
			caracteristicasRest = (EditText) findViewById(R.id.restcaracteristicas);
			emilRest = (EditText) findViewById(R.id.restemail);
			direccionRest = (EditText) findViewById(R.id.restdireccion);
			
			
			Button btnCreateRest = (Button) findViewById(R.id.crearrest);

			btnCreateRest.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					new CreateNewrest().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new Empleado
		 * */
		class CreateNewrest extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(crearRestaurant.this);
				pDialog.setMessage("Registrando restaurant..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Creating rest
			 * */
			protected String doInBackground(String... args) {
				
				idd = id.toString();
				String n = nomRest.getText().toString();
				String t = tipoRest.getText().toString();
				String d = descripcionRest.getText().toString();
				String c = caracteristicasRest.getText().toString();
				String e = emilRest.getText().toString();
				String dr = direccionRest.getText().toString();
				
				

					List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("Administrador_restaurant_idAdministrador_restaurant", idd));
				params.add(new BasicNameValuePair("Rest_nombre", n));
				params.add(new BasicNameValuePair("Rest_tipo", t));
				params.add(new BasicNameValuePair("Rest_descripcion", d));
				params.add(new BasicNameValuePair("Rest_caracteristicas", c));
				params.add(new BasicNameValuePair("Rest_email", e));
				params.add(new BasicNameValuePair("Rest_direccion", dr));
			

				JSONObject json = jsonParser.makeHttpRequest(url_create_rest,"POST", params);
				
				Log.d("Create Response", json.toString());

				try {
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {

				Intent i = new Intent(getApplicationContext(), perfilRestaurant.class);
						startActivity(i);

			//finish();
					} else {
						// fallo
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				pDialog.dismiss();
			}

		}
		
		 @Override
			public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				int id = item.getItemId();
				if (id == R.id.action_settings) {
					return true;
				}
				return super.onOptionsItemSelected(item);
			}

}
