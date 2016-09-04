/* 
 * Console persons class
 * @author Dmitry Kartsev
 * @version 01/09/2016
*/

class Firm {
	
	final static int ARRAY_SIZE = 5; // ������� � ����� ������� ����� �������?
	final static int AGE_FROM = 40; // ������ ������ �������� (������������) ������ ���� ���������, ����� ��� ������ ������� �� �����?
	
	public static void main(String args[]) {
		Person[] persArray = new Person[ARRAY_SIZE]; // ������� ��������� ������ ��������
		
		persArray[0] = new Person("����", "������", true, "�������", "ivivan@mailbox.com", "+79237451818", 30000, 30);
		persArray[1] = new Person("ϸ��", "�������", true, "��������", "p.sidorov@mailbox.com", "+79233215689", 45000, 33);
		persArray[2] = new Person(); // �������� "������" ������
		persArray[3] = new Person("�������", "����������", false, "���������", "t.dapkunaite@mailbox.com", "+79231064514", 65000, 53);
		persArray[4] = new Person("����", "�������", false, "��������", "i.parkati@mailbox.com", "+79238459973", 45000, 43);
		
		// ������� �� ����� ������ ���� ����������, ��� ������� ������ AGE_FROM	
		System.out.println("������ �����������, ��� ������� ����� " + (AGE_FROM - 1) + " ��� (����):");
		
		for(int i = 0; i < persArray.length; i++) {
			if(persArray[i].getAge() >= AGE_FROM) persArray[i].printPersonInfo();
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