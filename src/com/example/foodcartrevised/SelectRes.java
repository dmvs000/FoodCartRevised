package com.example.foodcartrevised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectRes extends Activity {
	public String selectres;
	private String hotel;
//	AutoCompleteTextView resselect;
//	static Parcelable ress;
	Spinner select;
	String selectedres;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_restaurentss);
		
	
		
		select=(Spinner)findViewById(R.id.resss);
		
		String[] reslist={"Tycoon","GreenPark","Novotel"};
		ArrayAdapter<String> resadapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, reslist);
        select.setAdapter(resadapter);
		
		
		
	//	resselect.setAdapter(resadapter);
        
        
        select.setOnItemSelectedListener(new OnItemSelectedListener() {
        	 
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                    int position, long id) {
                // On selecting a spinner item
                selectres = adapter.getItemAtPosition(position).toString();
                Logic.ressel=selectres;
                // Showing selected spinner item
               Toast.makeText(getApplicationContext(),
                        "Selected Res : " + selectres, Toast.LENGTH_LONG).show();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
 
            }
        });
		
		
		Button resbu=(Button)findViewById(R.id.res_proceed);
		resbu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		//		 hotel= resselect.getText().toString();
					
				 Logic.restaurents[Logic.rescount]=selectres;
				 Logic.selectedres=selectres;
				// Toast.makeText(SelectRes.this, Logic.selectedres, Toast.LENGTH_LONG).show();
				 Intent iselect=new Intent(SelectRes.this,SelectItem.class);
				 startActivity(iselect);
						finish();
				 
			//	 if(hotel!=""){
			//		 Logic.restaurents[Logic.rescount]=hotel;
				// ress=hotel;
			//	 Logic.selectedres=hotel;
			//	Intent iselect=new Intent(SelectRes.this,SelectItem.class);
			//	Bundle b=new Bundle();
			//	b.putParcelable("resname",ress);
			//	iselect.putExtras(b);
			//	startActivity(iselect);
			//	finish();
			//	 }
				
			}
		});
		
		
				
		

}
}