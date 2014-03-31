package com.example.foodcartrevised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SelectRes extends Activity {
	
	private String hotel;
	AutoCompleteTextView resselect;
//	static Parcelable ress;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_restaurentss);
		
		
		final AutoCompleteTextView resselect=(AutoCompleteTextView)findViewById(R.id.restaurentabc);
		
		String[] reslist={"Tycoon","GreenPark","Novotel"};
		ArrayAdapter<String> resadapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,reslist);
		
		
		
		resselect.setAdapter(resadapter);
		
		
		Button resbu=(Button)findViewById(R.id.res_proceed);
		resbu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 hotel= resselect.getText().toString();
					
				 Logic.restaurents[Logic.rescount]=hotel;
				 Logic.selectedres=hotel;
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