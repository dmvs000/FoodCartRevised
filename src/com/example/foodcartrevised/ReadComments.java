package com.example.foodcartrevised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ReadComments extends Activity{
	
	private EditText user, pass;
	private Button mSubmit, mRegister;
	private Spinner spinner1,spinner2;
	 // Progress Dialog
    private ProgressDialog pDialog;
    public String[] res;
   
 
    // JSON parser class
    JSONParserFetchRes jsonParser = new JSONParserFetchRes();
    
    //php login script location:
    
    //localhost :  
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";
    
    //testing on Emulator:
    private static final String LOGIN_URL = "http://kitesdevelopers.com/foodcart/SQL1.php";
    
  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";
    
    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    List<String> reslist = new ArrayList<String>();
    ArrayList<HashMap<String, String>> contactList;
    AutoCompleteTextView textview=(AutoCompleteTextView)findViewById(R.id.ai);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_comments);
		new AttemptLogin().execute();
       // reslist.add("food cart special");
		
		
		//dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spinner1.setAdapter(dataAdapter);
		//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		//setup input fields
		//user = (EditText)findViewById(R.id.username);
		//pass = (EditText)findViewById(R.id.password);
		
		//setup buttons
		//mSubmit = (Button)findViewById(R.id.login);
		//mRegister = (Button)findViewById(R.id.register);
		
		//register listeners
		//mSubmit.setOnClickListener(this);
		//mRegister.setOnClickListener(this);
		
	}

/*	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
				new AttemptLogin().execute();
			break;
		case R.id.register:
				Intent i = new Intent(this, Register.class);
				startActivity(i);
			break;

		default:
			break;
		}
	}*/
	
	class AttemptLogin extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReadComments.this);
            pDialog.setMessage("Retrieving List...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            //String username = user.getText().toString();
            //String password = pass.getText().toString();
            try {
                // Building Parameters
                //List<NameValuePair> params = new ArrayList<NameValuePair>();
                //params.add(new BasicNameValuePair("username", username));
                //params.add(new BasicNameValuePair("password", password));
 
                Log.d("restaurants request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                       LOGIN_URL, "POST");
 
                // check your log for json response
                Log.d("Restaurant Retreival Attempt", json.toString());
 
                // json success tag
                
                	 // Create JSONObject from result JSON string
                	   JSONArray arr = new JSONObject(json.toString()).getJSONArray("posts");
                	   // get the 'posts' section from the JSON string
                	   for (int i = 0; i < arr.length(); i++) {
                	      JSONObject post = arr.getJSONObject(i).getJSONObject("post");
                	      Log.i("dude", post.getString("resname"));
                	      //Log.i("dude", post.getString("name"));
                	      //Log.i(TAG, "" + post.getInt("age"));
                	      //Log.i(TAG, post.getString("color"));
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
            	Toast.makeText(ReadComments.this, file_url, Toast.LENGTH_LONG).show();
            	
            }
 ArrayAdapter<String> adapter=new ArrayAdapter<String>(ReadComments.this,android.R.layout.simple_list_item_1, res);
 textview.setAdapter(adapter);
 
        }
		
	}
		 

}
