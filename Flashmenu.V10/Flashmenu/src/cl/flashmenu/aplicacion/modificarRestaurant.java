package cl.flashmenu.aplicacion;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class modificarRestaurant extends Activity {

	
	EditText nom;
	EditText tipo;
	EditText descrip;
	EditText caract;
	EditText email;
	EditText direccion;
	

	Button btnSave2;
	Button btnDelete2;

	String n;

	
	private ProgressDialog pDialog;
	
	
	JSONParser jsonParser = new JSONParser();

	
    //private static final String url_detalles_rest = "http://190.153.212.77/daniel_fernandez/datosRestaurant.php";	
   // private static String url_detalles_rest = "http://10.40.3.149/PHP/FlashmenuPHP/datosRestaurant.php";
    private static String url_detalles_rest = "http://201.239.236.147/PHP/FlashmenuPHP/datosRestaurant.php";
    
	
	//private static final String url_actualizar_rest = "http://190.153.212.77/daniel_fernandez/actualizarRestaurant.php";
	//private static final String url_actualizar_rest = "http://10.40.3.149/PHP/FlashmenuPHP/actualizarRestaurant.php";
	private static final String url_actualizar_rest = "http://201.239.236.147/PHP/FlashmenuPHP/actualizarRestaurant.php";


	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_restaurant = "restaurant";
	
	private static final String TAG_TIPO = "tipo";
	private static final String TAG_DESCRIPCION = "descrip";
	private static final String TAG_CARACTERISTICAS = "caract";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_DIRECCION = "direccion";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modificarrestaurant);
		
        new GetRestDetails().execute();
		
      
        tipo = (EditText) findViewById(R.id.tipoModif);
        descrip = (EditText) findViewById(R.id.descripcionModif);
        caract = (EditText) findViewById(R.id.caracteristicasModif);
        email = (EditText) findViewById(R.id.emailRestModif);
        direccion = (EditText) findViewById(R.id.direccionModif);
        
        
        // save button
		btnSave2 = (Button) findViewById(R.id.btnSave2);
		btnDelete2 = (Button) findViewById(R.id.btnDelete2);

		
           n ="MAIA";


		btnSave2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				new Saverest().execute();
			}
		});

		
		btnDelete2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
			}
		});

	}

	/**
	 * Background Async Task to Get complete details
	 * */
	class GetRestDetails extends AsyncTask<String, String, String> {

	
	
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarRestaurant.this);
			pDialog.setMessage("Cargando datos. Porfavor espere...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
	
		
		protected String doInBackground(String... params) {

		
			Thread thread = new Thread(new Runnable(){
				public void run() {
				
					int success;
					try {
						
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("Rest_nombre", n));

					
						JSONObject json = jsonParser.makeHttpRequest(url_detalles_rest, "GET", params);

					
						Log.d("Detalles restaurant", json.toString());
						
					
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
						
							JSONArray EmpleadoObj = json.getJSONArray(TAG_restaurant); // JSON Array
							
						
							JSONObject rest = EmpleadoObj.getJSONObject(0);

				
							tipo = (EditText) findViewById(R.id.tipoModif);
							descrip = (EditText) findViewById(R.id.descripcionModif);
							caract = (EditText) findViewById(R.id.caracteristicasModif);
							email = (EditText) findViewById(R.id.emailRestModif);
							direccion = (EditText) findViewById(R.id.direccionModif);
							
					

				
							tipo.setText(rest.getString(TAG_TIPO));
							descrip.setText(rest.getString(TAG_DESCRIPCION));
							caract.setText(rest.getString(TAG_CARACTERISTICAS));
							email.setText(rest.getString(TAG_EMAIL));
							direccion.setText(rest.getString(TAG_DIRECCION));

						}else{
						
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			
			thread.start();

			return null;
		}


		
		 
		protected void onPostExecute(String file_url) {
			
			pDialog.dismiss();
		}
	}

	/**
	 * Background Async Task to  Save Details
	 * */
	class Saverest extends AsyncTask<String, String, String> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarRestaurant.this);
			pDialog.setMessage("Actualizando Restaurant ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
		
		 
		protected String doInBackground(String... args) {

			
			String t = tipo.getText().toString();
			String d = descrip.getText().toString();
			String c = caract.getText().toString();
			String e = email.getText().toString();
			String dir = direccion.getText().toString();

		
			List<NameValuePair> params = new ArrayList<NameValuePair>();
		
			params.add(new BasicNameValuePair("Rest_nombre", n));
			params.add(new BasicNameValuePair("Rest_tipo", t));
			params.add(new BasicNameValuePair("Rest_descripcion", d));
			params.add(new BasicNameValuePair("Rest_caracteristicas", c));
			params.add(new BasicNameValuePair("Rest_email", e));
			params.add(new BasicNameValuePair("Rest_direccion", dir));

		
			JSONObject json = jsonParser.makeHttpRequest(url_actualizar_rest, "POST", params);

		
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					
					
					Intent i = getIntent();
		
					setResult(100, i);
		     		finish();
					
					
				} else {
					
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

			
	//						}
		//else{
		//Toast toast1 = Toast.makeText(getApplicationContext(),"Error: rut no existe.", Toast.LENGTH_SHORT);
	 	 //   toast1.show();
	//	}
		
			return null;
		}


		
		
		
		protected void onPostExecute(String file_url) {
		
			pDialog.dismiss();
		}
		
	}

	/*****************************************************************
	 * Background Async Task to Delete Empleado
	 * */
	/*class Delete extends AsyncTask<String, String, String> {

		
		 //Before starting background thread Show Progress Dialog
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Borrando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

	
		 
		protected String doInBackground(String... args) {

			// Check for success tag
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cedula", cedula));

				
				JSONObject json = jsonParser.makeHttpRequest(
						url_borrar_empleado, "POST", params);

				
				Log.d("Borrando", json.toString());
				
			
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
				
					Intent i = getIntent();
				
					setResult(100, i);
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		
	
		 
		protected void onPostExecute(String file_url) {
		
			pDialog.dismiss();

		}

	}*/
}
