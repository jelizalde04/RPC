import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear un servidor que escucha en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexión en puerto 12345...");

            // Aceptar una conexión de cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado");

            // Crear flujo de entrada para leer los mensajes del cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Crear flujo de salida para enviar la respuesta al cliente
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String mensajeCliente;
            while (true) {
                // Leer el mensaje del cliente
                mensajeCliente = in.readLine();
                if (mensajeCliente == null) {
                    break; // Si el cliente cierra la conexión, salimos
                }

                System.out.println("Mensaje del cliente: " + mensajeCliente);

                // Si el mensaje es "salir", el servidor cierra la conexión
                if (mensajeCliente.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }

                // Responder al cliente con el mensaje recibido
                out.println("Servidor: Has enviado el mensaje: " + mensajeCliente);
            }

            // Cerrar la conexión
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
