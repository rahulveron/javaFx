package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

public class SampleController implements Initializable {

	@FXML
	private ListView<Task> taskListView;

	private TaskManager taskManager;

	//Task file name(we can also give the path before the task name, to store the file on some specific location)
	private static final String TASKS_FILE = "tasks.dat";

	public SampleController() {
		this.taskManager = new TaskManager();
	}

	@FXML
	private void addTask() {
		System.out.println("Add Task button clicked");
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add New Task");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter task name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String taskName = result.get();

			// Create a new task with the provided name and description
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			Task newTask = new Task(taskName, timeStamp);
			newTask.setTime(timeStamp);
			taskManager.addTask(newTask);
			taskListView.getItems().add(newTask);

			saveTasks();
		}
	}

	@FXML
	private void markAsCompleted() {
		System.out.println("Mark as Completed button clicked");
		// Implement logic to mark the selected task as completed
		Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			selectedTask.setCompleted(true);
			// Update the task list view
			taskListView.refresh();
			saveTasks();
		}
	}

	//To save task in a file
	private void saveTasks() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
			oos.writeObject(taskManager.getTaskList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Method to load task from the save file
	private void loadTasks() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASKS_FILE))) {
			List<Task> loadedTasks = (List<Task>) ois.readObject();
			taskManager.setTaskList(new ArrayList<>(loadedTasks));
		} catch (IOException | ClassNotFoundException e) {
			// Handle exceptions (e.g., file not found)
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Controller initialized.");
		System.out.println("Task List View items: " + taskListView.getItems());
		loadTasks();
		ObservableList<Task> observableTaskList = FXCollections.observableArrayList(taskManager.getTaskList());
		taskListView.setItems(observableTaskList);
	}
}
