import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
class Contact
{
	private String name;
	private int age;
	private String phoneNum;
	public Contact(String name, int age, String phoneNum)
	{
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
	}
	public Contact(String name, String phoneNum)
	{
		this(name, 0, phoneNum);
	}
	public Contact(String phoneNum)
	{
		this("unknown", 0, phoneNum);
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	public String getPhoneNum()
	{
		return phoneNum;
	}
}
public class ContactManager {
	private LinkedList<Contact> phoneBook;
	private Scanner scanner;
	public ContactManager() 
	{
		phoneBook = new LinkedList<Contact>();
		scanner = new Scanner(System.in);
	}
	
	public void showMenu() {
		
	}
	
	public void showList() {
		
	}
	
	public void regist() {
		
		String name, phoneNum = null;
		int age;
		boolean isDuplicate = true;
		System.out.print("이름: ");
		name = scanner.next();
		System.out.print("나이: ");
		age = scanner.nextInt();
		// clearBuffer
		scanner.nextLine();
		while(isDuplicate)
		{
			System.out.print("전화번호: ");
			phoneNum = scanner.nextLine();
			isDuplicate = false;
			for(Contact data : phoneBook)
			{
				if(data.getPhoneNum().equals(phoneNum))
				{
					isDuplicate = true;
					System.out.println("전화번호가 다른 연락처와 중복되었습니다. ");
					break;
				}
			}
		}
		phoneBook.add(new Contact(name, age, phoneNum));
		//System.out.println(phoneBook.get(0).getName() + phoneBook.get(0).getAge() + phoneBook.get(0).getPhoneNum());
	}

	public void delete() {
		
	}
	
	public void edit() {
		
	}

	public void search() {
		
	}
	
	public static void main(String[] args)
	{
		ContactManager CM = new ContactManager();
		boolean done = false;
		boolean rightInput = false;
		int act = 0;
		
		while(!done)
		{
			CM.showMenu();
			while(!rightInput)
			{
				try {
					act = CM.scanner.nextInt();
					if(act<1 || act>6) throw new InputMismatchException();
					rightInput = true;
				} catch(InputMismatchException e) {
					System.out.println("1~6 사이 정수만 입력해주세요.");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			switch(act) {
				case 1:
					CM.showList();
					break;
				case 2:
					CM.regist();
					break;
				case 3:
					CM.delete();
					break;
				case 4:
					CM.edit();
					break;
				case 5:
					CM.search();
					break;
				case 6:
					done = true;
					break;
			}
			rightInput = false;
			act = 0;
		}
		CM.scanner.close();
	}
	
}
