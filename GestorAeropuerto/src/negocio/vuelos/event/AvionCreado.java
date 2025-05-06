package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AvionCreado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		AvionCreado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		AvionCreado.subscribers.remove(subscriber);
	}
	
	public static void publish(String avionId) {
		for (ObserverVuelos observer : AvionCreado.subscribers) {
			observer.event(avionId);
		}
	}

}
