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

package com.ninetwozero.battlelog.datatypes;

import java.util.ArrayList;

import com.ninetwozero.battlelog.misc.PublicUtils;

public class ProfileInformation {

	//Attributes
	private int age;
	private long profileId, dateOfBirth, lastlogin, statusMessageChanged;
	private String name, username, presentation, location, statusMessage;
	private boolean allowFriendRequests, online;
	private ArrayList<FeedItem> feedItems;
	
	//Construct(s)
	public ProfileInformation(
		
		int a, long pid, long dob, long l, long sc,
		String n, String u, String p, String loc, String s,
		boolean af, boolean o, ArrayList<FeedItem> f
		
	) {
		
		this.age = a;
		this.profileId = pid;
		this.dateOfBirth = dob;
		this.lastlogin = l;
		this.statusMessageChanged = sc;
		this.name = n;
		this.username = u;
		this.presentation = p;
		this.location = loc; 
		this.statusMessage = s;
		this.allowFriendRequests = af;
		this.online = o;
		this.feedItems = f;
		
	}
	
	//Getters
	public int getAge() { return this.age; }
	public long getProfileId() { return this.profileId; }
	public long getDOB() { return this.dateOfBirth; }
	public String getLastLogin() { return PublicUtils.getRelativeDate( this.lastlogin, "Last logged in"); }
	public long getStatusMessageChanged() { return this.statusMessageChanged; }
	public String getName() { return this.name; }
	public String getUsername() { return this.username; }
	public String getPresentation() { return this.presentation; }
	public String getLocation() { return this.location; }
	public String getStatusMessage() { return this.statusMessage; }
	public boolean getAllowFriendRequests() { return this.allowFriendRequests; }
	public boolean isOnline() { return online; }
	public ArrayList<FeedItem> getFeedItems() { return this.feedItems; }

}