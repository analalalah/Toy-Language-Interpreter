package Repository;

import Model.ProgramState;

import java.io.*;
import java.util.*;

/**
 * Created by vladc on 22.10.2016.
 */
public class Repository implements IRepository {
    private List<ProgramState>  list;
    private String logFilePath;
    private final String serializeFile = "res\\ProgramState.ser";

    public Repository(String logFilePath) {
        this.list = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public void add(ProgramState state) {
        this.list.add(state);
    }

    public void logProgramStateExec(ProgramState state) {
        try (FileWriter fw = new FileWriter(logFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw))
        {
            pw.println(state.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logEndOfExecution() {
        try (FileWriter fw = new FileWriter(logFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw))
        {
            pw.println("End of execution...");
            pw.println("##################################################");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(serializeFile));
            out.writeObject(this.list);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void deserialize() {
        ObjectInputStream in = null;
        @Deprecated
//        ProgramState state = null;
        List<ProgramState> new_list = null;
        try {
            in = new ObjectInputStream(new FileInputStream(serializeFile));
//            state = (ProgramState)in.readObject();
            new_list = (ArrayList<ProgramState>)in.readObject();
            if (new_list != null)
                this.list = new_list;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.err.println("ERROR while deserializating...");
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ProgramState> getProgramList() {
        return this.list;
    }

    public void setProgramList(List<ProgramState> list) {
        this.list = list;
    }
}
