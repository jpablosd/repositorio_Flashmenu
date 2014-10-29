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
import android.widget.TextView;
import android.widget.Toast;

public class modificarAdmRestaurant extends Activity {

	TextView lblcedula;
	EditText rutAdm;
	EditText nom;
	EditText apeP;
	EditText apeM;
	EditText pass;
	EditText email;
	EditText nombre,clave;
	Button btnSave;
	Button btnDelete;

	String cedula;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// single Empleado url - Reemplaza la IP de tu equipo o la direccion de tu servidor 
	// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
    private static final String url_detalles_empleado = "http://192.168.50.10/PHP/FlashmenuPHP/datosAdmRestaurant.php";
	
	
	// url to update Empleado - Reemplaza la IP de tu equipo o la direccion de tu servidor 
	private static final String url_actualizar_empleado = "http://192.168.50.10/PHP/FlashmenuPHP/actualizarAdmRestaurant.php";
	
	// url to delete Empleado - Reemplaza la IP de tu equipo o la direccion de tu servidor 
	private static final String url_borrar_empleado = "http://192.168.50.10/PHP/FlashmenuPHP/borrarAdmRestaurant.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_EMPLEADOS = "empleado";
	//private static final String TAG_CEDULA = "cedula";
	//private static final String TAG_NOMBRE = "nombre";
		
	private static final String TAG_CEDULA = "idAdministrador_restaurant";
	private static final String TAG_RUT = "Adm_rut";
	private static final String TAG_NOMBRE = "Adm_nombre";
	private static final String TAG_APELLIDOPATERNO = "Adm_apellidoPaterno";
	private static final String TAG_APELLIDOMATERNO = "Adm_apellidoMaterno";
	private static final String TAG_CONTRASENA = "Adm_direccion";
	private static final String TAG_EMAIL = "Adm_email";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modificaradmrestaurant);

		// save button
		btnSave = (Button) findViewById(R.id.btnSave);
		btnDelete = (Button) findViewById(R.id.btnDelete);

		// getting Empleado details from intent
		Intent i = getIntent();
		
		// getting Empleado id (pid) from intent
		cedula = i.getStringExtra(TAG_CEDULA);

		// Getting complete Empleado details in background thread
		//new GetEmpleadoDetails().execute();

		// save button click event
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// starting background task to update Empleado
				new SaveEmpleadoDetails().execute();
			}
		});

		// Delete button click event
		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// deleting Empleado in background thread
				new DeleteEmpleado().execute();
			}
		});

	}

	/**
	 * Background Async Task to Get complete Empleado details
	 * */
	/*class GetEmpleadoDetails extends AsyncTask<String, String, String> {

	
	// Before starting background thread Show Progress Dialog
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Loading Empleado details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
	 //Getting Empleado details in background thread
		
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("idAdministrador_restaurant", cedula));

						// getting Empleado details by making HTTP request
						// Note that Empleado details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								url_detalles_empleado, "GET", params);

						// check your log for json response
						Log.d("Single Empleado Details", json.toString());
						
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received Empleado details
							JSONArray EmpleadoObj = json
									.getJSONArray(TAG_EMPLEADOS); // JSON Array
							
							// get first Empleado object from JSON Array
							JSONObject Empleado = EmpleadoObj.getJSONObject(0);

							// Empleado with this pid found
							// Edit Text
							lblcedula = (TextView) findViewById(R.id.lblcedula);
							rutAdm = (EditText) findViewById(R.id.rutModif);
							nom = (EditText) findViewById(R.id.NombreModif);
							apeP = (EditText) findViewById(R.id.ApellidopaternoModif);
							apeM = (EditText) findViewById(R.id.ApellidomaternoModif);
							pass = (EditText) findViewById(R.id.contrasenaModif);
							email = (EditText) findViewById(R.id.emailModif);

							// display Empleado data in EditText
							lblcedula.setText(Empleado.getString(TAG_CEDULA));
							rutAdm.setText(Empleado.getString(TAG_RUT));
							nom.setText(Empleado.getString(TAG_NOMBRE));
							apeP.setText(Empleado.getString(TAG_APELLIDOPATERNO));
							apeM.setText(Empleado.getString(TAG_APELLIDOMATERNO));
							pass.setText(Empleado.getString(TAG_CONTRASENA));
							email.setText(Empleado.getString(TAG_EMAIL));

						}else{
							// Empleado with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}


		
		 // After completing background task Dismiss the progress dialog
		 
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}*/

	/**
	 * Background Async Task to  Save Empleado Details
	 * */
	class SaveEmpleadoDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Actualizando Empleado ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Saving Empleado
		 * */
		protected String doInBackground(String... args) {

			// getting updated data from EditTexts
			
			
			String r = rutAdm.getText().toString();
			if (r.equalsIgnoreCase(TAG_RUT)==true){
			String n = nom.getText().toString();
			String ap = apeP.getText().toString();
			String am = apeM.getText().toString();
			String p = pass.getText().toString();
			String e = email.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(TAG_CEDULA, cedula));
			params.add(new BasicNameValuePair(TAG_RUT, r));
			params.add(new BasicNameValuePair(TAG_NOMBRE, n));
			params.add(new BasicNameValuePair(TAG_APELLIDOPATERNO, ap));
			params.add(new BasicNameValuePair(TAG_APELLIDOMATERNO, am));
			params.add(new BasicNameValuePair(TAG_CONTRASENA, p));
			params.add(new BasicNameValuePair(TAG_EMAIL, e));

			// sending modified data through http request
			// Notice that update Empleado url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_actualizar_empleado,
					"POST", params);

			// check json success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					
					
					
					
		
					// successfully updated
					Intent i = getIntent();
					// send result code 100 to notify about Empleado update
					setResult(100, i);
					finish();
					
					
				} else {
					// failed to update Empleado
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

			
							}
		else{
		Toast toast1 = Toast.makeText(getApplicationContext(),"Error: rut no existe.", Toast.LENGTH_SHORT);
	 	    toast1.show();
		}
		
			return null;
		}


		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once Empleado uupdated
			pDialog.dismiss();
		}
		
	}

	/*****************************************************************
	 * Background Async Task to Delete Empleado
	 * */
	class DeleteEmpleado extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(modificarAdmRestaurant.this);
			pDialog.setMessage("Borrando Empleado...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Deleting Empleado
		 * */
		protected String doInBackground(String... args) {

			// Check for success tag
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cedula", cedula));

				// getting Empleado details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						url_borrar_empleado, "POST", params);

				// check your log for json response
				Log.d("Borrando Empleado", json.toString());
				
				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					// Empleado successfully deleted
					// notify previous activity by sending code 100
					Intent i = getIntent();
					// send result code 100 to notify about Empleado deletion
					setResult(100, i);
					finish();
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
			// dismiss the dialog once Empleado deleted
			pDialog.dismiss();

		}

	}
}
