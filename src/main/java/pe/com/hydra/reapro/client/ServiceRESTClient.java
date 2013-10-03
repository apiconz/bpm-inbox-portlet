package pe.com.hydra.reapro.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bpm.rest.client.BPMClient;
import bpm.rest.client.BPMClientException;
import bpm.rest.client.BPMClientImpl;
import bpm.rest.client.authentication.AuthenticationTokenHandler;
import bpm.rest.client.authentication.AuthenticationTokenHandlerException;
import bpm.rest.client.authentication.was.WASAuthenticationTokenHandler;

public class ServiceRESTClient {

	private JSONObject resultados;
	private JSONObject argumentosDeConsulta;

	private BPMClient client;
	private final String BPD_ID = "25.c904b3b1-afc1-4698-bf5a-a20892c20275";
	private final String PROCESS_APP_ID = "2066.9ab0d0c6-d92c-4355-9ed5-d8a05acdc4b0";

	public ServiceRESTClient(String hostname, int port)
			throws AuthenticationTokenHandlerException, BPMClientException {
		AuthenticationTokenHandler handler = new WASAuthenticationTokenHandler();

		client = new BPMClientImpl(hostname, port, handler);
	}

	public List<Task> obtenerBandejaDeTareas() throws BPMClientException,
			AuthenticationTokenHandlerException, JSONException {
		System.out.println("--> obtenerBandejaDeTareas");
		
		resultados = client.getInbox();
		
		System.out.println("" + resultados.toString());
		
		JSONArray tasks = resultados.getJSONObject("data").getJSONArray("data");
		List<Task> tasksList = new ArrayList<Task>();
		for (int i = 0; i < tasks.length(); i++) {
			JSONObject taskItem = tasks.getJSONObject(i);
			String taskId = taskItem.getString("taskId");
			Task task = new Task();
			task.setName(taskItem.getString("taskActivityName"));
			task.setTaskId(taskId);
			task.setTaskAssignedPerson(taskItem.getJSONObject("taskAssignedTo")
					.getString("who"));
			task.setTaskAssignedPersonRole(taskItem.getJSONObject(
					"taskAssignedTo").getString("type"));
			task.setTaskDueDate(formateDate(taskItem.getString("taskDueDate")));
			task.setTaskReceivedDate(formateDate(taskItem
					.getString("taskReceivedDate")));
			tasksList.add(task);
		}
		System.out.println("taskList items:" + tasksList.size());
		return tasksList;
	}

	private String formateDate(String originalDate) {
		System.out.println("originalDate=" + originalDate);
		SimpleDateFormat formatter, FORMATTER;
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = null;
		try {
			date = formatter.parse(originalDate.substring(0, 20));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		return FORMATTER.format(date);
	}

}
