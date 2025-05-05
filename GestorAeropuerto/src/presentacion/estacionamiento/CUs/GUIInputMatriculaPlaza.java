package presentacion.estacionamiento.CUs;

public abstract class GUIInputMatriculaPlaza {
	
    private static GUIInputMatriculaPlaza instancia = GUIInputMatriculaPlazaImp.getInstancia();
    
    public static GUIInputMatriculaPlaza getInstancia() { 
    	return instancia; 
    	
    }
    
    public abstract void mostrar(int numeroPlaza);
}