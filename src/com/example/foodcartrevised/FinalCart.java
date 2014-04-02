package com.example.foodcartrevised;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FinalCart extends Activity implements OnClickListener{
	
	private EditText user, pass;
	private Button mSubmit, mRegister;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    JSONParserFetchRes jsonParser1 = new JSONParserFetchRes();
    ConnectionDetector cd;
    private String[] restaurants;
    //php login script location:
    
    //localhost :  
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";
    
    //testing on Emulator:
    private static final String LOGIN_URL = "http://kitesdevelopers.com/foodcart/subbu.php";
    private static final String LOGIN_URLSQL = "http://kitesdevelopers.com/foodcart/SQL1.php";
    Boolean isInternetPresent = false;
  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";
    
    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finalcart);
		
		//setup input fields
		//user = (EditText)findViewById(R.id.username);
		//pass = (EditText)findViewById(R.id.password);
		
		//setup buttons
		mSubmit = (Button)findViewById(R.id.cartconfirm);
		//mRegister = (Button)findViewById(R.id.register);
		
		//register listeners
		mSubmit.setOnClickListener(this);
		//mRegister.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent){
			new AttemptLogin().execute();
			}
		else
		{
			Toast.makeText(FinalCart.this, "Please Check You Internet Connectivity!", Toast.LENGTH_LONG).show();
		}	
	}
	
	class AttemptLogin extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FinalCart.this);
            pDialog.setMessage("Sending Cart for billing.. ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
			int success;
			try{
				
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", Logic.unique));
			Log.d("sending cart id", "Starting....");
			// getting product details by making HTTP request
			JSONObject json = jsonParser.makeHttpRequest(
			       LOGIN_URL, "POST", params);
			Log.d("sending attempt", json.toString());
			success = json.getInt(TAG_SUCCESS);
			finish();
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
			/*for(int i=0;i<3;i++){
			send.put("resname", Logic.restaurents[i]);
			send.put("itemname", "bla");
			send.put("qty", "3rd");
			}
			JSONArray jsonArray = new JSONArray();

			jsonArray.put(send);
			*/
			//-----------------------------------------//
			/*JSONObject json = new JSONObject();
			for(int i=0;i<3;i++)
			{
			json.put("resname","X");
			JSONArray items = new JSONArray();
			JSONObject vegData = new JSONObject();
			for(int j=0;j<3;j++)
			{
			vegData.put("itemname","red");
			vegData.put("qty","yellow");
			items.put(vegData);   
			}
			json.put("items",items);
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("res", json.toString()));
			JSONObject json1 = jsonParser.makeHttpRequest(
			           LOGIN_URL, "POST", params);
 */		}
			catch (JSONException e) {
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
            	Toast.makeText(FinalCart.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
		 

}
