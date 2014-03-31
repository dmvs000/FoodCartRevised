package com.example.foodcartrevised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SelectItem extends Activity {
	
	AutoCompleteTextView itemselect;
	private String item;
	
	
	
//	String[] Tycoon={"Idly","Dosa","poori"};
//	String[] GreenPark={"Tandoori","Gulabjamun","Rasgulla"};
//	String[] Novotel={"Panipoori","Rasmalai"};
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_items);
		
		
		String test="Tycoon";
		String test1="GreenPark";
		String test2="Novotel";
		
		 Toast.makeText(SelectItem.this, Logic.selectedres, Toast.LENGTH_LONG).show();
		Logic.itemcount=0;
		final AutoCompleteTextView itemselect=(AutoCompleteTextView)findViewById(R.id.fooditems);
		if(Logic.selectedres.equals(test)){
			String[] Tycoon={"Idly","Dosa","poori"};
ArrayAdapter<String> resadapterc=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Tycoon);
itemselect.setAdapter(resadapterc);
		}
		else if(Logic.selectedres.equals(test1)){
			String[] GreenPark={"Tandoori","Gulabjamun","Rasgulla"};
			ArrayAdapter<String> resadaptear=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,GreenPark);
			itemselect.setAdapter(resadaptear);
			
		}
		else if(Logic.selectedres.equals(test2)){
			String[] Novotel={"Panipoori","Rasmalai"};
			ArrayAdapter<String> resadapterb=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Novotel);
			itemselect.setAdapter(resadapterb);
			
		}
		
		else{
			
			String[] Novotel={"Panipoori","Rasmalai"};
			ArrayAdapter<String> resadapterb=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Novotel);
			itemselect.setAdapter(resadapterb);
			
		}
		
		
		Button anoitem=(Button)findViewById(R.id.anotheritem);
		anoitem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				item=itemselect.getText().toString();
				if(item!=""){
					Logic.items[Logic.rescount][Logic.itemcount++]=item;
					itemselect.setText("");
				}
				
				
				
			}
		});
		
		
		Button anorest=(Button)findViewById(R.id.anotherrest);
		anorest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Logic.items[Logic.rescount][Logic.itemcount++]=item;
				Logic.rescount++;
				
				
				Intent arselect=new Intent(SelectItem.this,SelectRes.class);
				//	Bundle b=new Bundle();
				//	b.putParcelable("resname",ress);
				//	iselect.putExtras(b);
					startActivity(arselect);
					finish();
				
				
			}
		});
		
		
		Button gotocart=(Button)findViewById(R.id.gotocart);
		gotocart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Logic.items[Logic.rescount][Logic.itemcount++]=item;
				
				Intent gotocart=new Intent(SelectItem.this,FinalCart.class);
				startActivity(gotocart);
				finish();
				
			}
		});
		
		
		
		
		
		
		
	

}
}