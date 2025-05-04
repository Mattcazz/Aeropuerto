package negocio.equipajes;

import java.util.List;

public interface SAEquipajes {

	List<TransferEquipaje> equipajesPorVuelo(String id_vuelo);

	List<String> listaVuelos();

	void modificarEquipaje(TransferEquipaje equ);

	void eliminarEquipaje(TransferEquipaje equ);

	void registrarEquipaje(TransferEquipaje te);
}
