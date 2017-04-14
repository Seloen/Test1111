package MyGame;

public class Ships {
	private int hitNumber;
	private int[] location={1,2,3};
	
	public Ships() {
		///////////////////////////////////
		super();
		//this.location = location;
		this.hitNumber=0;
	}
	
	public String checkYourSelf(int guess){
		String text = null;
		for(int i: location){
			if(guess>7 || guess<1){
				text = "please guess between 1 and 7";
				break;
			}
			
			if(guess==i){
				hitNumber++;
				if(hitNumber==3){
					text = "kill";
					break;
				}else{
					text = "hit";
					break;
				}
			}
			
			if(i!=guess){
				text = "miss";
			}
		}
		return text;
		
		
	}

}
