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

public class crearAdmRestaurant extends Activity{
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText idAdm;
		EditText rutAdm;
		EditText nom;
		EditText apeP;
		EditText apeM;
		EditText pass;
		EditText email;

		// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de tu servidor 
		// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
		private static String url_create_Empleado = "http://192.168.50.10/PHP/FlashmenuPHP/nuevoAdmRestaurant.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearadmrestaurant);

			// Edit Text
			idAdm = (EditText) findViewById(R.id.idAdministrador);
			rutAdm = (EditText) findViewById(R.id.rut);
			nom = (EditText) findViewById(R.id.Nombre);
			apeP = (EditText) findViewById(R.id.Apellidopaterno);
			apeM = (EditText) findViewById(R.id.Apellidomaterno);
			pass = (EditText) findViewById(R.id.contrasena);
			email = (EditText) findViewById(R.id.email);
			
			
			// Create button
			Button btnCreateEmpleado = (Button) findViewById(R.id.crearadm);

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
				pDialog = new ProgressDialog(crearAdmRestaurant.this);
				pDialog.setMessage("Registrando al Adminstrador de restaurant..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Creating Empleado
			 * */
			protected String doInBackground(String... args) {
				
				String idadmRest = idAdm.getText().toString();
				String rutAdmRest = rutAdm.getText().toString();
				String name = nom.getText().toString();
				String apePaterno = apeP.getText().toString();
				String apeMaterno = apeM.getText().toString();
				String password = pass.getText().toString();
				String mail = email.getText().toString();

				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("idAdministrador_restaurant", idadmRest));
				params.add(new BasicNameValuePair("Adm_rut", rutAdmRest));
				params.add(new BasicNameValuePair("Adm_nombre", name));
				params.add(new BasicNameValuePair("Adm_apellidoPaterno", apePaterno));
				params.add(new BasicNameValuePair("Adm_apellidoMaterno", apeMaterno));
				params.add(new BasicNameValuePair("Adm_direccion", password));
				params.add(new BasicNameValuePair("Adm_email", mail));

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
