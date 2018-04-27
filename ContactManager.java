import java.util.ArrayList;
import java.util.InputMismatchException;
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
	public String getName() { return name; }
	public int getAge() { return age; }
	public String getPhoneNum() { return phoneNum; }
	public void edit(String name, int age, String phoneNum) {
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
	}
	public void edit(String name, String phoneNum) {
		this.name = name;
		this.phoneNum = phoneNum;
	}
	public void edit(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String toString()
	{
		return name+" "+age+" "+phoneNum;
	}
}
public class ContactManager {
	private ArrayList<Contact> phoneBook;
	private Scanner scanner;
	public ContactManager() 
	{
		phoneBook = new ArrayList<Contact>();
		scanner = new Scanner(System.in);
	}
	
	public void showMenu() {

			System.out.println("############ 연락처 관리 ############");
			System.out.println("1. 연락처 출력");
			System.out.println("2. 연락처 등록");
			System.out.println("3. 연락처 삭제");
			System.out.println("4. 연락처 수정");
			System.out.println("5. 연락처 검색");
			System.out.println("6. 출력 설정");
			System.out.println("7. 끝내기");
			System.out.print("선택 : ");

	}
	
	public void showList() {
		int i = 1;
		for(Contact data:phoneBook) {
			System.out.println(i++ +"."+data);
		}
	}
	
	public void regist() {
		
		String name, phoneNum = null;
		int age;
		boolean isDuplicate = false;
		
		System.out.print("이름: ");
		name = scanner.nextLine();
		System.out.print("나이: ");
		age = scanner.nextInt();
		scanner.nextLine(); // clear \n
		
		do {
			System.out.print("전화번호: ");
			try {
				phoneNum = scanner.nextLine();
				for(Contact data : phoneBook)
					if(data.getPhoneNum().equals(phoneNum))
						throw new Exception("전화번호가 다른 연락처와 중복되었습니다. ");
				isDuplicate = false;
			} catch(Exception e) {
				System.out.println(e.getMessage());
				isDuplicate = true;
			}
		} while(isDuplicate);
		phoneBook.add(new Contact(name, age, phoneNum));
	}

	public void delete() 
	{
			int index = 0;
			System.out.print("삭제할 행번호: ");
			try {
				index = scanner.nextInt()-1;
				scanner.nextLine(); // clear \n
				if( index > phoneBook.size() || index < 0 )
					throw new Exception("삭제할 행 번호가 없습니다.");
			} catch(InputMismatchException ie) {
				System.out.println("정수만 입력해주세요.");
			} catch(Exception e) {
				e.getMessage();
			}
			System.out.println( "["+ phoneBook.get(index)+"]"+"가 삭제 되었습니다.");
			phoneBook.remove(index);
	}
	
	public void edit() {

		boolean rightInput = false;
		int index = 0;
		String newNum = null;
		
		while(!rightInput)
		{
			System.out.print("수정할 행번호 : ");
			index = scanner.nextInt() - 1;
			scanner.nextLine(); // clear \n
			
			try {
				System.out.println("수정할 번호를 입력하시오");
				newNum = scanner.nextLine();
				
				rightInput = true;
			}
			catch(InputMismatchException e) {
				System.out.println("번호를 다시 입력해주세요");
			}
		}
		phoneBook.get(index).edit(newNum);
		System.out.println("수정이 완료되었습니다.");
	}

	public void search() {
		String keyword;
		int i = 1;
		System.out.print("검색어 : ");
		keyword =  scanner.nextLine();
		
		for(Contact data : phoneBook)
		{ 
			if(data.getPhoneNum().contains(keyword))
				System.out.println(i+". "+data);
			i++;
		}
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
					CM.scanner.nextLine(); // clear \n
					if(act<1 || act>6) throw new InputMismatchException();
					rightInput = true;
				} catch(InputMismatchException e) {
					System.out.println("1~6 사이 정수만 입력해주세요.");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println();
			switch(act) {
				case 1:
					if(CM.phoneBook.size() != 0)
						CM.showList();
					else
						System.out.println("출력할게 없다는 메시지");
					break;
				case 2:
					CM.regist();
					break;
				case 3:
					if(CM.phoneBook.size() != 0)
						CM.delete();
					else
						System.out.println("삭제할게 없다는 메시지");
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
			System.out.print("\n\n");
		}
		CM.scanner.close();
	}
	
}
