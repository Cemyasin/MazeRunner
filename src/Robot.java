import java.awt.Point;
import java.util.List;

public class Robot {
	Point point;
	Robot next;
	List<Robot> neighbours;
	int cost;

	public Robot(Point point, Robot next, List<Robot> neighbours, int cost) {
		this.point = point;
		this.next = next;
		this.neighbours = neighbours;
		this.cost = cost;
	}
	
	public Robot() {
		
	}

}


//for (Robot robo : neighbours) {
//	if (robot.parent.cost > robo.cost) {
//		robot.parent = robo;
//	}
//}
//robot=new Robot(current, robot.parent, neighbours, cost);



//
//String uurl = textField.getText();
//String url1 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
//String url2 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt";
//URL url;
//char[][] input = new char[20][20];
//try {
//	url = new URL(uurl);
//	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//
//	String inputLine;
//
//	int i = 0, j = 0, k = 0;
//	boolean flag = true;
//	while ((inputLine = in.readLine()) != null) {
//		if (flag) {
//			input = new char[inputLine.length()][inputLine.length()];
//			matrix = new int[input.length + 2][input.length + 2];
//			flag = false;
//		}
//		input[i] = inputLine.toCharArray();
//
//		//System.out.println(input[i]);
//		i++;
//	}
//
//	in.close();
//} catch (MalformedURLException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//} catch (IOException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}
//for (int i = 0; i < input.length + 2; i++) {//y eks
//	for (int j = 0; j < input[0].length + 2; j++) {//x eks
//		if (i == 0 || j == 0 || i == input.length + 1 || j == input.length + 1) {
//			matrix[i][j] = 1;
//			continue;
//		}
//		
//		if (i < input.length + 1 && j < input.length + 2)
//			matrix[i][j] = Character.getNumericValue(input[i - 1][j - 1]);
//	}
//
//}