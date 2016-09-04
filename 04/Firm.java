/* 
 * Console persons class
 * @author Dmitry Kartsev
 * @version 01/09/2016
*/

class Firm {
	
	final static int ARRAY_SIZE = 5; // сколько в нашем массиве будет записей?
	final static int AGE_FROM = 40; // старше какого возраста (включительно) должен быть сотрудник, чтобы его запись вывести на экран?
	
	public static void main(String args[]) {
		Person[] persArray = new Person[ARRAY_SIZE]; // Сначала объявляем массив объектов
		
		persArray[0] = new Person("Иван", "Иванов", true, "инженер", "ivivan@mailbox.com", "+79237451818", 30000, 30);
		persArray[1] = new Person("Пётр", "Сидоров", true, "менеджер", "p.sidorov@mailbox.com", "+79233215689", 45000, 33);
		persArray[2] = new Person(); // создадим "пустую" запись
		persArray[3] = new Person("Татьяна", "Дапкунайте", false, "бухгалтер", "t.dapkunaite@mailbox.com", "+79231064514", 65000, 53);
		persArray[4] = new Person("Инна", "Паркати", false, "менеджер", "i.parkati@mailbox.com", "+79238459973", 45000, 43);
		
		// выведем на экран список всех персонажей, чей возраст больше AGE_FROM	
		System.out.println("СПИСОК СОТРУДНИКОВ, ЧЕЙ ВОЗРАСТ БОЛЕЕ " + (AGE_FROM - 1) + " ЛЕТ (ГОДА):");
		
		for(int i = 0; i < persArray.length; i++) {
			if(persArray[i].getAge() >= AGE_FROM) persArray[i].printPersonInfo();
		}
	}	
}

class Person {
	private String firstName; // имя
	private String lastName; // фамилия
	private boolean male; // пол
	private String position; // должность
	private String email; // электронный адрес почты
	private String phone; // контактный номер телефона
	private int payment; // оклад (в мес.)
	private int age; // возраст
	
	// создаём объект, если нам известны все параметры	
	Person(String firstName, String lastName, boolean sex, String position, String email, String phone, int payment, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.male = sex;
		this.position = position;
		this.email = email;
		this.phone = phone;
		this.payment = payment;
		this.age = age;
	}	
	
	// создаём "пустой" объект	
	Person() {
		this.firstName = "Неизвестно";
		this.lastName = "";
		this.male = false;
		this.position = "нет должности";
		this.email = "no@mail.com";
		this.phone = "";
		this.payment = 0;
		this.age = 0;
	}
	
	// отдадим возраст персонажа	
	int getAge() {
		return age;
	}
	
	// поскольку пол мы храним в переменной типа boolean, а выводить хотим в строке, то сделаем метод, который будет возвращать строку в зависимости
	// от значения полученной переменной	
	String getMale(boolean sex){
		if(sex)
			return "Мужчина";
		else
			return "Женщина";
	}
	
	// в этом методе выводим все данные о работнике	
	void printPersonInfo() {
		System.out.println(firstName + " " + lastName + "\n\nДолжность: " + position + "\n\nЗпл: " + " " + payment + " руб. / мес.\n\n" + getMale(male) + ", " + age + " года (лет)" + "\n\nКонтактная информация:\n" + phone + ", " + email + "\n--------------\n\n");
	}
}