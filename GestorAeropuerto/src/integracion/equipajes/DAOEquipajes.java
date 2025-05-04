package integracion.equipajes;

import java.util.List;

import negocio.equipajes.TransferEquipaje;

public interface DAOEquipajes {
	
	void anadirEquipaje(TransferEquipaje equ);

	List<TransferEquipaje> obtenerPorIdVuelo(String id_vuelo);

	void modificarEquipaje(TransferEquipaje equ);

	void eliminarEquipaje(TransferEquipaje equ);
}
