package cl.pkg.PruebaClientes.servicio;

import java.util.List;

import cl.pkg.PruebaClientes.modelo.Cliente;

public abstract class Exportador {

	public abstract void exportar(String fileName, List<Cliente>listaClientes);
}
