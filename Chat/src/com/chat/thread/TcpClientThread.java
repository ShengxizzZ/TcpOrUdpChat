package com.chat.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpClientThread extends Thread{
	
	Socket socket;
	TcpServerThread serverThread;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	String str;
	public TcpClientThread(Socket s,TcpServerThread tcpServerThread) {
		
		this.socket = s;
		this.serverThread = tcpServerThread;

		try {
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {

			System.out.println("�����쳣" + e);
			System.out.println("����I/Oͨ��ʧ��!");
			System.exit(3);
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		
		while(true){
			
			try {
				String msg  = dataInputStream.readUTF();
				synchronized (serverThread.messages) {
					serverThread.messages.addElement(msg);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
}
