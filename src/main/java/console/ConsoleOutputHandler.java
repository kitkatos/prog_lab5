package console;

public class ConsoleOutputHandler {
    public void printLine(String str){
        System.out.println(str);
    }

    public void printString(String str){
        System.out.print(str);
    }

    public void printError(String str){
        System.out.println("$ " + str);
    }
}
