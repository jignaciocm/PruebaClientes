package cl.pkg.PruebaClientes.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cl.pkg.PruebaClientes.modelo.*;

public class ClienteServicio {

	private List<Cliente> listaClientes;
	private Scanner sc = new Scanner(System.in);
	
	public ClienteServicio() {
		listaClientes = new ArrayList <Cliente>();
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public void listarClientes() {
		
		if (listaClientes != null && listaClientes.size() != 0) {
			for (Cliente clienteTemp : listaClientes) {
				System.out.println("-------------Datos del Cliente-------------");
				System.out.println("RUN del Cliente: "+clienteTemp.getRunCliente());
				System.out.println("Nombre del Cliente: "+clienteTemp.getNombreCliente());
				System.out.println("Apellido del Cliente: "+clienteTemp.getApellidoCliente());
				System.out.println("Años como Cliente: "+clienteTemp.getAniosCliente());
				System.out.println("Categoria del Cliente: "+clienteTemp.getNombreCategoria());
				System.out.println("");
				System.out.println("-------------------------------------------");
			}
		} else {
			System.out.println("\nNo se han encontrado clientes o no existen clientes");
		}
		
	}
	
	public void agregarClientes(Cliente cliente) {
		
		if (listaClientes != null) {
			listaClientes.add(cliente);
		} else {
			listaClientes = new ArrayList<Cliente>();
			listaClientes.add(cliente);
		}
		
		System.out.println("---------- CLIENTE AGREGADO ----------");
		
	}
	
	public void editarEstadoCliente(Cliente clienteTemp) {
		System.out.printf("El estado actual es %s\n", clienteTemp.getNombreCategoria());
		System.out.println("¿Desea cambiar el estado del cliente a inactivo? (S/N)");
		String respuesta = sc.nextLine().toUpperCase();
		while (!(respuesta.equals("S") || respuesta.equals("N"))) {
			System.out.println("Opción inválida. Ingrese nuevamente (S/N)");
			respuesta = sc.nextLine();
		}
		if (respuesta.equals("S")) {
			clienteTemp.setNombreCategoria(CategoriaEnum.INACTIVO);
			System.out.println("Estado cambiado\n");
		} 
	}

	public void editarDatosCliente(Cliente clienteTemp) {
		System.out.println("Seleccione que desea hacer?");
	
		System.out.println("1. Ingrese nuevo rut");
		
		System.out.println("2. Ingrese nuevo nombre");
	
		System.out.println("3. Ingrese nuevo apellido");
		
		System.out.println("4. Ingrese nuevos años");
		System.out.println("0. Retornar al menu anterior");
		
		String opcion = sc.nextLine();
		
		switch (opcion) {
		case "1":
			System.out.println("1. Ingrese nuevo rut");
			clienteTemp.setRunCliente(sc.nextLine());
			break;
		case "2":
			System.out.println("1. Ingrese nuevo nombre");
			clienteTemp.setNombreCliente(sc.nextLine());
			break;
		case "3":
			System.out.println("1. Ingrese nuevo apellido");
			clienteTemp.setApellidoCliente(sc.nextLine());	
			break;
		case "4":
			System.out.println("1. Ingrese nuevo años");
			clienteTemp.setAniosCliente(sc.nextLine());
		case "0":
			break;
		default:
			break;
		}
		
	}	
	
}
