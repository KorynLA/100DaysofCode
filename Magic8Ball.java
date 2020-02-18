import java.util.*;

class Magic8Ball {
	HashMap<Integer, String> answer;
	Magic8Ball() {
		answer = new HashMap<Integer, String>();
		answer.put(1, "No");
		answer.put(2, "Ask again later");
		answer.put(3, "Yes");
		answer.put(4, "Maybe");

	}
	public static void main(String[] args) {
		Magic8Ball ball = new Magic8Ball();
		Random randAnswer = new Random();
		Scanner input= new Scanner(System.in);
		System.out.println("What is your question?");
		String userAnswer= input.next();

		int index=randAnswer.nextInt(5)+1;
		System.out.println(userAnswer+ " " +ball.answer.get(index));
	}
}