package GUI;

import Controller.Controller;
import Model.Statement.*;
import Repository.IRepository;
import Repository.Repository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladc
 */
public class StartWindow extends Application {
    private List<IStatement> examplesList;
    private ListView<IStatement> examplesListView;

    private static final String logFilePath = "res\\log.txt";

    private IRepository repo;
    private Controller ctrl;
    private MainWindowController mainWindowController;
    private MainWindow mainWindow;
    private Stage mainStage;

    private VBox root;
    private Scene scene;
    private Button selectButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.mainStage = new Stage();

        this.examplesList = new ArrayList<>();
        this.examplesList = ExamplesFactory.getAll();

        repo = new Repository(logFilePath);
        ctrl = new Controller(repo);
        mainWindowController = new MainWindowController(ctrl);
        mainWindow = new MainWindow(mainWindowController);

        initGUI(stage);

        stage.show();
    }

    private void initGUI(Stage stage) {
        root = new VBox(20);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.TOP_CENTER);

        stage.setTitle("Toy Language Interpreter | JavaFX | Start Window");
        scene = new Scene(root, 540, 540);
        stage.setScene(scene);

        selectButton = new Button("Select");
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = examplesListView.getFocusModel().getFocusedIndex();

                if (index < 0) {
                    return ;
                }

                IStatement st = examplesList.get(index);
                ctrl.add(st);

                mainWindow.start(mainStage);
            }
        });

        examplesListView = new ListView<>(FXCollections.observableArrayList(examplesList));
        examplesListView.getSelectionModel().select(0);
        examplesListView.setCellFactory(new Callback<ListView<IStatement>, ListCell<IStatement>>() {
            @Override
            public ListCell<IStatement> call(ListView<IStatement> param) {
                return new ListCell<IStatement>() {
                    @Override
                    protected void updateItem(IStatement st, boolean empty) {
                        super.updateItem(st, empty);
                        if (st != null) {
                            setText(st.toString());
                        }
                    }
                };
            }
        });

        root.getChildren().add(new Label("Welcome to Toy Language Interpreter | Java FX"));
        root.getChildren().add(new Label("In order to continue, please select your example program from the list below:"));
        root.getChildren().add(examplesListView);
        root.getChildren().add(selectButton);
        root.getChildren().add(new Label("Copyright (c) Vlad Cîncean, group 923"));
    }
}
