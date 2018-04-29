import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;
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
	boolean sortMode = false;
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
		System.out.println("6. 추가기능");
		System.out.println("7. 끝내기");
			
	}
	
	public void showAddiMenu() {
		System.out.println("############ 추가 기능 ############");
		if(sortMode)
			System.out.println("1. 가나다순 정렬 해제");
		else
			System.out.println("1. 가나다순 정렬 설정");
		System.out.println("2. 즐겨찾기");
	}
	
	public void showList() {
		int i = 1;
		if(sortMode) {	
			TreeMap<String, Contact> dataList = new TreeMap<String, Contact>();
			for(Contact data : phoneBook)
			{
				dataList.put(data.getName()+(i++), data);
			}
			i = 1;
			for(String name : dataList.keySet())
			{
				System.out.println(i++ +"."+ dataList.get(name));
			}
		} else {
			for(Contact data:phoneBook) {
				System.out.println(i++ +"."+data);
			}
		}
	}
	
	public void regist() {
		
		String name = null, phoneNum = null;
		int age = 0;
		boolean isDuplicate = false;
		boolean rightInput = false;
		
		System.out.print("이름: ");
		name = scanner.nextLine();
		do
		{
			try {
				System.out.print("나이: ");
				age = scanner.nextInt();
				if(age<0)
					throw new Exception("나이는 음수가 될 수 없습니다.");
				rightInput = true;
			} catch (InputMismatchException ie) {
				System.out.println("나이는 정수만 입력해 주세요.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if(scanner.hasNextLine())
					scanner.nextLine(); // clear buffer
			}
		} while(!rightInput);
		
		do {
			try {
				System.out.print("전화번호: ");
				phoneNum = scanner.nextLine();
				String checkRegex = "^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$";
				if(Pattern.matches(checkRegex, phoneNum) == false)  
					throw new Exception("비정상입력");
				rightInput = true;
				for(Contact data : phoneBook)
					if(data.getPhoneNum().equals(phoneNum))
						throw new Exception("전화번호가 다른 연락처와 중복되었습니다. ");
				isDuplicate = false;
			} catch(Exception e) {
				if(e.getMessage().equals("비정상입력"))
					rightInput = false;
				else
					isDuplicate = true;
				System.out.println(e.getMessage());
			}
		} while(isDuplicate || !(rightInput));
		
		phoneBook.add(new Contact(name, age, phoneNum));
	}

	public void delete() 
	{
			int index = 0;
			System.out.print("삭제할 행번호: ");
			try {
				index = scanner.nextInt()-1;
				if( index > phoneBook.size() || index < 0 )
					throw new Exception("삭제할 행 번호가 없습니다.");
			} catch(InputMismatchException ie) {
				System.out.println("정수만 입력해주세요.");
			} catch(Exception e) {
				e.getMessage();
			} finally {
				if(scanner.hasNextLine())
					scanner.nextLine();
			}
			System.out.println( "["+ phoneBook.get(index)+"]"+"가 삭제 되었습니다.");
			phoneBook.remove(index);
	}
	
	public void edit() { }

	public void search() {
		String keyword = null;
		boolean rightInput = true;
		boolean hasResult = false;
		int i = 1;
		do {
			
			System.out.print("검색어 : ");
			try {
				keyword =  scanner.nextLine();
				// 의미가 있을까?
				if(!Pattern.matches("[0-9]+$", keyword))
					throw new Exception("검색어는 숫자만 사용가능합니다.");
				rightInput = true;
			} catch(Exception e) {
				System.out.println(e.getMessage());
				rightInput = false;
			}
			
		} while(!rightInput);
		
		for(Contact data : phoneBook)
		{
			if(data.getPhoneNum().contains(keyword))
			{				
				hasResult = true;
				System.out.println(i +". "+data);
			}			
			i++;
		}
		if(!hasResult) System.out.println("No results found");
	}
	
	public void sortOnOff() {
		sortMode = !sortMode;
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
				System.out.print("선택 : ");
				try {
					act = CM.scanner.nextInt();
					if(act<1 || act>7) throw new InputMismatchException();
					rightInput = true;
				} catch(InputMismatchException e) {
					System.out.println("1~6 사이 정수만 입력해주세요.");
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(CM.scanner.hasNextLine())
						CM.scanner.nextLine();
				}
			}
			System.out.println();
			switch(act) {
				case 1:
					if(CM.phoneBook.size() != 0)
						CM.showList();
					else
						System.out.println("연락처에 출력할 데이터가 없습니다.");
					break;
				case 2:
					CM.regist();
					break;
				case 3:
					if(CM.phoneBook.size() != 0)
						CM.delete();
					else
						System.out.println("연락처에 삭제할 데이터가 없습니다.");
					break;
				case 4:
					if(CM.phoneBook.size() != 0)
						CM.edit();
					else
						System.out.println("연락처에 수정할 데이터가 없습니다.");
					break;
				case 5:
					if(CM.phoneBook.size() != 0)
						CM.search();
					else
						System.out.println("연락처에 검색할 데이터가 없습니다.");
					break;
				case 6:
					rightInput = false;
					act = 0;
					CM.showAddiMenu();
					while(!rightInput)
					{
						System.out.print("선택 : ");
						try {
							act = CM.scanner.nextInt();
							if(act<1 || act>2) throw new InputMismatchException();
							rightInput = true;
						} catch(InputMismatchException e) {
							System.out.println("1~2 사이 정수만 입력해주세요.");
						} catch(Exception e) {
							e.printStackTrace();
						} finally {
							if(CM.scanner.hasNextLine())
								CM.scanner.nextLine();
						}
					}
					switch(act)
					{
						case 1:
							CM.sortOnOff();	
							break;
						case 2:
//							CM.favorite();
					}
					break;
				case 7:
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
