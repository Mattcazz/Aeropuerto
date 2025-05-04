package negocio.equipajes;

public class TransferEquipaje {
	
	int _id;
	String _id_vuelo;
	double _peso;
	
	public TransferEquipaje(){
		
	}
	
	public TransferEquipaje(String id_vuelo, double d){
		_id= -1;
		_id_vuelo=id_vuelo;
		_peso= d;
	}
	
	public TransferEquipaje(int id, String id_vuelo, double d) {
		// TODO Auto-generated constructor stub
		_id= id;
		_id_vuelo=id_vuelo;
		_peso= d;
	}

	public void setId(int id){
		_id= id;
	}
	
	public void setNombre(String id_vuelo){
		_id_vuelo= id_vuelo;
	}
	
	public void peso(Float peso){
		_peso= peso;
	}
	
	public int getId()	{
		return _id;
	}
	
	public String getIdVuelo(){
		return _id_vuelo;
	}
	
	public double getPeso(){
		return _peso;
	}
	
}
