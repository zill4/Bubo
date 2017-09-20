package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.nio.*;
import java.io.*;
public class Test 
{
	
	
	
	public static void main(String args[]) throws Exception
	{
		//Variables
		int count = 0;
		int favorites = 0;
		int rei_f = 0;
		int max_f = 0;
		double avg_f;
		//
		int retweets = 0;
		int rei_r = 0;
		int max_r = 0;
		double avg_r = 0;
		long wait = 0;
		long timeDiff = 0;
		long timeAvg = 0;
		
		String csvFile = "/Users/justinc.admin/eclipse-workspace/Bubo/sample2.csv";
		FileWriter write = new FileWriter(csvFile);
		// The factory instance is re-useable and thread safe.
	    	Twitter twitter = TwitterFactory.getSingleton();
	    	write.append("USER,FAVORITES,RETWEETS,TEXT\n");
	    	for(int i = 0; i < 2; i++) {
	    		// Thread.sleep(10000);
	    		Paging page = new Paging(i+1,200);
	    		List<Status> statuses = twitter.getUserTimeline("SlackHQ",page);
	    		//if(statuses.isEmpty())Thread.sleep(900000);
	    		System.out.println("Showing Slack timeline.");
	    		for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" + status.getFavoriteCount() + " " 
	        		+ status.getRetweetCount() + "\n" + status.getText());
	        write.append(status.getUser().getName()+",");
	        write.append(status.getFavoriteCount()+",");
	        write.append(status.getRetweetCount()+",");
	        write.append(status.getText()+"\n");
	        count++;
	        //Retweets and favorites counters
	        favorites += status.getFavoriteCount();
	        retweets += status.getRetweetCount();
	        //calculating difference in time.
	        if(wait != 0) {
	        	timeDiff += (wait- status.getCreatedAt().getTime());
	        }
	         wait = status.getCreatedAt().getTime();
	        
	        
	        
	        switch(status.getFavoriteCount())
	        {
	        case 0: rei_f++;
	        break;
	        default: if(max_f < status.getFavoriteCount()) max_f = status.getFavoriteCount();
	        break;
	        }
	        switch(status.getRetweetCount())
	        {
	        case 0: rei_r++;
	        break;
	        default: if(max_r < status.getRetweetCount()) max_r = status.getRetweetCount();
	        break;
	        }
	    	}
	    		avg_f = favorites/count;
	    		avg_r = retweets/count;
	    		timeAvg = timeDiff/count;
	    		System.out.println("average favorite: " + avg_f );
	    		System.out.println("average retweets: " + avg_r );
	    		
	    		System.out.println("favorite number of zeros: " + rei_f);
	    		System.out.println("retweets number of zeros: " + rei_r);
	    		
	    		System.out.println("Total sum of favorites: " + favorites);
	    		System.out.println("Total sum of retweets: " + retweets);
	    		
	    		System.out.println("Total number of tweets" + count);
	    		System.out.println("Average time difference " + timeAvg);
	    		
	    		/*
	    		 * Considering the 
	    		 */
	    	}
	    
	}
	
}
