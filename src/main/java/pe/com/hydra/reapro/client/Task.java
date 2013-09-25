package pe.com.hydra.reapro.client;

import java.io.Serializable;

public class Task implements Serializable {

	private String name;
	private String taskId;
	private String taskAssignedPerson;
	private String taskAssignedPersonRole;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskAssignedPerson() {
		return taskAssignedPerson;
	}

	public void setTaskAssignedPerson(String taskAssignedPerson) {
		this.taskAssignedPerson = taskAssignedPerson;
	}

	public String getTaskAssignedPersonRole() {
		return taskAssignedPersonRole;
	}

	public void setTaskAssignedPersonRole(String taskAssignedPersonRole) {
		this.taskAssignedPersonRole = taskAssignedPersonRole;
	}

}
