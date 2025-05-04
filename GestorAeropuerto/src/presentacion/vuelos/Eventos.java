package presentacion.vuelos;

public enum Eventos {
	MENU_PRINCIPAL,
	VOLVER_MENU,
	
	// Menus CUs
	// Aqui poner los eventos relacionados con la apertura de cada pantalla del CU
	// Formato: ABRIR_MENU_[CU]
	// Ejemplo
	ABRIR_MENU_CREAR_VUELO,
	ABRIR_MENU_ACTUALIZAR_VUELO,
	ABRIR_MENU_ELIMINAR_VUELO,
	ABRIR_MENU_BUSCAR_VUELO,
	
	ABRIR_MENU_CREAR_AVION,
	
	// Operaciones CUs
	// Aqui poner los eventos relacionados con los botones de las pantallas
	// Por ejemplo abrir submenus, hacer una operacion, mostrar un mensaje
	// Formato: [Operacion]_[Objeto]_{Detalle, si es necesario}
	// Ejemplo: ABRIR_SUBMENU_SELECCION_FECHA
	// Ejemplo: CREAR_VUELO (para terminar la creacion del vuelo a partir de un Builder o Factoria, por ejemplo)
	// Ejemplo: MOSTRAR_MENSAJE_ID_VUELO_INVALIDO
	// Ejemplo: ABRIR_PANTALLA_LISTA_VUELOS
	// Ejemplo
	CREAR_VUELO,
	ABRIR_SUBMENU_ACTUALIZAR_VUELO,
	ACTUALIZAR_VUELO,
	ELIMINAR_VUELO,
	BUSCAR_VUELO,
	
	CREAR_AVION,
	
	MOSTRAR_MENSAJE,
	
	// Misc
	// Aqui poner cualquier evento que no entre en las otras dos categorias
	// Usad un formato similar por favor
};