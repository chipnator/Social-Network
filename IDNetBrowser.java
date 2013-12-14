//chris dale - data structures final project - this is IDNetBrowser.java 
//this is the Idea Network Browser aka user interface

//requires SocialNetwork class
//required nodes: Student node class, Idea node class

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IDNetBrowser {
	public static void main(String[] args) {
		//ON START-UP:
		SocialNetwork theNet = new SocialNetwork(); 
		theNet.run();	
	}
}
