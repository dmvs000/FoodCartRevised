package com.example.foodcartrevised;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
 
@SuppressLint("NewApi") public class Order extends Activity {
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.order);
 
 // Permission StrictMode
 if (android.os.Build.VERSION.SDK_INT > 9) {
 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
 StrictMode.setThreadPolicy(policy);
 }
 
 // spinner1
 final Spinner spin = (Spinner)findViewById(R.id.spinner1);
 
 String url = "http://kitesdevelopers.com/foodcart/test.php";
 
try {
 
 JSONArray data = new JSONArray(getJSONUrl(url));
 
 final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
 HashMap<String, String> map;
 
 for(int i = 0; i < data.length(); i++){
 JSONObject c = data.getJSONObject(i);
 
 map = new HashMap<String, String>();
 map.put("customerID", c.getString("customerID"));
 map.put("name", c.getString("name"));
 map.put("phone", c.getString("phone"));
 MyArrList.add(map);
 
 }
 SimpleAdapter sAdap;
 sAdap = new SimpleAdapter(Order.this, MyArrList, R.layout.order_show,
 new String[] {"customerID", "name", "phone"}, new int[] {R.id.ColCustomerID, R.id.ColName, R.id.ColTel});
 spin.setAdapter(sAdap);
 
 final AlertDialog.Builder viewDetail = new AlertDialog.Builder(this);
 
 spin.setOnItemSelectedListener(new OnItemSelectedListener() {
 
public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
 int position, long id) {
 String sCustomerID = MyArrList.get(position).get("customerID")
 .toString();
 String sName = MyArrList.get(position).get("name")
 .toString();
 String sTel = MyArrList.get(position).get("phone")
 .toString();
 
 viewDetail.setIcon(android.R.drawable.btn_star_big_on);
 viewDetail.setTitle("Customer Detail");
 viewDetail.setMessage("customerID : " + sCustomerID + "\n"
 + "name : " + sName + "\n" + "Tel : " + sTel);
 viewDetail.setPositiveButton("OK",
 new DialogInterface.OnClickListener() {
 public void onClick(DialogInterface dialog,
 int which) {
 // TODO Auto-generated method stub
 dialog.dismiss();
 }
 });
 viewDetail.show();
 
 }
 
public void onNothingSelected(AdapterView<?> arg0) {
 // TODO Auto-generated method stub
 Toast.makeText(Order.this,
 "Your Selected : Nothing",
 Toast.LENGTH_SHORT).show();
 }
 });
 
 } catch (JSONException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
 
}
 
public String getJSONUrl(String url) {
 StringBuilder str = new StringBuilder();
 HttpClient client = new DefaultHttpClient();
 HttpGet httpGet = new HttpGet(url);
 try {
 HttpResponse response = client.execute(httpGet);
 StatusLine statusLine = response.getStatusLine();
 int statusCode = statusLine.getStatusCode();
 if (statusCode == 200) { // Download OK
 HttpEntity entity = response.getEntity();
 InputStream content = entity.getContent();
 BufferedReader reader = new BufferedReader(new InputStreamReader(content));
 String line;
 while ((line = reader.readLine()) != null) {
 str.append(line);
 }
 } else {
 Log.e("Log", "Failed to download result..");
 }
 } catch (ClientProtocolException e) {
 e.printStackTrace();
 } catch (IOException e) {
 e.printStackTrace();
 }
 
 return str.toString();
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 // getMenuInflater().inflate(R.menu.activity_main, menu);
 return true;
 }
 
}