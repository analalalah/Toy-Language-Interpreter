package View;

import Controller.Controller;
import Exception.MyStatementExecutionException;
/**
 * Created by vladc on 08.11.2016.
 */
public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        }
        catch (MyStatementExecutionException e) {
            // todo: treat the exceptions that may occur
            e.printStackTrace();
        }
    }
}
