package pe.com.hydra.reapro.client;

import java.io.Serializable;

public class Task implements Serializable {

	private String name;
	private String taskId;
	private String taskAssignedPerson;
	private String taskAssignedPersonRole;
	private String taskDueDate;
	private String instanceId;
	private String taskActivityName;
	private String taskReceivedDate;
	private String taskPriority;
	private String taskStatus;
	
	public String getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getTaskActivityName() {
		return taskActivityName;
	}

	public void setTaskActivityName(String taskActivityName) {
		this.taskActivityName = taskActivityName;
	}

	public String getTaskReceivedDate() {
		return taskReceivedDate;
	}

	public void setTaskReceivedDate(String taskReceivedDate) {
		this.taskReceivedDate = taskReceivedDate;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

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
