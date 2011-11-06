/*
	This file is part of BF3 Battlelog

    BF3 Battlelog is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BF3 Battlelog is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
*/   
package com.ninetwozero.battlelog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninetwozero.battlelog.datatypes.Config;
import com.ninetwozero.battlelog.datatypes.ProfileData;
import com.ninetwozero.battlelog.datatypes.WebsiteHandlerException;
import com.ninetwozero.battlelog.misc.WebsiteHandler;

public class UnlocksView extends Activity {

	//SharedPreferences for shizzle
	SharedPreferences sharedPreferences;
	ProgressBar progressBar;
	GetDataSelfAsync getDataAsync;
	private static int instances = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    
    	//onCreate - save the instance state
    	super.onCreate(savedInstanceState);

    	//Instances += 1
    	instances = 1;
    	
    	//Set the content view
        setContentView(R.layout.unlocks_view);

        //Prepare to tango
        this.sharedPreferences = this.getSharedPreferences( Config.fileSharedPrefs, 0);
    	if( instances == 1 ) { this.reloadLayout(); }
	}        

    public void reloadLayout() {
    	
    	//ASYNC!!!
    	new GetDataSelfAsync(this).execute(
    		
    		new ProfileData(
				this.sharedPreferences.getString( "battlelog_persona", "" ),
				this.sharedPreferences.getLong( "battlelog_persona_id", 0 ),
				this.sharedPreferences.getLong( "battlelog_persona_id", 0 ),
				this.sharedPreferences.getLong( "battlelog_platform_id", 1)
			)
		
		);
    	
    	
    }
    
    public void doFinish() {}
    
    private class GetDataSelfAsync extends AsyncTask<ProfileData, Void, Boolean> {
    
    	//Attributes
    	Context context;
    	ProgressDialog progressDialog;
    	ArrayList<UnlockData> unlockData;
    	
    	public GetDataSelfAsync(Context c) {
    		
    		this.context = c;
    		this.progressDialog = null;
    		
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		
    		//Let's see
			this.progressDialog = new ProgressDialog(this.context);
			this.progressDialog.setTitle("Please wait");
			this.progressDialog.setMessage( "Downloading the data..." );
			this.progressDialog.show();
    		
    	}
    	

		@Override
		protected Boolean doInBackground( ProfileData... arg0 ) {
			
			try {
				
				this.unlockData = WebsiteHandler.getUnlocksForUser( arg0[0] );
				return true;
				
			} catch ( WebsiteHandlerException ex ) {
				
				Log.d("com.ninetwozero.battlelog", ex.getMessage() );
				return false;
				
			}

		}
		
		@Override
		protected void onPostExecute(Boolean result) {
		
			//Fail?
			if( !result ) { 
				
				if( this.progressDialog != null ) this.progressDialog.dismiss();
				Toast.makeText( this.context, "No data found.", Toast.LENGTH_SHORT).show(); 
				((Activity) this.context).finish();
				return; 
			
			}

			//Do actual stuff, like sending to an adapter		
		
	        if( this.progressDialog != null ) this.progressDialog.dismiss();
			return;
		}
    	
    }
    
    @Override
	public boolean onCreateOptionsMenu( Menu menu ) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate( R.menu.option_profileview, menu );
		return super.onCreateOptionsMenu( menu );
	
    }
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {

		//Let's act!
		if( item.getItemId() == R.id.option_reload ) {
	
			this.reloadLayout();
			
		} else if( item.getItemId() == R.id.option_back ) {
			
			((Activity) this).finish();
			
		}
		
		// Return true yo
		return true;

	}  
    
    @Override
    public void onConfigurationChanged(Configuration newConfig){        
        super.onConfigurationChanged(newConfig);
    }  
    
}