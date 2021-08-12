package cl.pkg.PruebaClientes.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import cl.pkg.PruebaClientes.modelo.Cliente;
import cl.pkg.PruebaClientes.modelo.CategoriaEnum;

public class ArchivoServicio {


	public ArrayList<Cliente> cargarDatos(String nombreArchivo) {
		
		ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
	
		
		File text = new File("src" + File.separator + nombreArchivo + ".csv");
		
		if (text.exists()) {
			try {
				FileReader fr = new FileReader(text);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();
				
				
				if (data == null) {
					System.out.println("El archivo de texto está vacío");
				}else {
					while(data !=null) {
						ArrayList<String> datos = new ArrayList<String>(Arrays.asList(data.split(",")));
						Cliente cliente = new Cliente(datos.get(0), datos.get(1), datos.get(2), datos.get(3), CategoriaEnum.valueOf(datos.get(4).toUpperCase()));
						arrayClientes.add(cliente);
						data = br.readLine();
					}
				}
				br.close();
				System.out.println("Datos cargados exitosamente.");
			} catch (IOException e) {
				System.out.println("Error importando datos. Intente nuevamente");
			
			}
		
		}else {
			System.out.println("Archivo no encontrado. Favor revisar que este exista");
		}
		return arrayClientes;
		
	}

	
}
