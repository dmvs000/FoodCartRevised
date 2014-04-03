package com.example.foodcartrevised;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.foodcartrevised.Login.AttemptLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SelectItem extends Activity {
	Unique u;
	public  String itemselected="";
	//public  String restaurantselected="";
	public  String qty="2";
	
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
    JSONParserFetchRes jsonParser1 = new JSONParserFetchRes();
    ConnectionDetector cd;
//	AutoCompleteTextView itemselect;
	private String itemone;
	Spinner itemitem;
	String quantity;
	Spinner qnty;
	//Spinner qtyq;
	//String qtyitem;
	ArrayAdapter<String> resadapter;
	
	ArrayAdapter<String> qtyadapter;
	//ArrayAdapter<String> qtyadapter;
	private static final String LOGIN_URL = "http://kitesdevelopers.com/foodcart/entry.php";
	Boolean isInternetPresent = false;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public String getwhat="";
	
	
//	String[] Tycoon={"Idly","Dosa","poori"};
//	String[] GreenPark={"Tandoori","Gulabjamun","Rasgulla"};
//	String[] Novotel={"Panipoori","Rasmalai"};
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_items);

		String[] Tycoon={"Idly","Dosa","poori"};
		String[] GreenPark={"Tandoori","Gulabjamun","Rasgulla"};
		String[] Novotel={"Panipoori","Rasmalai"};
		String[] Daspalla={"something","npthng"};
		String[] Qty={"one","two","three","four","five","six"};
		String test="Tycoon";
		String test1="GreenPark";
		String test2="Novotel";
		String test3="Daspalla";
		quantity=Qty[0];
		itemone=Novotel[0];
		itemitem=(Spinner)findViewById(R.id.itemse);
		resadapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Novotel);
		//qtyq=(Spinner)findViewById(R.id.qtyq);
		itemitem.setAdapter(resadapter);
		
	//	itemitem.setAdapter(resadapter);
		  itemitem.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	 
	            @Override
	            public void onItemSelected(AdapterView<?> adapter, View v,
	                    int position, long id) {
	                // On selecting a spinner item
	                itemone = adapter.getItemAtPosition(position).toString();
	 
	                // Showing selected spinner item
	              //  Toast.makeText(getApplicationContext(),
	               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	 
	            }
	        });   
		  
		  
		  qnty=(Spinner)findViewById(R.id.qntyy);
			qtyadapter=new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, Qty);
			//qtyq=(Spinner)findViewById(R.id.qtyq);
			qnty.setAdapter(qtyadapter);
			
		//	itemitem.setAdapter(resadapter);
			  qnty.setOnItemSelectedListener(new OnItemSelectedListener() {
		        	 
		            @Override
		            public void onItemSelected(AdapterView<?> adapter, View v,
		                    int position, long id) {
		                // On selecting a spinner item
		                quantity = adapter.getItemAtPosition(position).toString();
		 
		                // Showing selected spinner item
		              //  Toast.makeText(getApplicationContext(),
		               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
		            }
		 
		            @Override
		            public void onNothingSelected(AdapterView<?> arg0) {
		                // TODO Auto-generated method stub
		 
		            }
		        });   
		  
		  if(Logic.selectedres.equals(test)){
			  itemone=Tycoon[0];
			  resadapter=new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, Tycoon);
			  resadapter.notifyDataSetChanged();
			  itemitem.setAdapter(resadapter);
			  
			  
		  }
		  
		  if(Logic.selectedres.equals(test1)){
			  itemone=GreenPark[0];
			  resadapter=new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, GreenPark);
			  resadapter.notifyDataSetChanged();
			  itemitem.setAdapter(resadapter);
		  }
		  
		  
         if(Logic.selectedres.equals(test2)){
        	 itemone=Novotel[0];
        	  resadapter=new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, Novotel);
			  resadapter.notifyDataSetChanged();
			  itemitem.setAdapter(resadapter);
         }
         if(Logic.selectedres.equals(test3)){
        	 itemone=Daspalla[0];
       	  resadapter=new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, Daspalla);
			  resadapter.notifyDataSetChanged();
			  itemitem.setAdapter(resadapter);
         }
		
		//String test="Tycoon";
		//String test1="GreenPark";
		//String test2="Novotel";
		
		//String[] numm={"one","two","three","four","five"};
	//	 qtyadapter=new ArrayAdapter<String>(this,
      //          android.R.layout.simple_spinner_item, numm);
        //qtyq.setAdapter(qtyadapter);
		
		
		
	//	resselect.setAdapter(resadapter);
        
        
        /*qtyq.setOnItemSelectedListener(new OnItemSelectedListener() {
        	 
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                    int position, long id) {
                // On selecting a spinner item
                qtyitem = adapter.getItemAtPosition(position).toString();
 
                // Showing selected spinner item
              //  Toast.makeText(getApplicationContext(),
               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
 
            }
        });*/
		
	//	 Toast.makeText(SelectItem.this, Logic.selectedres, Toast.LENGTH_LONG).show();
		//Logic.itemcount=0;
		//final AutoCompleteTextView itemselect=(AutoCompleteTextView)findViewById(R.id.fooditems);
		//if(Logic.selectedres.equals(test)){
			//String[] Tycoon={"Idly","Dosa","poori"};
			// resadapter=new ArrayAdapter<String>(this,
	          //      android.R.layout.simple_spinner_item, Tycoon);
	//	}
	   //     itemitem.setAdapter(resadapter);
			
			
			
		//	resselect.setAdapter(resadapter);
	        
	        
	  /*      itemitem.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	 
	            @Override
	            public void onItemSelected(AdapterView<?> adapter, View v,
	                    int position, long id) {
	                // On selecting a spinner item
	                itemone = adapter.getItemAtPosition(position).toString();
	 
	                // Showing selected spinner item
	              //  Toast.makeText(getApplicationContext(),
	               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	 
	            }
	        });   */
		//}
		/*else if(Logic.selectedres.equals(test1)){
			//String[] GreenPark={"Tandoori","Gulabjamun","Rasgulla"};
			 resadapter=new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, GreenPark);
	  //      itemitem.setAdapter(resadapter);
		}	
			
			
		//	resselect.setAdapter(resadapter);
	        
	        
	/*        itemitem.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	 
	            @Override
	            public void onItemSelected(AdapterView<?> adapter, View v,
	                    int position, long id) {
	                // On selecting a spinner item
	                itemone = adapter.getItemAtPosition(position).toString();
	 
	                // Showing selected spinner item
	              //  Toast.makeText(getApplicationContext(),
	               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	 
	            }
	        });   */
			
		//}
	//	else if(Logic.selectedres.equals(test2)){
		//	String[] Novotel={"Panipoori","Rasmalai"};
		//	 resadapter=new ArrayAdapter<String>(this,
	      //          android.R.layout.simple_spinner_item, Novotel);
	   //     itemitem.setAdapter(resadapter);
	//	}	
			
			
		//	resselect.setAdapter(resadapter);
	        
	        
	/*        itemitem.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	 
	            @Override
	            public void onItemSelected(AdapterView<?> adapter, View v,
	                    int position, long id) {
	                // On selecting a spinner item
	                itemone = adapter.getItemAtPosition(position).toString();
	 
	                // Showing selected spinner item
	              //  Toast.makeText(getApplicationContext(),
	               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	 
	            }
	        });   */
			
	//	}
		
		
			
		/*String[] Novotel={"Panipoori","Rasmalai"};
			ArrayAdapter<String> resadapterb=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Novotel);
			itemselect.setAdapter(resadapterb);  */
			
		
		
		
/*		itemitem.setAdapter(resadapter);
		  itemitem.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	 
	            @Override
	            public void onItemSelected(AdapterView<?> adapter, View v,
	                    int position, long id) {
	                // On selecting a spinner item
	                itemone = adapter.getItemAtPosition(position).toString();
	 
	                // Showing selected spinner item
	              //  Toast.makeText(getApplicationContext(),
	               //         "Selected Country : " + item, Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	 
	            }
	        });     */
		
		
		Button anoitem=(Button)findViewById(R.id.anotheritem);
		anoitem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	item=itemselect.getText().toString();
				int vamsi=Logic.itemcount;
					Logic.items[Logic.rescount][vamsi]=itemone;
			//		Logic.qty[Logic.rescount][vamsi]=qtyitem;
					Logic.itemcount++;
	//				itemselect.setText("");
					cd = new ConnectionDetector(getApplicationContext());
					isInternetPresent = cd.isConnectingToInternet();
					if(isInternetPresent){	
					itemselected=itemone;
					Logic.myitemc[Logic.myrestaurantcount]=Logic.myresitemcount;
					Logic.myitem[Logic.myrestaurantcount][Logic.myresitemcount++]=itemone;
					Log.d("Button Selected","Another Item");
					//Log.d("value of resc and resci", Logic.resc+" "+Logic.resci);
					new AttemptLogin().execute();
					}
					else
					{
						Toast.makeText(SelectItem.this, "Please Check You Internet Connectivity!", Toast.LENGTH_LONG).show();
					}
				
				
				
			}
		});
		
		
		Button anorest=(Button)findViewById(R.id.anotherrest);
		anorest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	//			item=itemselect.getText().toString();
				int krish=Logic.itemcount;
				//Logic.qty[Logic.rescount][krish]=qtyitem;
				Logic.items[Logic.rescount][krish]=itemone;
				Logic.itemcount++;
				Logic.rescount++;
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
				itemselected=itemone;
				
				
				if(isInternetPresent){
					getwhat="anotherres";
					itemselected=itemone;
					new AttemptLogin().execute();
					Logic.myitemc[Logic.myrestaurantcount]=Logic.myresitemcount;
					Logic.myitem[Logic.myrestaurantcount][Logic.myresitemcount++]=itemone;
					Logic.myrestaurantcount++;
					//Logic.resci=0;
					Log.d("Button Selected","Another restaurant");
					//Log.d("value of resc and resci", Logic.resc+" "+Logic.resci);
				}
					else
					{
						Toast.makeText(SelectItem.this, "Please Check You Internet Connectivity!", Toast.LENGTH_LONG).show();
					}
				
				//Intent arselect=new Intent(SelectItem.this,SelectRes.class);
				//startActivity(arselect);
				//finish();
//					Bundle b=new Bundle();
					//	b.putParcelable("resname",ress);
					//	iselect.putExtras(b);
				//	else
					//{
						//Toast.makeText(SelectItem.this, "Please Check You Internet Connectivity!", Toast.LENGTH_LONG).show();
					//}
				
			}
		});
		
		
		Button gotocart=(Button)findViewById(R.id.gotocart);
		gotocart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		//		item=itemselect.getText().toString();
				int red=Logic.itemcount;
				//Logic.qty[Logic.rescount][red]=qtyitem;
				Logic.items[Logic.rescount][red]=itemone;
			//	Logic.itemcount++;
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
				itemselected=itemone;
				
				if(isInternetPresent){
					getwhat="gotocart";
					itemselected=itemone;
					Log.d("Button Selected","Go To cart");
					Logic.myitemc[Logic.myrestaurantcount]=Logic.myresitemcount;
					Logic.myitem[Logic.myrestaurantcount][Logic.myresitemcount++]=itemone;
					
					//Log.d("value of resc and resci", Logic.resc+" "+Logic.resci);
					new AttemptLogin().execute();
					}
					else
					{
						Toast.makeText(SelectItem.this, "Please Check You Internet Connectivity!", Toast.LENGTH_LONG).show();
					}
				//new AttemptLogin().execute();
				//Intent gotocart=new Intent(SelectItem.this,FinalCart.class);
				//startActivity(gotocart);
				//finish();
				}
				
			
		});
		
	}	
		
		
		
		class AttemptLogin extends AsyncTask<String, String, String> {

			 /**
	         * Before starting background thread Show Progress Dialog
	         * */
			boolean failure = false;
			
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(SelectItem.this);
	            pDialog.setMessage("Saving to Cart");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
			
			@Override
			protected String doInBackground(String... args) {
				// TODO Auto-generated method stub
				 // Check for success tag
	            int success;
	           // String username = user.getText().toString();
	           // String password = pass.getText().toString();
	            try{
	                // Building Parameters
	                List<NameValuePair> params = new ArrayList<NameValuePair>();
	                params.add(new BasicNameValuePair("item", itemselected));
	                params.add(new BasicNameValuePair("resname", Logic.selectedres));
	                params.add(new BasicNameValuePair("quantity", quantity));
	                params.add(new BasicNameValuePair("username", Logic.username));
	                params.add(new BasicNameValuePair("id", Logic.unique));
	 
	                Log.d("Sending values", "starting");
	                // getting product details by making HTTP request
	                JSONObject json = jsonParser.makeHttpRequest(
	                       LOGIN_URL, "POST", params);
	 
	                // check your log for json response
	                Log.d("Sending Selections", json.toString());
	                
	 
	                // json success tag
	                success = json.getInt(TAG_SUCCESS);
	                if(getwhat.equals("anotherres"))
	                {
					Intent arselect=new Intent(SelectItem.this,SelectRes.class);
					startActivity(arselect);
					finish();
	                }
	                if(getwhat.equals("gotocart"))
	                {
					Intent gotocart=new Intent(SelectItem.this,FinalCart.class);
					startActivity(gotocart);
					finish();
	                }
					if (success == 1) {
	                	Log.d("Login Successful!", json.toString());
	                	//Logic.username=username;
	                	//Intent i = new Intent(SelectItem.this, SelectRes.class);
	                	//finish();
	    				//startActivity(i);
	                	return json.getString(TAG_MESSAGE);
	                }else{
	                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
	                	
	                	return json.getString(TAG_MESSAGE);
	                	
	                	
	                
	            }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
	            return null;
				
			}
			/**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once product deleted
	            pDialog.dismiss();
	            if (file_url != null){
	            	Toast.makeText(SelectItem.this, "Done", Toast.LENGTH_LONG).show();
	            }
	 
	        }		
	        public void onFinish()
	        {
	        	pDialog.dismiss();
	        }
		
	

}
	

}
