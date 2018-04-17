package com.chat.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{

	String chatIn;
	Socket socket;
	Thread thread;
	DataInputStream dis;
	DataOutputStream out;
	
	public Client() {
		try {
			socket = new Socket("localhost", 8880);
			dis = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		while(true){
			try {
				chatIn = dis.readUTF();
				StringBuffer sb = new StringBuffer();
				sb.append(chatIn);
				System.out.println(sb.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
