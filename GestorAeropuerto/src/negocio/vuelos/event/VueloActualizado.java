package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VueloActualizado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		VueloActualizado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		VueloActualizado.subscribers.remove(subscriber);
	}
	
	public static void publish(String vueloId) {
		for (ObserverVuelos observer : VueloActualizado.subscribers) {
			observer.event(vueloId);
		}
	}

}
