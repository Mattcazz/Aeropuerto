package negocio.incidencias;

import java.sql.Date;

public abstract class TransferIncidencia {

	private TipoIncidencia tipo;
	private Date fecha;
    private String descripcion;
    private EstadoIncidencia estado;    
    private float compensacion;
    
    public TransferIncidencia(TipoIncidencia tipo, Date fecha, String descripcion, EstadoIncidencia estado , float compensacion) {
    	this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.compensacion = compensacion;
    }
    
    
    // Métodos set
    
	public TransferIncidencia() {
		// TODO Auto-generated constructor stub
	}


	public abstract void setId(String id);

    
    public void setTipo(TipoIncidencia t) {
    	this.tipo = t;
    }
	
	public void setFecha(Date f) {
		this.fecha = f;
	}
	
	public void setDescripcion(String d) {
		this.descripcion = d;
	}
	
	public void setEstado(EstadoIncidencia e) {
		this.estado = e;
	}
	
	public void setCompensacion(float c) {
		this.compensacion = c;
	}
	
	// Métodos get
	
	public TipoIncidencia getTipoIncidencia() {
		return this.tipo;
	}
	
	 public Date getFecha() {
	        return this.fecha;
	    }

	
	public String getDescripcion() {
        return this.descripcion;
    }

    public String getEstado() {
    	switch(this.estado) {
    	case RESUELTA:{
    		return "resuelta";   		
    	}
    	case NO_RESUELTA:{
    		return "no resuelta";   		
    	}
    	default:
    		return " ";
    	}
    	
    }

    public float getCompensacion() {
        return this.compensacion;
    }


	


	public  String getTipo() {
		return this.tipo.toString();
	}
    
    public String getTipoString() {
    	if(this.tipo == TipoIncidencia.VUELO) {
    		return "vuelo";
    	}
    	else {
    		return "equipaje";
    	}
    }

    // Métodos abstractos
    
    public abstract String getId();
    
	public abstract String getSolucion();
	
	public abstract void setSolucion(String s);
    
}


