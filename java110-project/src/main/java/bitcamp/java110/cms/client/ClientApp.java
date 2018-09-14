package bitcamp.java110.cms.client;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        try (
                //autoclosable interface를 구현한 것만 try의 ()안에 넣었을 때, 자동 close됨!    =>  finally 필요 없음!(자동close)
                //File f = new File("okok");, String s = "okok"; 등과 같은건 되지 않음. 

                // 서버에 연결하기
                Socket socket = new Socket("localhost", 8888);  //8888 서버에 접속, 서버연결시 return을 받음.

                // 서버에 데이터를 보내고 읽을 도구를 준비하기
                /*        OutputStream out = socket.getOutputStream();    //outputStream은 byte단위이기 때문에 .
        BufferedOutputStream out1 = new BufferedOutputStream(out); // 사이에 붙여서 버퍼에 모아 넣어야 함
        PrintStream out2 = new PrintStream(out1);    //한줄씩 프린트하는 데코레이터.outputStream을 그대로 쓰지말고 붙여서 쓰면 println사용가능.
                 */        
                //위의 세줄과 같음.
                PrintStream out = new PrintStream(
                        new BufferedOutputStream(
                                socket.getOutputStream())); 

                /*        InputStream in1 = socket.getInputStream();
        InputStreamReader in2 = new InputStreamReader(in1); //bytestream을 characterstream으로 바꿔주는 역할!
        BufferedReader in3 = new BufferedReader(in2); //BufferedInputStream은 한줄단위로 읽지 못함.
                 */        

                //위의 세줄과 같음.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream())); 
                ){
            out.println("HELLO");   // server에서 내가 출력한걸 읽어야 작업을 끝내고 return하여 다음 라인으로 이동하게 됨. 
            // 서버에서 읽었을 때 실행이 끝남. 서버가 읽지 않으면 다음작업으로 가지 못함.
            out.flush();
            System.out.println(in.readLine());

            while (true) {
                String requestLine = prompt();
                out.println(requestLine);   //서버가 나에게 응답?
                out.flush();
                
                while(true) {
                    String responseLine = in.readLine();
                    System.out.println(responseLine);
                    if(responseLine.length() == 0)
                        break;
                }
                if (requestLine.equals("EXIT")){
                    break;
                } 
            }
        }

        keyIn.close();
    }

    private static String prompt() {
        System.out.print("입력> ");
        return keyIn.nextLine();
    }
}







