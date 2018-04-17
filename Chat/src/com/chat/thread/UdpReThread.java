package com.chat.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReThread extends Thread {

	DatagramSocket ds;

	public UdpReThread(DatagramSocket ds) {

		this.ds = ds;

	}

	@Override
	public void run() {
		byte[] buf = new byte[1024];
		while (true) {
			try {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp);
				String content = new String(dp.getData(), 0, dp.getLength());
				String ip = dp.getAddress().getHostAddress();
				System.out.println(ip + ":" + content);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
