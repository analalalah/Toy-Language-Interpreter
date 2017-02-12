package GUI;

import Controller.Controller;
import DataTypes.MyPair;
import Model.ProgramState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author vladc
 */
public class MainWindowController {
    private Controller ctrl;

    MainWindowController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    Controller getController() {
        return ctrl;
    }

    List<Integer> getProgramStateIdentifiersList() {
        List<ProgramState> programList = ctrl.getProgramList();
        List<Integer> idList = new ArrayList<>();

        for (ProgramState p : programList) {
            idList.add(p.getId());
        }

        return idList;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    ObservableList<Integer> getOutObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<Integer> list = new ArrayList<>();
        Iterator<Integer> it = program.getOut().iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

        return FXCollections.observableArrayList(list);
    }

    @NotNull
    ObservableList<String> getExeStackObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<String> list = new ArrayList<>();

        Iterator it = program.getExeStack().iterator();

        while (it.hasNext()) {
            list.add(0, it.next().toString());
        }

        return FXCollections.observableArrayList(list);
    }

    @NotNull
    ObservableList<Map.Entry<String, Integer>> getSymTableObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> it = program.getSymTable().getDict().entrySet().iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

        return FXCollections.observableArrayList(list);
    }

    @NotNull
    ObservableList<Map.Entry<Integer, MyPair<String, BufferedReader>>> getFileTableObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<Map.Entry<Integer, MyPair<String, BufferedReader>>> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, MyPair<String, BufferedReader>>> it = program.getFileTable().getDict().entrySet().iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

        return FXCollections.observableArrayList(list);
    }

    @NotNull
    ObservableList<Map.Entry<Integer, Integer>> getHeapTableObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, Integer>> it = program.getHeap().getContent().entrySet().iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

        return FXCollections.observableArrayList(list);
    }

    @NotNull
    ObservableList<Map.Entry<Integer, Integer>> getLatchTableObservableList(int id) {
        ProgramState program = ctrl.getProgramById(id);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, Integer>> it = program.getLatchTable().getContent().entrySet().iterator();

        while (it.hasNext()) {
            list.add(it.next());
        }

        return FXCollections.observableArrayList(list);
    }
}
