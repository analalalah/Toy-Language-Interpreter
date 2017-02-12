package GUI;

import DataTypes.MyPair;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.util.Map;

/**
 * @author vladc
 */
public class MainWindow extends Application {
    private MainWindowController mainWindowController;

    private final Paint color = Color.LIGHTGREEN;
    private HBox root;
    private Scene scene;
    private TextField nrOfProgramStatesTextFields;
    private TableView<Map.Entry<Integer, Integer>> heapTableView;
    private ListView<Integer> outListView;
    private TableView<Map.Entry<Integer, MyPair<String, BufferedReader>>> fileTableView;
    private ListView<Integer> programStateIdentifiersListView;
    private TableView<Map.Entry<String, Integer>> symTableView;
    private ListView<String> exeStackListView;
    private Button runOneStepButton;

    private TableView<Map.Entry<Integer, Integer>> latchTableView;

    private VBox leftColumn;
    private VBox middleColumn;
    private VBox rightColumn;

    MainWindow(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @Override
    public void start(Stage stage) {
        setUpGUI(stage);

        programStateIdentifiersListView.setItems(FXCollections.observableArrayList(
                mainWindowController.getProgramStateIdentifiersList()
        ));
        programStateIdentifiersListView.getSelectionModel().select(0);

        int index = programStateIdentifiersListView.getSelectionModel().getSelectedItem();

        heapTableView.setItems(mainWindowController.getHeapTableObservableList(index));
        outListView.setItems(mainWindowController.getOutObservableList(index));
        fileTableView.setItems(mainWindowController.getFileTableObservableList(index));
        symTableView.setItems(mainWindowController.getSymTableObservableList(index));
        exeStackListView.setItems(mainWindowController.getExeStackObservableList(index));

        latchTableView.setItems(mainWindowController.getLatchTableObservableList(index));

        programStateIdsViewSelectionChangeHandler();
        runButtonHandler();

        stage.show();
    }

    private void setUpGUI(Stage stage) {
        root = new HBox(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER_LEFT);

        scene = new Scene(root, 780, 680, color);

        stage.setTitle("Toy Language Interpreter | JavaFX");
        stage.setScene(scene);

        leftColumn = new VBox(5);
        middleColumn = new VBox(5);
        rightColumn = new VBox(5);

        setUpLeftColumn();
        setUpMiddleColumn();
        setUpRightColumn();

        root.getChildren().add(leftColumn);
        root.getChildren().add(middleColumn);
        root.getChildren().add(rightColumn);
    }

    private void setUpLeftColumn() {
        nrOfProgramStatesTextFields = new TextField(Integer.toString(1));

        runOneStepButton = new Button("Run One Step");

        exeStackListView = new ListView<>(FXCollections.observableArrayList());

        heapTableView = new TableView<>();
        TableColumn<Map.Entry<Integer, Integer>, String> column1 = new TableColumn<>("address");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey().toString());
            }
        });
        TableColumn<Map.Entry<Integer, Integer>, String> column2 = new TableColumn<>("value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
        heapTableView.getColumns().add(column1);
        heapTableView.getColumns().add(column2);

        leftColumn.getChildren().add(new Label("Number of program states:"));
        leftColumn.getChildren().add(nrOfProgramStatesTextFields);
        leftColumn.getChildren().add(runOneStepButton);
        leftColumn.getChildren().add(new Label("Execution stack:"));
        leftColumn.getChildren().add(exeStackListView);
        leftColumn.getChildren().add(new Label("Heap Table:"));
        leftColumn.getChildren().add(heapTableView);
    }

    private void setUpMiddleColumn() {
        outListView = new ListView<>(FXCollections.observableArrayList());

        fileTableView = new TableView<>();
        TableColumn<Map.Entry<Integer, MyPair<String, BufferedReader>>, String> column1 = new TableColumn<>("identifier");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, MyPair<String, BufferedReader>>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, MyPair<String, BufferedReader>>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey().toString());
            }
        });
        TableColumn<Map.Entry<Integer, MyPair<String, BufferedReader>>, String> column2 = new TableColumn<>("value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, MyPair<String, BufferedReader>>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, MyPair<String, BufferedReader>>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
        fileTableView.getColumns().add(column1);
        fileTableView.getColumns().add(column2);

        middleColumn.getChildren().add(new Label("Out list:"));
        middleColumn.getChildren().add(outListView);
        middleColumn.getChildren().add(new Label("File table:"));
        middleColumn.getChildren().add(fileTableView);
    }

    private void setUpRightColumn() {
        programStateIdentifiersListView = new ListView<>(FXCollections.observableArrayList());

        symTableView = new TableView<>();
        TableColumn<Map.Entry<String, Integer>, String> column1 = new TableColumn<>("variable");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey());
            }
        });
        TableColumn<Map.Entry<String, Integer>, String> column2 = new TableColumn<>("value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
        symTableView.getColumns().add(column1);
        symTableView.getColumns().add(column2);


        latchTableView = new TableView<>();
        TableColumn<Map.Entry<Integer, Integer>, String> c1 = new TableColumn<>("location");
        c1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey().toString());
            }
        });
        TableColumn<Map.Entry<Integer, Integer>, String> c2 = new TableColumn<>("value");
        c2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
        latchTableView.getColumns().add(c1);
        latchTableView.getColumns().add(c2);

        rightColumn.getChildren().add(new Label("ProgramState identifiers:"));
        rightColumn.getChildren().add(programStateIdentifiersListView);
        rightColumn.getChildren().add(new Label("Symbol Table:"));
        rightColumn.getChildren().add(symTableView);
        rightColumn.getChildren().add(new Label("Latch table:"));
        rightColumn.getChildren().add(latchTableView);
    }

    private void programStateIdsViewSelectionChangeHandler() {
        programStateIdentifiersListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Integer>() {
                    @Override
                    public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                        if (newValue == null) {
                            return;
                        }

                        heapTableView.setItems(mainWindowController.getHeapTableObservableList(newValue));
                        outListView.setItems(mainWindowController.getOutObservableList(newValue));
                        fileTableView.setItems(mainWindowController.getFileTableObservableList(newValue));
                        symTableView.setItems(mainWindowController.getSymTableObservableList(newValue));
                        exeStackListView.setItems(mainWindowController.getExeStackObservableList(newValue));

                        latchTableView.setItems(mainWindowController.getLatchTableObservableList(newValue));
                    }
                }
        );
    }

    private void runButtonHandler() {
        runOneStepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                heapTableView.refresh();
                outListView.refresh();
                fileTableView.refresh();
                symTableView.refresh();
                exeStackListView.refresh();

                latchTableView.refresh();

                try {
                    mainWindowController.getController().allStepGUI();
                }
                catch (Exception e) {
                    showErrorMessage(e.toString());
                }

                programStateIdentifiersListView.setItems(FXCollections.observableArrayList(
                        mainWindowController.getProgramStateIdentifiersList()
                ));
                programStateIdentifiersListView.getSelectionModel().select(0);

                int value = programStateIdentifiersListView.getSelectionModel().getSelectedItem();

                heapTableView.setItems(mainWindowController.getHeapTableObservableList(value));
                outListView.setItems(mainWindowController.getOutObservableList(value));
                fileTableView.setItems(mainWindowController.getFileTableObservableList(value));
                symTableView.setItems(mainWindowController.getSymTableObservableList(value));
                exeStackListView.setItems(mainWindowController.getExeStackObservableList(value));
                nrOfProgramStatesTextFields.setText(Integer.toString(mainWindowController.getController().getProgramList().size()));

                latchTableView.setItems(mainWindowController.getLatchTableObservableList(value));
            }
        });
    }

    private void showErrorMessage(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("An exception was caught.");
        alert.setContentText(text);

        alert.showAndWait();
    }
}
