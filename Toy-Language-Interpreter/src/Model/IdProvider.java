package Model;

/**
 * @author vladc
 */
public class IdProvider {
    private static int current_id = 1;

    public static int getNextId() {
        current_id++;
        return current_id;
    }
}
