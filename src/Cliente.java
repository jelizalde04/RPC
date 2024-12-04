import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Para leer la entrada del usuario
        try {
            // Conectarse al servidor en el puerto 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor");

            // Crear flujo de salida para enviar mensajes al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Crear flujo de entrada para recibir la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Permitir que el usuario ingrese varios mensajes
            String mensaje;
            while (true) {
                // Solicitar al usuario un mensaje
                System.out.print("Ingresa un mensaje para enviar al servidor (o 'salir' para terminar): ");
                mensaje = scanner.nextLine();

                // Si el usuario ingresa "salir", termina el cliente
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando conexión con el servidor...");
                    break;
                }

                // Enviar el mensaje al servidor
                out.println(mensaje);

                // Leer y mostrar la respuesta del servidor
                String respuesta = in.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            // Cerrar la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
