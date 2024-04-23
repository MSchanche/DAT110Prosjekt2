package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// Assuming the broker is running on localhost and the default port is 8080
		String brokerHost = "localhost";
		int brokerPort = 8080;
		String sensorUsername = "sensor";
		String topic = "Temperature"; // Define the topic under which to publish the temperatures

		// create a client object
		Client client = new Client(sensorUsername, brokerHost, brokerPort);

		// connect to the broker
		client.connect();

		// publish the temperatures
		for (int i = 0; i < COUNT; i++) {
			int temp = sn.read();
			System.out.println("READING: " + temp);
			client.publish(topic, "Temperature reading: " + temp);

			try {
				// Sleep for a while to simulate sensor reading delay
				Thread.sleep(1000); // 1 second delay
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		client.disconnect();

		System.out.println("Temperature device stopping ... ");
	}
}
