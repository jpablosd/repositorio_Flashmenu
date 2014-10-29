package cl.flashmenu.aplicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class infoRestaurantes extends Activity {
	
	TextView n, t, d, c;
	Button s;

	
	String nombre;
	String tipo;
	String descripcion;
	String caracteristicas;
	
	
	private ProgressDialog pDialog;

   JSONParser jParser = new JSONParser();
   

   private static String url_all_inforest = "http://10.40.3.149/PHP/FlashmenuPHP/restaurantes.php";
   //private static String url_all_inforest = "http://190.153.212.77/daniel_fernandez/restaurantes.php";

	
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOMBRE = "nombre";
	private static final String TAG_TIPO = "tipo";
	private static final String TAG_DESCRIPCION = "descripcion";
	private static final String TAG_CARACTERISTICAS = "caracteristicas";
	private static final String TAG_restaurant = "restaurant";
	

JSONArray rest = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inforestaurantes);
		
		n = (TextView) findViewById(R.id.nr);
		t = (TextView) findViewById(R.id.tr);
		d = (TextView) findViewById(R.id.dr);
		c = (TextView) findViewById(R.id.cr);
		
				
		
		new Loadrest().execute();
		
		s = (Button) findViewById(R.id.sigPaso);
		s.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), cantPersonasHorario.class);
				startActivity(i);

				//finish();
			}
		});
		
	}
	class Loadrest extends AsyncTask<String, String, String> {

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
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("buscar", "1"));
			
			JSONObject json = jParser.makeHttpRequest(url_all_inforest, "POST", params);
			
			Log.d("All empleados: ", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					rest = json.getJSONArray(TAG_restaurant);

					for (int i = 0; i < rest.length(); i++) {
						JSONObject c = rest.getJSONObject(i);

						nombre = c.getString(TAG_NOMBRE);
						tipo = c.getString(TAG_TIPO);
						descripcion = c.getString(TAG_DESCRIPCION);
						caracteristicas = c.getString(TAG_CARACTERISTICAS);
						
						

					
					}
				} else {

			Intent i = new Intent(getApplicationContext(), verMapa.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
	}
		
		
		
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		
		n.setText(nombre);
		t.setText(tipo);
		d.setText(descripcion);
		c.setText(caracteristicas);

		}

		
	}

	

	}
