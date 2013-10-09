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
	private String hiringManager;
	private String department;
	private String bpdName;
	
	public String getBpdName() {
		return bpdName;
	}

	public void setBpdName(String bpdName) {
		this.bpdName = bpdName;
	}

	public String getHiringManager() {
		return hiringManager;
	}

	public void setHiringManager(String hiringManager) {
		this.hiringManager = hiringManager;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

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
