package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainWindow extends Application {
    public static void main(String args[]) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        GridPane root1 = new GridPane();
        root.setStyle("-fx-background-color:rgb(159, 174, 195)");

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Label labelTitle = new Label("Журнал задач");
        root.add(labelTitle, 0, 0);

        Button exit = new Button("Выйти");
        root.add(exit, 4, 7);

        Button change = new Button("Изменить");
        root.add(change, 2, 7);

        Button delTask = new Button("Удалить задачу");
        root.add(delTask, 1, 7);

        Button addTask = new Button("Добавить задачу");
        root.add(addTask, 0, 7);

        Button fileButton = new Button("File");
        root.add(fileButton, 0, 1);

        Label checkBoxes = new Label("№");
        root.add(checkBoxes, 0, 2);

        Label nameTask = new Label("Наименование");
        root.add(nameTask, 1, 2);

        Label descriptionLabel = new Label("Описание");
        root.add(descriptionLabel, 2, 2);

        Label dateLabel = new Label("Дата");
        root.add(dateLabel, 3, 2);

        CheckBox checkBox1 = new CheckBox();
        root.add(checkBox1, 0, 3);

        CheckBox checkBox2 = new CheckBox();
        root.add(checkBox2, 0, 4);

        CheckBox checkBox3 = new CheckBox();
        root.add(checkBox3, 0, 5);

        Label name1 = new Label("Позавтракать");
        root.add(name1, 1, 3);

        Label name2 = new Label("Погулять с собакой");
        root.add(name2, 1, 4);

        Label name3 = new Label("Пообедать");
        root.add(name3, 1, 5);

        Label desc1 = new Label("Съесть омлет и выпить кофе");
        root.add(desc1, 2, 3);

        Label desc2 = new Label("Прогулка с собакой по парку");
        root.add(desc2, 2, 4);

        Label desc3 = new Label("Съесть борщ");
        root.add(desc3, 2, 5);

        Label date1 = new Label("09.01.2007 9:41");
        root.add(date1, 3, 3);

        Label date2 = new Label("09.01.2007 9:41");
        root.add(date2, 3, 4);

        Label date3 = new Label("09.01.2007 9:41");
        root.add(date3, 3, 5);

        Scene scene = new Scene(root);
        stage.setTitle("TASK MANAGER");
        stage.setScene(scene);
        stage.show();
    }
}

