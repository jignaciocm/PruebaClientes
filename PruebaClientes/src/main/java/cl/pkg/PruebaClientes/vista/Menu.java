package cl.pkg.PruebaClientes.vista;

import java.util.ArrayList;
import java.util.Scanner;

import cl.pkg.PruebaClientes.modelo.CategoriaEnum;
import cl.pkg.PruebaClientes.modelo.Cliente;
import cl.pkg.PruebaClientes.servicio.ArchivoServicio;
import cl.pkg.PruebaClientes.servicio.ClienteServicio;
import cl.pkg.PruebaClientes.servicio.ExportadorCSV;
import cl.pkg.PruebaClientes.servicio.ExportadorTXT;
import cl.pkg.PruebaClientes.utilidades.Utilidad;


public class Menu {

	ClienteServicio clienteServicio = new ClienteServicio();
	ArchivoServicio archivoServicio = new ArchivoServicio();
	ExportadorCSV exportadorCsv = new ExportadorCSV();
	ExportadorTXT exportadorTxt = new ExportadorTXT();
	Scanner sc = new Scanner (System.in);//Scanner String
	Scanner scInt = new Scanner (System.in);//Scanner int
	
	private String fileName = "Clientes";
	private String fileName1 = "DBClientes.csv";
	
	public void iniciarMenu() {
		System.out.println("----MENU PRINCIPAL----");
		System.out.println("1. Listar Clientes");
		System.out.println("2. Agregar Clientes");
		System.out.println("3. Editar Clientes");
		System.out.println("4. Importar Datos");
		System.out.println("5. Exportar Datos");
		System.out.println("6. Terminar Programa");
		System.out.println("----FINAL MENU----");
		System.out.println("Ingrese una opción");
		
		int opcion = scInt.nextInt();
		seleccionMenu(opcion);
	}

	private void seleccionMenu(int opcion) {
		
		switch (opcion) {
		case 1:
			listarClientes();
			iniciarMenu();
			break;
		case 2:
			agregarClientes();
			iniciarMenu();
			break;

		case 3:
			editarClientes();
			iniciarMenu();
			break;

		case 4:
			importarDatos();
			iniciarMenu();
			break;

		case 5:
			exportarDatos();
			iniciarMenu();
			break;
			
		case 6:
			terminarPrograma();
			break;

		default:
			System.out.println("¡Ingresaste una opción incorrecta!");
			break;
		}
		
	}

	private void listarClientes() {
		System.out.println("Listando los clientes Existentes");
		clienteServicio.listarClientes();
		
	}
	
	private void agregarClientes() {
		
		String runCliente="";
		do {
			
			System.out.println("Ingrese el RUN del cliente Ej 27011011-1");
			runCliente = sc.nextLine();
			if (Utilidad.validaRut(runCliente)) {
				System.out.println("Run ingresado es correcto");
			} else {
				System.out.println("Run ingresado es incorrecto");
			}
			
		} while (!Utilidad.validaRut(runCliente));
		
		System.out.println("Ingrese el nombre del cliente");
		String nombreCliente = sc.nextLine();
		System.out.println("Ingrese el apellido del Cliente");
		String apellidoCliente = sc.nextLine();
		System.out.println("Ingrese los años como cliente");
		String aniosCliente = sc.nextLine();

		
		Cliente cliente = new Cliente(runCliente,nombreCliente,apellidoCliente,aniosCliente,CategoriaEnum.ACTIVO);
		
		clienteServicio.agregarClientes(cliente);
	}
	
	private void editarClientes() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese rut de cliente que desea editar:");
		String rut = sc.nextLine();
		
		for (Cliente clienteTemp : clienteServicio.getListaClientes()) {
			if (rut.equals(clienteTemp.getRunCliente())) {
				
				String opcion = "";
				do  {	
				System.out.println("¿Qué desea hacer?:");
				System.out.println("1. Editar datos del cliente");
				System.out.println("2. Editar estado de cliente");
				System.out.println("0. Retornar al menu anterior");
				opcion = sc.nextLine();
				
				switch (opcion) {
				case "1":
					clienteServicio.editarDatosCliente(clienteTemp);		
					break;
				case "2":
					clienteServicio.editarEstadoCliente(clienteTemp);
					break;

				default:
					break;
				}
				}while (!opcion.equals("0"));
			}
		}
		
	}
	


	public void importarDatos() {
		
		System.out.println("Ingrese nombre del archivo");
		String nombreArchivo = sc.nextLine();
		
		ArrayList<Cliente> arrayImportado = archivoServicio.cargarDatos(nombreArchivo);
		
		if (arrayImportado.size() == 0) {
			importarDatos();
		} else {
			clienteServicio.getListaClientes().addAll(arrayImportado);
		}
		
			
	}
	
	private void exportarDatos() {
		// TODO Auto-generated method stub
		
	}
	
	private void terminarPrograma() {
		// TODO Auto-generated method stub
		
	}
}
