package Project2;

public class BriefTest {
	public static void main(String[] args) {
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		for(int i = 0; i < 1000000; i++) {
			System.out.println(i);
			mal.add(i);
		}
	}
}
