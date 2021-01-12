package com.path.atm.netty.text.client;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.bootstrap.Bootstrap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		new Client("localhost", 8000).run();
	}

	private final String host;
	private final int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new io.netty.bootstrap.Bootstrap().group(group).channel(NioSocketChannel.class)
					.handler(new ChatClientHandlerInitializer());

			Channel channel = null;

			String xmlFilePath = "C:\\Users\\USEr\\Desktop\\XML files\\UN_MAIN.xml";
			String jsonFilePath = "C:\\Users\\USEr\\Desktop\\XML files\\testing.json";
			String csvFilePath = "C:\\Users\\USEr\\Desktop\\COA DTL\\main_coa.csv";
			String textFilePath = "E:/imall project data/Omni Channel/scripts/activity drop down.txt";
			String content = "";

			int choice = 0, msgChoice = 0;
			do {
				System.out.println("Select from the flollowing menue\n1-Connect with ATM Server \n2-Quit");
				choice = new Scanner(System.in).nextInt();
				switch (choice) {
				case 1:
					channel = bootstrap.connect(host, port).sync().channel();
					System.out.println("Client is Connected with ATM Interface");

					do {
						System.out.println("Select Message to send\n1-XML\n2-CSV\n3-Text\n4-JSON\n5-Stop Connection");
						msgChoice = new Scanner(System.in).nextInt();
						switch (msgChoice) {
						case 1:
							content = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
							channel.writeAndFlush(content + "\r\n");
							break;
						case 2:
							content = new String(Files.readAllBytes(Paths.get(csvFilePath)));
							channel.writeAndFlush(content + "\r\n");
							break;
						case 3:
							content = new String(Files.readAllBytes(Paths.get(textFilePath)));
							channel.writeAndFlush(content + "\r\n");
							break;
						case 4:
							content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
							channel.writeAndFlush(content);
							break;
						default:
							break;
						}
					} while (msgChoice != 5);
					break;
				}
			} while (choice != 2);
		} finally {
			group.shutdownGracefully();
		}
	}
}
