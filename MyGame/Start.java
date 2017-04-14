package MyGame;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int guess = 0;
		Ships ship = new Ships();
		String res = ship.checkYourSelf(guess);
		System.out.println(res);

	}

}
