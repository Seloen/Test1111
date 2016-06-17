package com.chris;

import java.util.HashMap;

public abstract class Visitor{
	int id;
	String type;
	
	HashMap<String, Boolean> like = new HashMap<String, Boolean>();//use hashmap to store visitor's like or dislike to the 4 places

	public boolean likeOrNot(String location){

		System.out.println("Visitor "+id+" is going to "+location+".");
		if(like.get(location)){
			System.out.println("Visitor " +id+" did like "+ location+".");
			return true;}
		else {
			System.out.println("Visitor " +id+" did not like " +location+".");
		return false;}
		
	}
	
	public void displayleave(){
		System.out.println("Visitor " +id+ " has left the city.");
	}
	
	public int id(){
		return this.id;
	}
	public String type(){
		return this.type;
	}
}
