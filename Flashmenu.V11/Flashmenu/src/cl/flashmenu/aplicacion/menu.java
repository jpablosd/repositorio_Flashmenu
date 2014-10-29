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
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends Activity {
	
	TextView n, d, p, n1, d1, p1, n2, d2, p2;
	Button s;
	RadioGroup rg;
	int checa;

	
	String nombre;
	String descripcion;
	String precio;
	
	
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	//ArrayList<HashMap<String, String>> empleadosList;

	//private static String url_all_infmenu = "http://190.153.212.77/daniel_fernandez/menu.php";
	//private static String url_all_infmenu = "http://10.40.3.149/PHP/FlashmenuPHP/menu.php";
	private static String url_all_infmenu = "http://201.239.236.147/PHP/FlashmenuPHP/menu.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOMBRE = "nombre";
	private static final String TAG_DESCRIPCION = "descripcion";
	private static final String TAG_PRECIO = "precio";
	private static final String TAG_MENU = "menu";
	
	
	private Spinner spinner1;
	private List<String> lista1;
	private Spinner spinner2;
	private List<String> lista2;

	JSONArray menu = null;
	
    Spinner lista;
	String[] datos = {"1", "2"};
	Button pago;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		
		rg = (RadioGroup) findViewById(R.id.rg);
		
		n = (TextView) findViewById(R.id.n);	
		d = (TextView) findViewById(R.id.d);
		p = (TextView) findViewById(R.id.p);
		
		n1 = (TextView) findViewById(R.id.n1);	
		d1 = (TextView) findViewById(R.id.d1);
		p1 = (TextView) findViewById(R.id.p1);
		
		n2 = (TextView) findViewById(R.id.n2);	
		d2 = (TextView) findViewById(R.id.d2);
		p2 = (TextView) findViewById(R.id.p2);
		
		lista = (Spinner)findViewById(R.id.sp);
		
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
		lista.setAdapter(adaptador);
		
		lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
				
				switch(i){
				
				case 0:
					
					n.setText("Nombre: Carbonada");
					d.setText("Descripcion: Preparado con papa, zapallo, zanahorias, porotitos, etc");
					p.setText("3990");
					
					n1.setText("Nombre: Empanadas");
					d1.setText("Descripcion: de pino al horno");
					p1.setText("8990");
					
					n2.setText("Nombre: porotos granados");
					d2.setText("Descripcion: plato tipico chileno");
					p2.setText("5990");
					
					checa = rg.getCheckedRadioButtonId();
					
					
					Toast to = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_LONG);
					to.show();
				//	Toast to1 = Toast.makeText(getApplicationContext(), "menu " + checa + " seleccionado" , Toast.LENGTH_LONG);
				//	to1.show();
					
					break;
					

				case 1:
					
					n.setText("Nombre: Carbonada");
					d.setText("Descripcion: Preparado con papa, zapallo, zanahorias, porotitos, etc");
					p.setText("3990");
					
					n1.setText("Nombre: Empanadas");
					d1.setText("Descripcion: de pino al horno");
					p1.setText("8990");
					
					n2.setText("Nombre: porotos granados");
					d2.setText("Descripcion: plato tipico chileno");
					p2.setText("5990");
					
					
					checa = rg.getCheckedRadioButtonId();
					
					Toast t = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_LONG);
					t.show();
					break;
				
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		 pago = (Button) findViewById(R.id.haciaPagar);
		   pago.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), Paypal.class);
				startActivity(i);

				//finish();
			}
		});
		
			
		
		

		/*
	new todosMenu().execute();
		
		s = (Button) findViewById(R.id.haciaPagar);
	    s.setVisibility(1);
		s.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), pagarMenu.class);
				startActivity(i);

				//finish();
			}
		});
		
		}	
	
	
	class todosMenu extends AsyncTask<String, String, String> {

	
		//  Before starting background thread Show Progress Dialog
		 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(menu.this);
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

		*/
		
	}

	/*
	public void onClick(View v){
		checa = rg.getCheckedRadioButtonId();
		switch(checa){
			case R.id.r1: 
				
				n.setText("Carne");
				d.setText("asdasdasdasdasdasdasdasdasdasdasdasdsasd");
				p.setText("3990");
								
				break;
			
			case R.id.r2: 
			
				n1.setText("pure");
				d1.setText("asdasdasdasdasdasdasdasdasdasdasdasdsasd");
				p1.setText("8990");
				break;
			
			case R.id.r3: 
				n2.setText("fideos");
				d2.setText("asdasdasdasdasdasdasdasdasdasdasdasdsasd");
				p2.setText("5990");
				
				break;
		}
		
	}*/
	}
