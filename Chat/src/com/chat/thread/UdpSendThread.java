package com.chat.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSendThread extends Thread {

	DatagramSocket ds;

	public UdpSendThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String brStr;
		try {
			while ((brStr = br.readLine()) != null) {
				DatagramPacket dp = new DatagramPacket(brStr.getBytes(), brStr.getBytes().length,
						InetAddress.getByName("localhost"), 8888);
				
				ds.send(dp);
			}
			
			ds.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
