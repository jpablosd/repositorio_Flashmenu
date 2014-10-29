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
	
	
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		
		EditText rutAdm;
		EditText nom;
		EditText apeP;
		EditText apeM;
		EditText pass;
		EditText email;
		
	//private static String url_create_Adm = "http://190.153.212.77/daniel_fernandez/nuevoAdmRestaurant.php";
	//private static String url_create_Adm = "http://10.40.3.149/PHP/FlashmenuPHP/nuevoAdmRestaurant.php";
		private static String url_create_Adm = "http://201.239.236.147/PHP/FlashmenuPHP/nuevoAdmRestaurant.php";
	
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearadmrestaurant);

			
			rutAdm = (EditText) findViewById(R.id.rut);
			nom = (EditText) findViewById(R.id.Nombre);
			apeP = (EditText) findViewById(R.id.Apellidopaterno);
			apeM = (EditText) findViewById(R.id.Apellidomaterno);
			pass = (EditText) findViewById(R.id.contrasena);
			email = (EditText) findViewById(R.id.email);
			
			
			
			Button btnCreateAdm = (Button) findViewById(R.id.crearadm);

			
			btnCreateAdm.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
				
					new CreateNewAdm().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new ADM
		 * */
		class CreateNewAdm extends AsyncTask<String, String, String> {

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
			 * Creating adm
			 * */
			protected String doInBackground(String... args) {
				
				String rutAdmRest = rutAdm.getText().toString();
				String name = nom.getText().toString();
				String apePaterno = apeP.getText().toString();
				String apeMaterno = apeM.getText().toString();
				String password = pass.getText().toString();
				String mail = email.getText().toString();

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
					params.add(new BasicNameValuePair("Adm_rut", rutAdmRest));
				params.add(new BasicNameValuePair("Adm_nombre", name));
				params.add(new BasicNameValuePair("Adm_apellidoPaterno", apePaterno));
				params.add(new BasicNameValuePair("Adm_apellidoMaterno", apeMaterno));
				params.add(new BasicNameValuePair("Adm_direccion", password));
				params.add(new BasicNameValuePair("Adm_email", mail));

				JSONObject json = jsonParser.makeHttpRequest(url_create_Adm,"POST", params);
				
				Log.d("Create Response", json.toString());

				try {
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
							Intent i = new Intent(getApplicationContext(), iniciarSesion.class);
						startActivity(i);
						
					} else {
						// fallo
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
