package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VueloCreado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		VueloCreado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		VueloCreado.subscribers.remove(subscriber);
	}
	
	public static void publish(String vueloId) {
		for (ObserverVuelos observer : VueloCreado.subscribers) {
			observer.event(vueloId);
		}
	}

}
