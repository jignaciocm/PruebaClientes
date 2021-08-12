package cl.pkg.PruebaClientes.servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.pkg.PruebaClientes.modelo.Cliente;

public class ExportadorTXT extends Exportador{

	@Override
	public void exportar(String fileName, List<Cliente> listaClientes) throws IOException {
		
		File archivo = new File ("src" + File.separator + fileName + ".txt"); //ruta 
		
		if (!archivo.exists()) {
			
				archivo.createNewFile();
	
		} else {
			FileWriter fileWriter = new FileWriter(archivo);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (Cliente clienteTemp : listaClientes) {
				bufferedWriter.write(clienteTemp.getRunCliente() +"," + clienteTemp.getNombreCliente()+ ","+ clienteTemp.getApellidoCliente()+ ","+
						clienteTemp.getAniosCliente()+","+clienteTemp.getNombreCategoria());
			bufferedWriter.newLine();			
			}
			
			bufferedWriter.close();
			fileWriter.close();
			
		}
		
	}

}
