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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import cl.flashmenu.aplicacion.perfilAdmRestaurant.Loadrest;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class verMapa extends Activity{

	private GoogleMap googleMap;
	
	   
	   private static String url_all_inforest = "http://10.40.3.149/PHP/FlashmenuPHP/perfilAdm.php";
	   //private static String url_all_inforest = "http://190.153.212.77/daniel_fernandez/perfilAdm.php"
		

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		setContentView(R.layout.vermapa);
		
		
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		
		
		
				
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-33.013779, -71.543099))
		.title("MAIA").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-40.056788, -61.512542))
		.title("otro 1").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-50.043567, -51.678678))
		.title("otro 2").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-28.056732, -31.123789))
		.title("otro 3").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-46.086754, -21.654478))
		.title("otro 4").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-33.045634, -91.98654))
		.title("otro 5").snippet("Vuelva a presionar el marker para mas informacion!"));
		
		//googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		
		googleMap.setOnMarkerClickListener(new OnMarkerClickListener(){

			@Override
			public boolean onMarkerClick(Marker arg0) {
				
				
				Toast.makeText(getApplicationContext(), arg0.getTitle(), Toast.LENGTH_LONG).show();
				//////////
				googleMap.setOnMarkerClickListener(new OnMarkerClickListener(){

					@Override
					public boolean onMarkerClick(Marker arg0) {
						
						Intent i = new Intent(getApplicationContext(), infoRestaurantes.class);
						startActivity(i);

						return false;
					}
				});
				//////////
				
				return false;
			}
		});
		
	}//oncreatr
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
