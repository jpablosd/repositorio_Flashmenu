package cl.flashmenu.aplicacion;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class cantPersonasHorario  extends Activity {
	
	private Spinner spinner1;
	private List<String> lista;
	
	private Spinner spinner2;
	private List<String> lista2;
	
	Button btnHaciaMenu;
	String L;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.cantpersonashorario);
	   DatosPorDefecto();
	   DatosPorDefecto2();
	   
	   btnHaciaMenu = (Button) findViewById(R.id.haciaMenu);
	   btnHaciaMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), menu.class);
				startActivity(i);

				
		//	 Intent i2=new Intent(cantPersonasHorario.this, infoMenu.class);
		//		 i2.putExtra("datosLista",L);
		//		 startActivity(i2); 

				//finish();
			}
		});
	   
	   
	}


	private void DatosPorDefecto() {
	   spinner1 = (Spinner) findViewById(R.id.spinner);
	   lista = new ArrayList<String>();
	   spinner1 = (Spinner) this.findViewById(R.id.spinner);
	   lista.add("1 persona");
	   lista.add("2 persona");
	   lista.add("3 persona");
	   lista.add("4 persona");
	   lista.add("5 persona");
	   lista.add("6 persona");
	   ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
	   adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   spinner1.setAdapter(adaptador);
	   
	 //  	L=lista.get(RESULT_OK);   
	   if(lista.toArray().toString().equalsIgnoreCase("1 persona") == true){
		   L = "1 persona";		   
	   }
	   
	   if(lista.toArray().toString().equalsIgnoreCase("2 persona") == true){
		   L = "2 persona";		   
	   }
	   
	   if(lista.toArray().toString().equalsIgnoreCase("3 persona") == true){
		   L = "3 persona";		   
	   }
	   
	   if(lista.toArray().toString().equalsIgnoreCase("4 persona") == true){
		   L = "4 persona";		   
	   }
	   
	   if(lista.toArray().toString().equalsIgnoreCase("5 persona") == true){
		   L = "5 persona";		   
	   }
	   
	   if(lista.toArray().toString().equalsIgnoreCase("6 persona") == true){
		   L = "6 persona";		   
	   }
	   
	 /*  spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
		   @Override
		   public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		      Toast.makeText(arg0.getContext(), "Seleccionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
		   }
		   @Override
		   public void onNothingSelected(AdapterView<?> arg0) {
		   }
		});*/
	   
	}
	
	
	private void DatosPorDefecto2() {
		   spinner2 = (Spinner) findViewById(R.id.spinner2);
		   lista2 = new ArrayList<String>();
		   spinner2 = (Spinner) this.findViewById(R.id.spinner2);
		   lista2.add("12:00");
		   lista2.add("13:00");
		   lista2.add("14:00");
		   lista2.add("15:00");
		   lista2.add("16:00");
		   lista2.add("17:00");
		   lista2.add("18:00");
		   lista2.add("19:00");
		   
		   ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
		   adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   spinner2.setAdapter(adaptador);
		   
		 /*  spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			   @Override
			   public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			      Toast.makeText(arg0.getContext(), "Seleccionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
			   }
			   @Override
			   public void onNothingSelected(AdapterView<?> arg0) {
			   }
			});*/
		   
		}
	

	
		
	}

