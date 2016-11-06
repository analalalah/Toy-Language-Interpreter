import Repository.*;
import Controller.Controller;
import View.View;

/**
 * Created by vladc on 28.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        IRepository repo    = new Repository();
        Controller ctrl     = new Controller(repo);
        View ui             = new View(ctrl);
        ui.run();
    }
}
