package logging;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logging {

	public void log(String data) {

		String fileName = "output.txt";

		try {
			FileWriter fileWriter = new FileWriter(fileName,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(data);
			bufferedWriter.newLine();

			bufferedWriter.close();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}

}
