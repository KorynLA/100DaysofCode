/***
A single household with pets. Have their names, types and the amount printed to the screen. 
***/
import java.util.*;
/***
UserPets is only accessible in this program
***/
class UserPets {
	/***
	Used to see amount of pets in the house. 
	***/
	static int petAmnt=0;
	String petType;
	String petName;
	UserPets(String petType, String petName){
		this.petType=petType;
		this.petName=petName;
		petAmnt++;
	}
	public void sayName() {
		System.out.println("My name is "+ petName + ", and I am a " + petType+".");
	}
	public int amntPets() {
		return petAmnt;
	}
}

public class Pets {
	public static void main(String[] args) {
		UserPets dog1 = new UserPets("Dog", "Jared");
		UserPets dog2 = new UserPets("Dog", "Spot");
		UserPets cat = new UserPets("Cat", "Blackie");
		cat.sayName();
		System.out.println("There are "+dog2.amntPets()+" pets in the house.");
	}
}