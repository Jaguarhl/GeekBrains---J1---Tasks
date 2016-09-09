/*
 * OOP level 2 example
 * @author Dmitry Kartsev
 * @version 08/09/2016
*/

import java.text.*;

// abstarct class Animal (parent for all other classes here)
abstract class Animal {
	int jump_height; // height of animals jump
	double walk_speed; // speed of animals walking
	double run_ratio; // koefficient of walking speedup when run
	String color; // color of animal
	String name; // name of animal to display
	String sound; // sound of an animal
	String sound_extra; // extra sound of an animal

	public Animal(String _color, int _jh, double _ws, double _rr) {
	    color = _color;
	    jump_height = _jh;
		walk_speed = _ws;
		run_ratio = _rr;
		name = "Animal";
		sound = "some noise";
	}
	
    abstract void voice(); // animal sound
	
	// utility for formatting doubles to string output
	static public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
        return output;
	}

	// animal jump
	void jump( int _barrier_height ) {
		if(_barrier_height <= jump_height) System.out.println(color + " " + name.toLowerCase() + " makes a sound: \"" + sound.toLowerCase() + "\" and jumps for a " + ((double)_barrier_height / 10) + " meters height.");
		else System.out.println(color + " " + name.toLowerCase() + " can't jump for a " + ((double)_barrier_height / 10) + " meters height and makes sound \"" + sound_extra + "\"");
	}
	void walk() { // animal walk (calm)
		System.out.println(color + " " + name.toLowerCase() + " walkin around with speed about " + walk_speed + " meters per minute.");
	}
	void run() { // animal run (to or from another animal, for example)
	    System.out.println(color + " " + name.toLowerCase() + " runing with speed about " + customFormat("0.0", (walk_speed * run_ratio)) + " meters per second.");
	}
	abstract void swim(); // animal swim in water
}

// creating child class from Aimal - Horse
class Horse extends Animal {

	public Horse(String _color, int _jh, double _ws, double _rr) {
	    super(_color, _jh, _ws, _rr);
		name = "Horse";
		sound = "Hrrr, tpph, hrrr...";
		sound_extra = "Tprphphph...";
	}
	
	@Override
	void voice() {
		System.out.println(color + " " + name.toLowerCase() + " makes a sound: " + sound.toLowerCase());
	}
	
	@Override
	void swim() {
		System.out.println(color + " " + name.toLowerCase() + " swiming " );
	}
}

// creating child class from Aimal - Dog
class Dog extends Animal {

	public Dog(String _color, int _jh, double _ws, double _rr) {
	    super(_color, _jh, _ws, _rr);
		name = "Dog";
		sound = "Hav-hav, rrrrr...";
		sound_extra = "RRRRR...";
	}
	
	@Override
	void voice() {
		System.out.println(color + " " + name.toLowerCase() + " makes a sound: " + sound.toLowerCase());
	}
	
	@Override
	void swim() {
		System.out.println(color + " " + name.toLowerCase() + " swiming " );
	}
}

// creating child class from Animal - Cat
class Cat extends Animal {

	public Cat(String _color, int _jh, double _ws, double _rr) {
	    super(_color, _jh, _ws, _rr);
		name = "Cat";
		sound = "Meaw, meaw";
		sound_extra = "Wheeeeee";
	}
	
	@Override
	void voice() {
	    System.out.println(color + " " + name.toLowerCase() + " makes a sound: " + sound.toLowerCase());
	}
		
	@Override
	void swim() {
		System.out.println(color + " " + name.toLowerCase() + " do not swim and makes a loud sound: \"" + sound_extra.toLowerCase() + "!!!\"");
	}
}

class MainClass {
	public static void main(String args[]) {
		Cat c1 = new Cat("Red", 20, 5.0, 2.7);
		Dog d1 = new Dog("Grey", 8, 7.4, 1.876);
		Horse h1 = new Horse("Silver", 32, 15.7, 0.7);
		
		// animals are walking calm
		c1.walk();
		d1.walk();
		h1.walk();
		System.out.println();
		System.out.println("And than...");
		// and makes sounds
		c1.voice();
		d1.voice();
		h1.voice();
		
		System.out.println();
		System.out.println("But near this place hunters are shooting ducks. And a loud shoot brings animals to panic.");
		// and makes sounds
		c1.run();
		d1.run();
		h1.run();
		
		System.out.println();
		System.out.println("There are some troubles it meets.");
		System.out.println();
		for(int i = 8; i <= 35; i+=7)
		{
			System.out.println();
			System.out.println("There is barrier " + ((double)i / 10) + " meters height, and animals need to jump over it. Let's go!");
			c1.jump(i);
			d1.jump(i);
			h1.jump(i);
			
			System.out.println();
			System.out.println("But there was an moat behind barrier. And animal is needed to swim. If it can.");
			c1.swim();
			d1.swim();
			h1.swim();
		}
		System.out.println();
		System.out.println("Good news, if you carking about cat - it is alive, and have learned to descend on water )");
	}
}