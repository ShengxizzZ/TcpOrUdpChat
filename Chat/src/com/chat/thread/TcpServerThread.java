package com.chat.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class TcpServerThread extends Thread{
	
	ServerSocket serverSocket;
	Vector<TcpClientThread> clients;
	Vector<Object> messages;
	InetAddress adress;
	String ip;
	
	public TcpServerThread() {

		try {
			clients = new Vector<>();
			messages  =new Vector<>();	
			serverSocket = new ServerSocket(8880);
			adress = InetAddress.getLocalHost();
			ip = adress.getHostAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		
		while(true){
			try {
				Socket socket = serverSocket.accept();
				System.out.println("等待连接。。。。");
				TcpClientThread client = new TcpClientThread(socket,this);
				client.start();
				
				if(socket!=null){
					synchronized (clients) {
						clients.addElement(client);
					}
				}
			} catch (IOException e) {
				System.err.println("发生异常:" + e);
				System.err.println("建立客户端联机失败!");
				System.exit(2);
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void finalize() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			serverSocket = null;
			e.printStackTrace();
		}
	}
}
