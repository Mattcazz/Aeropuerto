package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AvionEliminado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		AvionEliminado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		AvionEliminado.subscribers.remove(subscriber);
	}
	
	public static void publish(String avionId) {
		for (ObserverVuelos observer : AvionEliminado.subscribers) {
			observer.event(avionId);
		}
	}

}
