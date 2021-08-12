package cl.pkg.PruebaClientes.vista;

import java.io.IOException;
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
	
	public void iniciarMenu() throws IOException, InterruptedException {
		System.out.println("---------- SISTEMA CLIENTES ----------");
		System.out.println("1. Listar Clientes");
		System.out.println("2. Agregar Clientes");
		System.out.println("3. Editar Clientes");
		System.out.println("4. Importar Datos");
		System.out.println("5. Exportar Datos");
		System.out.println("6. Salir");
		System.out.println("--------------------------------------");
		System.out.println("Ingrese una opción");
		
		int opcion = scInt.nextInt();
		seleccionMenu(opcion);
	}

	private void seleccionMenu(int opcion) throws IOException, InterruptedException {
		
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
			System.out.println("-------------Crear Cliente-------------");
			System.out.println("Ingrese RUN del cliente:");
			runCliente = sc.nextLine();
			if (Utilidad.validaRut(runCliente)) {
				System.out.println("Run ingresado es correcto");
			} else {
				System.out.println("Run ingresado es incorrecto");
			}
			
		} while (!Utilidad.validaRut(runCliente));
		
		System.out.println("Ingrese Nombre del Cliente");
		String nombreCliente = sc.nextLine();
		System.out.println("Ingrese Apellido del Cliente");
		String apellidoCliente = sc.nextLine();
		System.out.println("Ingrese años como cliente");
		String aniosCliente = sc.nextLine();
		System.out.println("-------------------------------------");
		
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
				System.out.println("Seleccione que desea hacer: ");
				System.out.println("1. Editar datos del cliente");
				System.out.println("2. Cambiar el estado de cliente");
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
	
	private void exportarDatos() throws IOException {
		System.out.println("Ingrese la ruta");
		String ruta = sc.nextLine();
		
		System.out.println("Desea exportar el archivo como: ");
		System.out.println("1. csv");
		System.out.println("2. txt");
		System.out.println("0. Salir");

		int opcion = scInt.nextInt();
		
		switch (opcion) {
		case 1:
			exportadorCsv.exportar(ruta, clienteServicio.getListaClientes());
			System.out.println("Se ha expotado con éxito");
			break;

		case 2:
			exportadorTxt.exportar(ruta, clienteServicio.getListaClientes());
			System.out.println("Se ha expotado con éxito");
			break;	

		case 0:
						
			break;
			
		default:
			System.out.println("Ingrese un número válido");
			exportarDatos();
			break;
		}
		
	}
	
	private void terminarPrograma() throws InterruptedException {
		System.out.println("Saliendo del programa...");
		Thread.sleep(2000);
		System.out.println("Programa cerrado correctamente");
		
	}
}
