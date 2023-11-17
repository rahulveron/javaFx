package application;

import java.net.URL;
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

	public SampleController() {
     this.taskManager = new TaskManager();
 }

	@FXML
	private void addTask() {
		 System.out.println("Add Task button clicked");
//		// Implement logic to add a new task to the list
//		// For simplicity, let's assume the task name is provided externally
//		Task newTask = new Task("Sample Task");
//		taskManager.addTask(newTask);
//		taskListView.getItems().add(newTask);
		 TextInputDialog dialog = new TextInputDialog();
	        dialog.setTitle("Add New Task");
	        dialog.setHeaderText(null);
	        dialog.setContentText("Enter task name:");

	        Optional<String> result = dialog.showAndWait();
	        if (result.isPresent()) {
	            String taskName = result.get();

	            // For simplicity, you can also add a description input if needed
	             TextInputDialog descriptionDialog = new TextInputDialog();
	             descriptionDialog.setTitle("Add New Task");
	             descriptionDialog.setHeaderText(null);
	             descriptionDialog.setContentText("Enter task description:");
	             Optional<String> descriptionResult = descriptionDialog.showAndWait();
	             String taskDescription = descriptionResult.orElse("");

	            // Create a new task with the provided name and description
	            Task newTask = new Task(taskName);
	            taskManager.addTask(newTask);
	            taskListView.getItems().add(newTask);
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
		}
	}

	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	    System.out.println("Controller initialized.");
	    System.out.println("Task List View items: " + taskListView.getItems());
	    ObservableList<Task> observableTaskList = FXCollections.observableArrayList(taskManager.getTaskList());
        taskListView.setItems(observableTaskList);
	}
}
