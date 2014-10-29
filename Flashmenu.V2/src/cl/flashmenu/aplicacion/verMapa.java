package cl.flashmenu.aplicacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class verMapa extends Activity{

	private GoogleMap googleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vermapa);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		
		
		googleMap.addMarker(new MarkerOptions().position(new LatLng(-33.013779, -71.543099)).title("MAIA").snippet("Vuelva a presionar para mas informacion del restaurant!"));
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
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
