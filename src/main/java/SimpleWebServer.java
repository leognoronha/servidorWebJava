import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        final int PORT = 8080; // Porta do servidor

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor aguardando conexões na porta " + PORT + "...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão estabelecida com " + clientSocket);

            // Cria uma thread para lidar com a requisição do cliente
            new Thread(() -> {
                try {
                    handleRequest(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //recebe a requisição
        OutputStream out = clientSocket.getOutputStream(); // envia a resposta

        String request = in.readLine();
        StringTokenizer tokenizer = new StringTokenizer(request);
        String method = tokenizer.nextToken();
        String path = tokenizer.nextToken();

        if (method.equals("GET")) {
            if (path.equals("/")) {
                path = "/index.html"; // Página HTML
            }

            // Lê o arquivo correspondente à URL requisitada
            try {
                byte[] content = Files.readAllBytes(Paths.get("src/main/resources/static" + path));
                out.write(("HTTP/1.1 200 OK\r\nContent-Length: " + content.length + "\r\n\r\n").getBytes());
                out.write(content);
            } catch (IOException e) {
                // Arquivo não encontrado
                out.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
            }
        }

        // Fecha os streams e o socket
        out.close();
        in.close();
        clientSocket.close();
    }
}
