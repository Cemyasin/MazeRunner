package entities;
import java.awt.Point;
import java.util.List;

public class Robot {
	public Point point;
	public Robot next;
	public List<Robot> neighbours;
	public int cost;

	public Robot(Point point, Robot next, List<Robot> neighbours, int cost) {
		this.point = point;
		this.next = next;
		this.neighbours = neighbours;
		this.cost = cost;
	}
	
	public Robot() {
		
	}

}

//String url1 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
//String url2 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt";
