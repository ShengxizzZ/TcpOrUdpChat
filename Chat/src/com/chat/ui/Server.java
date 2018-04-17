package com.chat.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.chat.thread.TcpServerThread;

public class Server {
	
	
	
	public static void main(String[] args) {
	 TcpServerThread tcpServerThread =null;
		
		show();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int action = 0;
		try {
			action = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(action){
		case 1:
			tcpServerThread =new TcpServerThread();
			tcpServerThread.start();
			
			break;
		case 2 :

			tcpServerThread.finalize();
			
			break;
		}
	}

	private static void show() {
		System.out.println("选择服务器操作：1、打开服务器；2、关闭服务器");
		
	}

}
