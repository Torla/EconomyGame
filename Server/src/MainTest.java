import Connection.ClientConnectionsRoutine;

public class MainTest {
	public static void main(String[] args) {
		ClientConnectionsRoutine clientConnectionsRoutine = new ClientConnectionsRoutine();
		new Thread(clientConnectionsRoutine).start();
	}
}
