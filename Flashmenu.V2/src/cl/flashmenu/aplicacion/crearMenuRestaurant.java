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

public class crearMenuRestaurant extends Activity{
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText idAdm;
		EditText des;
		EditText nom;
		EditText precio;
		
		// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de tu servidor 
		// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
		private static String url_create_Empleado = "http://192.168.50.10/PHP/FlashmenuPHP/nuevoMenuRestaurant.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearmenurestaurant);

			// Edit Text
			idAdm = (EditText) findViewById(R.id.idMenu);
			nom = (EditText) findViewById(R.id.nombreMenu);
			des = (EditText) findViewById(R.id.descripcionMenu);
			precio = (EditText) findViewById(R.id.precioMenu);
			
			
			// Create button
			Button btnCreateEmpleado = (Button) findViewById(R.id.crearmenu);

			// button click event
			btnCreateEmpleado.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					// creating new Empleado in background thread
					new CreateNewEmpleado().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new Empleado
		 * */
		class CreateNewEmpleado extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(crearMenuRestaurant.this);
				pDialog.setMessage("Ingresando menu..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Creating Empleado
			 * */
			protected String doInBackground(String... args) {
				
				String idMenuRest = idAdm.getText().toString();
			    String name = nom.getText().toString();
				String desMenua = des.getText().toString();
				String precioMenu = precio.getText().toString();

				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("idMenu", idMenuRest));
				params.add(new BasicNameValuePair("Menu_nombre", name));
				params.add(new BasicNameValuePair("Menu_descripcion", desMenua));
				params.add(new BasicNameValuePair("Menu_precio", precioMenu));

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
						Intent i = new Intent(getApplicationContext(), iniciarSesion.class);
						startActivity(i);
						
						// closing this screen
						finish();
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
