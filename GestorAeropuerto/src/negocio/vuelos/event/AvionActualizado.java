package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AvionActualizado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		AvionActualizado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		AvionActualizado.subscribers.remove(subscriber);
	}
	
	public static void publish(String avionId) {
		for (ObserverVuelos observer : AvionActualizado.subscribers) {
			observer.event(avionId);
		}
	}

}
