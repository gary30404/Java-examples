import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception{
        String strLocal, strSocket;

        ServerSocket ssocketWelcome = new ServerSocket(1234);

            Socket socketServer = ssocketWelcome.accept();

        //BufferedReader brInFromUser = new BufferedReader(new InputStreamReader(System.in));

            String str = "Hi";
        BufferedReader brInFromUser = new BufferedReader(new StringReader(str));
        BufferedReader brInFromClient = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));

        DataOutputStream dosOutToClient = new DataOutputStream(socketServer.getOutputStream());

        //do{
            strSocket = brInFromClient.readLine();
            System.out.println("Client: "+strSocket);
            strLocal = brInFromUser.readLine();
            dosOutToClient.writeBytes(strLocal+'\n');

            
        //}while(!strSocket.equals("bye"));
        
        

        socketServer.close();
    }
}