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

public class modificarAdmRestaurant extends Activity {


    EditText nom;
	EditText apeP;
	EditText apeM;
	EditText pass;
	EditText email;
	
	Button btnSave;
	Button btnDelete;

	String cedula;

	
	private ProgressDialog pDialog;


	JSONParser jsonParser = new JSONParser();

	// private static final String url_detalles_adm = "http://190.153.212.77/daniel_fernandez/datosAdmRestaurant.php";
	private static String url_detalles_adm = "http://10.40.3.149/PHP/FlashmenuPHP/datosAdmRestaurant.php";
	
	
   //  private static final String url_actualizar_adm = "http://190.153.212.77/daniel_fernandez/actualizarAdmRestaurant.php";
   private static String url_actualizar_adm = "http://10.40.3.149/PHP/FlashmenuPHP/actualizarAdmRestaurant.php";
	 
   //  private static final String url_borrar_adm = "http://190.153.212.77/daniel_fernandez/borrarAdmRestaurant.php";
     private static String url_borrar_adm = "http://10.40.3.149/PHP/FlashmenuPHP/borrarAdmRestaurant.php";
 	
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_EMPLEADOS = "adminstrador_restaurant";
	
		
	
	private static final String TAG_NOMBRE = "nom";
	private static final String TAG_APELLIDOPATERNO = "apep";
	private static final String TAG_APELLIDOMATERNO = "apem";
	private static final String TAG_CONTRASENA = "dir";
	private static final String TAG_EMAIL = "email";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modificaradmrestaurant);
		
        new GetEmpleadoDetails().execute();
		
        nom = (EditText) findViewById(R.id.NombreModif);
        apeP = (EditText) findViewById(R.id.ApellidopaternoModif);
        apeM = (EditText) findViewById(R.id.ApellidomaternoModif);
        email = (EditText) findViewById(R.id.emailModif);
        pass = (EditText) findViewById(R.id.contrasenaModif);
        
        // save button
		btnSave = (Button) findViewById(R.id.btnSave);
		btnDelete = (Button) findViewById(R.id.btnDelete);

           cedula ="123";


		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				new SaveAdm().execute();
			}
		});

		// Delete button click event
		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				//new Delete().execute();
			}
		});

	}

	/**
	 * Background Async Task to Get complete Empleado details
	 * */
	class GetEmpleadoDetails extends AsyncTask<String, String, String> {

	
	
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
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
						params.add(new BasicNameValuePair("Adm_rut", cedula));

						
						JSONObject json = jsonParser.makeHttpRequest(url_detalles_adm, "GET", params);

					
						Log.d("Detalles adm", json.toString());
						
						
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							
							JSONArray EmpleadoObj = json.getJSONArray(TAG_EMPLEADOS); // JSON Array
							
						
							JSONObject Empleado = EmpleadoObj.getJSONObject(0);

						
							nom = (EditText) findViewById(R.id.NombreModif);
							apeP = (EditText) findViewById(R.id.ApellidopaternoModif);
							apeM = (EditText) findViewById(R.id.ApellidomaternoModif);
							pass = (EditText) findViewById(R.id.contrasenaModif);
							email = (EditText) findViewById(R.id.emailModif);


							nom.setText(Empleado.getString(TAG_NOMBRE));
							apeP.setText(Empleado.getString(TAG_APELLIDOPATERNO));
							apeM.setText(Empleado.getString(TAG_APELLIDOMATERNO));
							pass.setText(Empleado.getString(TAG_CONTRASENA));
							email.setText(Empleado.getString(TAG_EMAIL));

						}else{

                 ///
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
	 * Background Async Task to  Save Empleado Details
	 * */
	class SaveAdm extends AsyncTask<String, String, String> {

		
		
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Actualizando Empleado ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
		
		 
		protected String doInBackground(String... args) {



			String n = nom.getText().toString();
			String ap = apeP.getText().toString();
			String am = apeM.getText().toString();
			String p = pass.getText().toString();
			String e = email.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
		

			params.add(new BasicNameValuePair("Adm_nombre", n));
			params.add(new BasicNameValuePair("Adm_apellidoPaterno", ap));
			params.add(new BasicNameValuePair("Adm_apellidoMaterno", am));
			params.add(new BasicNameValuePair("Adm_direccion", p));
			params.add(new BasicNameValuePair("Adm_email", e));



			JSONObject json = jsonParser.makeHttpRequest(url_actualizar_adm, "POST", params);

		
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					
				
					Intent i = getIntent();
		
					setResult(100, i);
		     		finish();
					
					
				} else {
					// fallo
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
	 * Background Async Task to Delete
	 * */
	/*class Delete extends AsyncTask<String, String, String> {

		
		 //Before starting background thread Show Progress Dialog
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Borrando Empleado...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
		// Deleting Empleado
		 
		protected String doInBackground(String... args) {

			// Check for success tag
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cedula", cedula));

				// getting details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						url_borrar_empleado, "POST", params);

				// check your log for json response
				Log.d("Borrando", json.toString());
				
				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					// successfully deleted
					// notify previous activity by sending code 100
					Intent i = getIntent();
					// send result code 100 to notify about deletion
					setResult(100, i);
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		
		 //After completing background task Dismiss the progress dialog
		 
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once deleted
			pDialog.dismiss();

		}

	}*/
}
