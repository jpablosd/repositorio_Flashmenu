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
	
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText des;
		EditText nom;
		EditText precio;
		
		//private static String url_create_Rest = "http://190.153.212.77/daniel_fernandez/nuevoMenuRestaurant.php";
	//	private static String url_create_Rest = "http://10.40.3.149/PHP/FlashmenuPHP/nuevoMenuRestaurant.php";
		private static String url_create_Rest = "http://201.239.236.147/PHP/FlashmenuPHP/nuevoMenuRestaurant.php";
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearmenurestaurant);

			nom = (EditText) findViewById(R.id.nombreMenu);
			des = (EditText) findViewById(R.id.descripcionMenu);
			precio = (EditText) findViewById(R.id.precioMenu);
			
			
			Button btnCreateRest = (Button) findViewById(R.id.crearmenu);

			btnCreateRest.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
						new nuevoMenu().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new Empleado
		 * */
		class nuevoMenu extends AsyncTask<String, String, String> {

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
				
			    String name = nom.getText().toString();
				String desMenu = des.getText().toString();
				String preMenu = precio.getText().toString();

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("Menu_nombre", name));
				params.add(new BasicNameValuePair("Menu_descripcion", desMenu));
				params.add(new BasicNameValuePair("Menu_precio", preMenu));

				JSONObject json = jsonParser.makeHttpRequest(url_create_Rest,"POST", params);
				
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
