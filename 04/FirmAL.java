/* 
 * Console persons class with ArrayList implementation
 * @author Dmitry Kartsev
 * @version 05/09/2016
*/

import java.util.ArrayList;

class FirmAL {
	
	final static int ARRAY_SIZE = 5; // ������� � ����� ������� ����� �������?
	final static int AGE_FROM = 40; // ������ ������ �������� (������������) ������ ���� ���������, ����� ��� ������ ������� �� �����?
	
	public static void main(String args[]) {
		ArrayList<Person> persons = new ArrayList<Person>(); // ������ ������ �������� ArrayList
		
		Person person = new Person("����", "������", true, "�������", "ivivan@mailbox.com", "+79237451818", 30000, 30);
		persons.add(person);
		person = new Person("ϸ��", "�������", true, "��������", "p.sidorov@mailbox.com", "+79233215689", 45000, 40);
		persons.add(person);
		person = new Person(); // �������� "������" ������
		persons.add(person);
		person = new Person("�������", "����������", false, "���������", "t.dapkunaite@mailbox.com", "+79231064514", 65000, 53);
		persons.add(person);
		person = new Person("����", "�������", false, "��������", "i.parkati@mailbox.com", "+79238459973", 45000, 43);
		persons.add(person);
		
		// ������� �� ����� ������ ���� ����������, ��� ������� ������ AGE_FROM	
		System.out.println("������ �����������, ��� ������� ����� " + (AGE_FROM - 1) + " ��� (����):");
		
		for(int i = 0; i < persons.size(); i++) {
			if(persons.get(i).getAge() >= AGE_FROM) persons.get(i).printPersonInfo();
		}
	}	
}

class Person {
	private String firstName; // ���
	private String lastName; // �������
	private boolean male; // ���
	private String position; // ���������
	private String email; // ����������� ����� �����
	private String phone; // ���������� ����� ��������
	private int payment; // ����� (� ���.)
	private int age; // �������
	
	// ������ ������, ���� ��� �������� ��� ���������	
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
	
	// ������ "������" ������	
	Person() {
		this.firstName = "����������";
		this.lastName = "";
		this.male = false;
		this.position = "��� ���������";
		this.email = "no@mail.com";
		this.phone = "";
		this.payment = 0;
		this.age = 0;
	}
	
	// ������� ������� ���������	
	int getAge() {
		return age;
	}
	
	// ��������� ��� �� ������ � ���������� ���� boolean, � �������� ����� � ������, �� ������� �����, ������� ����� ���������� ������ � �����������
	// �� �������� ���������� ����������	
	String getMale(boolean sex){
		if(sex)
			return "�������";
		else
			return "�������";
	}
	
	// � ���� ������ ������� ��� ������ � ���������	
	void printPersonInfo() {
		System.out.println(firstName + " " + lastName + "\n\n���������: " + position + "\n\n���: " + " " + payment + " ���. / ���.\n\n" + getMale(male) + ", " + age + " ���� (���)" + "\n\n���������� ����������:\n" + phone + ", " + email + "\n--------------\n\n");
	}
}