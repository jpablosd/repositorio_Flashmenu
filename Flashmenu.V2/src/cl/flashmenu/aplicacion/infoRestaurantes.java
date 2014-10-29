package cl.flashmenu.aplicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class infoRestaurantes extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> empleadosList;

	// url to get all empleados list Reemplaza la IP de tu equipo o la direccion de tu servidor 
	// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
	private static String url_all_empleados = "http://192.168.50.10/PHP/FlashmenuPHP/restaurantes.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOMBRE = "Rest_nombre";
	private static final String TAG_TIPO = "Rest_tipo";
	private static final String TAG_DESCRIPCION = "Rest_descripcion";
	private static final String TAG_CARACTERISTICAS = "Rest_caracteristicas";
	private static final String TAG_restaurant = "restaurant";
	

	// empleados JSONArray
	JSONArray empleados = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inforestaurantes);

		// Hashmap for ListView
		empleadosList = new ArrayList<HashMap<String, String>>();

		// Loading empleados in Background Thread
		new LoadAllempleados().execute();
		
	}
	class LoadAllempleados extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(infoRestaurantes.this);
			pDialog.setMessage("Cargando informacio. Espere porfavor...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All empleados from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_empleados, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All empleados: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// empleados found
					// Getting Array of empleados
					empleados = json.getJSONArray(TAG_restaurant);

					// looping through All empleados
					for (int i = 0; i < empleados.length(); i++) {
						JSONObject c = empleados.getJSONObject(i);

						// Storing each json item in variable
						String nombre = c.getString(TAG_NOMBRE);
						String tipo = c.getString(TAG_TIPO);
						String descripcion = c.getString(TAG_DESCRIPCION);
						String caracteristicas = c.getString(TAG_CARACTERISTICAS);
						
						

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_NOMBRE, nombre);
						map.put(TAG_TIPO, tipo);
						map.put(TAG_DESCRIPCION, descripcion);
						map.put(TAG_CARACTERISTICAS, caracteristicas);

						// adding HashList to ArrayList
						empleadosList.add(map);
					}
				} else {
					// no empleados found
					// Launch Add New Empleado Activity
					Intent i = new Intent(getApplicationContext(), verMapa.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
	}

		
	}

	

	}
