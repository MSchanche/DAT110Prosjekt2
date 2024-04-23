package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main (String[] args) {

		System.out.println("Display starting ...");

		// Assuming the broker is running on localhost and the default port is 8080
		String brokerHost = "localhost";
		int brokerPort = 8080;
		String displayUsername = "display";
		String topic = "Temperature"; // The topic to which the display subscribes

		// create a client object
		Client displayClient = new Client(displayUsername, brokerHost, brokerPort);

		// connect to the broker
		displayClient.connect();

		// create the temperature topic on the broker
		displayClient.createTopic(topic);

		// subscribe to the topic
		displayClient.subscribe(topic);

		// receive messages on the topic
		for (int i = 0; i < COUNT; i++) {
			Message message = displayClient.receive();

			if (message instanceof PublishMsg) {
				String temperature = ((PublishMsg) message).getMessage();
				System.out.println("Display: " + temperature);
			} else {
				System.err.println("Received non-publish message: " + message.getClass().getSimpleName());
			}

			// Optional: Sleep to simulate time spent handling the message
			try {
				Thread.sleep(1000); // Sleep for 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// unsubscribe from the topic
		displayClient.unsubscribe(topic);

		// disconnect from the broker
		displayClient.disconnect();

		System.out.println("Display stopping ... ");
	}
}
