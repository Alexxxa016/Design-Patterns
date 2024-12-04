package view;

public class ConsoleView implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }
}
