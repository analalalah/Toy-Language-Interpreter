package Repository;

import Model.ProgramState;

import java.io.*;
import java.util.*;

/**
 * Created by vladc on 22.10.2016.
 */
public class Repository implements IRepository {
    private Vector<ProgramState> programStates;
    private String logFilePath;
    private final String serializeFile = "res\\ProgramState.ser";

    public Repository(String logFilePath) {
        this.programStates = new Vector<>();
        this.logFilePath = logFilePath;
    }

    // temporary
    public ProgramState getCurrentProgram() {
        return programStates.get(programStates.size() - 1);
    }

    public void add(ProgramState state) {
        programStates.addElement(state);
    }

    public void logProgramStateExec() {
        try (FileWriter fw = new FileWriter(logFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw))
        {
            pw.println(this.getCurrentProgram().toString());
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
            out.writeObject(this.getCurrentProgram());
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

    public ProgramState deserialize() {
        ObjectInputStream in = null;
        ProgramState state = null;
        try {
            in = new ObjectInputStream(new FileInputStream(serializeFile));
            state = (ProgramState)in.readObject();
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

        if (state != null) {
            this.add(state);
        }

        return state;
    }
}
