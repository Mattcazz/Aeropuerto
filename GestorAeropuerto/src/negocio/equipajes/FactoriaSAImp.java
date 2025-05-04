package negocio.equipajes;

public class FactoriaSAImp extends FactoriaSA{
	
	public SAEquipajes nuevoSAEquipajes() {
		return new SAEquipajesImp();
	}
}
