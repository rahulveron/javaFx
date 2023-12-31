package application;

//TaskManager.java
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private ArrayList<Task> taskList;

	public TaskManager() {
		this.taskList = new ArrayList<>();
	}

	public void addTask(Task task) {
		taskList.add(task);
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = (ArrayList<Task>) taskList;
	}
}
