package ServerSide;
import IDisplay.IDisplayGoodBye;
import IDisplay.IDisplayHello;
import IDisplay.IDisplaySorry;

public class Display implements IDisplayHello , IDisplaySorry, IDisplayGoodBye {
    @Override
    public void ShowHelloMessage() {
        System.out.println("Hello,Connected Successfully.");
    }

    @Override
    public void ShowSorryMessage() {
        System.out.println("Sorry, Connected Failed Or Connected Cut With Client.");

    }

    @Override
    public void ShowGoodByeMessage() {
        System.out.println("Good Bye :)");
    }
}
