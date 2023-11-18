package application;

import java.io.Serializable;

//Task.java
public class Task implements Serializable {
	private String name;
	private String time;
	private boolean completed;

	public Task(String name, String time) {
		this.name = name;
		this.completed = false;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public String setTime(String timeStamp) {
		return time;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Task Name='" + name + '\'' + ", Completed=" + completed + ", CreatedAt='" + time + '\'' + '}';
	}
}
