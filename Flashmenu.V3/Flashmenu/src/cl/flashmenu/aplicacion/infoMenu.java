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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class infoMenu extends Activity {
	
	TextView n, d, p;
	Button s;

	
	String nombre;
	String descripcion;
	String precio;
	
	
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	//ArrayList<HashMap<String, String>> empleadosList;

	//private static String url_all_infmenu = "http://190.153.212.77/daniel_fernandez/menu.php";
	private static String url_all_infmenu = "http://200.83.21.43/PHP/FlashmenuPHP/menu.php";


	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOMBRE = "nombre";
	private static final String TAG_DESCRIPCION = "descripcion";
	private static final String TAG_PRECIO = "precio";
	private static final String TAG_MENU = "menu";
	
	private Spinner spinner2;
	private List<String> lista2;

	JSONArray menu = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomenu);
		
		
		n = (TextView) findViewById(R.id.nm);	
		d = (TextView) findViewById(R.id.dm);
		p = (TextView) findViewById(R.id.pm);
		
				
		
			/*	Bundle extras = getIntent().getExtras();
				System.out.println(extras.getString("datosLista"));
				
		        if (extras.getString("datosLista").equalsIgnoreCase("1 persona") == true) {
		        	new LoadAllempleados().execute();     	   
		        }
		        else if(extras.getString("datosLista").equalsIgnoreCase("2 persona") == true) {
		        	new LoadAllempleados().execute(); 
		        	new LoadAllempleados().execute();
			        }
		        else if(extras.getString("datosLista").equalsIgnoreCase("3 persona") == true) {
			     	   String cantidad  = extras.getString("datosLista");//usuario		     	   
			        }
		        else if(extras.getString("datosLista").equalsIgnoreCase("4 persona") == true) {
			     	   String cantidad  = extras.getString("datosLista");//usuario		     	   
			        }
		        else if(extras.getString("datosLista").equalsIgnoreCase("5 persona") == true) {
			     	   String cantidad  = extras.getString("datosLista");//usuario		     	   
			        }
		        else if(extras.getString("datosLista").equalsIgnoreCase("6 persona") == true) {
			     	   String cantidad  = extras.getString("datosLista");//usuario		     	   
			        }*/

		
	new LoadAllempleados().execute();
		
		s = (Button) findViewById(R.id.haciaPagar);
		s.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), pagarMenu.class);
				startActivity(i);

				//finish();
			}
		});
		
		}
		private void DatosPorDefecto2() {
			   spinner2 = (Spinner) findViewById(R.id.spinner2);
			   lista2 = new ArrayList<String>();
			   spinner2 = (Spinner) this.findViewById(R.id.spinner2);
			   lista2.add("1");
			   lista2.add("2");
			  
			   
			   ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
			   adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			   spinner2.setAdapter(adaptador);
		}
		
	
	
	
	class LoadAllempleados extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(infoMenu.this);
			pDialog.setMessage("Cargando informacio. Espere porfavor...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}


		protected String doInBackground(String... args) {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("buscar", "1"));
			
			JSONObject json = jParser.makeHttpRequest(url_all_infmenu, "POST", params);
			
			Log.d("Todos los menu: ", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
				
					menu = json.getJSONArray(TAG_MENU);

					for (int i = 0; i < menu.length(); i++) {
						JSONObject c = menu.getJSONObject(i);

						nombre = c.getString(TAG_NOMBRE);
						descripcion = c.getString(TAG_DESCRIPCION);
						precio = c.getString(TAG_PRECIO);
						
						

					
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
		d.setText(descripcion);
		p.setText(precio);

		}

		
	}

	

	}
