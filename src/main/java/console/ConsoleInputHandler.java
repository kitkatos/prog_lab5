package console;

import java.util.Scanner;

public class ConsoleInputHandler {

    public String readTrimLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
