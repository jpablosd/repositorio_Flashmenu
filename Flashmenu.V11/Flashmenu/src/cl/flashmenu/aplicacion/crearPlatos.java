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

public class crearPlatos extends Activity{
	
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText des;
		EditText nom;
		EditText precio;
		
		String id;
		
		//private static String url_create_Rest = "http://190.153.212.77/daniel_fernandez/nuevoMenuRestaurant.php";
	//	private static String url_create_Rest = "http://10.40.3.149/PHP/FlashmenuPHP/nuevoMenuRestaurant.php";
		private static String url_create_Rest = "http://201.239.236.147/PHP/FlashmenuPHP/nuevoPlatos.php";
		private static final String TAG_SUCCESS = "success";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.crearplato);
			
			//RECIBIR DATOS POR INTENT
			Bundle extras = getIntent().getExtras();
			        if (extras != null) {
			     	   id  = extras.getString("id");//id restaurant
			     	   
			     	//	Toast toast1 = Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT);
					 //     toast1.show();
			     	   
			        }else{
			        	id="error";
			     	}///

			nom = (EditText) findViewById(R.id.nombrePlatos);
			des = (EditText) findViewById(R.id.descripcionPlatos);
			precio = (EditText) findViewById(R.id.precioPlatos);
			
			
			Button btnCreateRest = (Button) findViewById(R.id.crearPlatos);

			btnCreateRest.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
						new nuevoPlato().execute();
				}
			});
		}

		/**
		 * Background Async Task to Create new Empleado
		 * */
		class nuevoPlato extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(crearPlatos.this);
				pDialog.setMessage("Ingresando Plato..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Creating plato
			 * */
			protected String doInBackground(String... args) {
				
			 	String idd = id.toString();
			    String name = nom.getText().toString();
				String desPlatos = des.getText().toString();
				String prePlatos = precio.getText().toString();

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("Restaurant_idRestaurant", idd));
				params.add(new BasicNameValuePair("Platos_nombre", name));
				params.add(new BasicNameValuePair("Platos_descripcion", desPlatos));
				params.add(new BasicNameValuePair("Platos_precio", prePlatos));

				JSONObject json = jsonParser.makeHttpRequest(url_create_Rest,"POST", params);
				
				Log.d("Create Response", json.toString());

				try {
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {

				Intent i = new Intent(getApplicationContext(), perfilRestaurant.class);
						startActivity(i);
			
					//	finish();
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
				Toast toast1 = Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT);
			      toast1.show();
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
