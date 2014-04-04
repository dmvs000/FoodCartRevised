package com.example.foodcartrevised;
import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class CustomListViewAndroidExample extends Activity {
             
                ListView list;
                CustomAdapter adapter;
                public  CustomListViewAndroidExample CustomListView = null;
                public  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
                 
                @Override
                protected void onCreate(Bundle savedInstanceState) {
 
 
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_custom_list_view_android_example);
                     
                    CustomListView = this;
                     
                    /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
                    setListData();
                     
                    Resources res =getResources();
                    list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
                     
                    /**************** Create Custom Adapter *********/
                    adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
                    list.setAdapter( adapter );
                     
                }
             
                /****** Function to set data in ArrayList *************/
                public void setListData()
                {
                     
                    for (int i = 0; i <=Logic.myrestaurantcount; i++) {
                         
                        final ListModel sched = new ListModel();
                             
                          /******* Firstly take data in model object ******/
                           sched.setCompanyName("Restaurant "+Logic.myitem[i][0]);
                           CustomListViewValuesArr.add( sched );
                           for(int j=1;j<Logic.myitem[i].length;j++)
                           {
                        	   final ListModel sched1 = new ListModel();
                        	   sched1.setUrl("item "+Logic.myitem[i][j]);
                            
                        /******** Take Model Object in ArrayList **********/
                        CustomListViewValuesArr.add( sched1 );
                           }
                           
                    }
                     
                }
                
 
               /*****************  This function used by adapter ****************/
                public void onItemClick(int mPosition)
                {
                    ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
   
 
                   // SHOW ALERT                  
 
                    Toast.makeText(CustomListView,
                            ""+tempValues.getCompanyName()
                              +"Image:"+tempValues.getImage()
                              +" Url:"+tempValues.getUrl(),Toast.LENGTH_LONG).show();
                }
       }