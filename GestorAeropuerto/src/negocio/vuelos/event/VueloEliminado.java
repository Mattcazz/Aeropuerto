package negocio.vuelos.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VueloEliminado {
	private static final List<ObserverVuelos> subscribers = new CopyOnWriteArrayList<ObserverVuelos>();
	
	public static void subscribe(ObserverVuelos subscriber) {
		VueloEliminado.subscribers.add(subscriber);
	}

	public static void unsubscribe(ObserverVuelos subscriber) {
		VueloEliminado.subscribers.remove(subscriber);
	}
	
	public static void publish() {
		for (ObserverVuelos observer : VueloEliminado.subscribers) {
			observer.event();
		}
	}

}
