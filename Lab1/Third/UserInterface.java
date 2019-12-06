import java.lang.reflect.Method;
import java.util.Scanner;

public class UserInterface {
  private static Scanner sc = new Scanner(System.in);
  private Library lib = new Library();

  public Method startMenu() {
    System.out.println("\n------------------\nWelcome to the library.");
    System.out.println("1 - Show all books\n2 - Find books by...\n3 - Add book\n4 - Edit book\n5 - Remove book\n6 - Exit\n------------------\n");
    int reply = sc.nextInt();
    switch (reply) {
      case 1: 
        printBookList();
        break;
      case 2: 
        findBook();
        break;
      case 3:
        addBook();
        break;
      case 4:
        editBook();
        break;
      case 5: 
        removeBook();
        break;
      case 6:
        System.exit(0);
        break;
      default:
        System.err.println("wrong choice");
    }

    return startMenu();
  }

  private Method removeBook() {
    System.out.println("Enter id of the book: ");
    int id = sc.nextInt();
    if (lib.exists(id)) {
      System.out.print("Are you sure? Book : ");
      printBook(lib.findBook(1, Integer.toString(id)));
      System.out.print(" will be deleted [y/n] ? ");
      String reply = sc.next();
      System.out.println(reply);
      if (reply.equals("y") || reply.equals("yes")) {
        lib.removeBook(id);
      }
    } else {
      System.out.println("No such book found");
    }

    return startMenu();
  }

  private Method editBook() {
    System.out.println("Enter id of the book: ");
    int id = sc.nextInt();
    if (lib.exists(id)) {
      System.out.println("What do you want to change?\n1 - id\n2 - Author\n3 - Name\n4 - Year");
      int key = sc.nextInt();
      System.out.println("New value?");
      String value = sc.next();
      lib.editBook(id, key, value);
    } else {
      System.out.println("No such book found");
    }
    return startMenu();
  }

  private Method addBook() {
    System.out.println("id?");
    int id = sc.nextInt();
    if (!lib.exists(id)) {
      System.out.println("Author?");
      String author = sc.next();
      System.out.println("Name?");
      String name = sc.next();
      System.out.println("Year?");
      int year = sc.nextInt();
      lib.addBook(id, author, name, year);
    } else {
      System.out.println("Book with this id already exists!");
    }

    return startMenu();
  }

  public Method findBook() {
    System.out.println("Enter key for find:\n1 - id\n2 - Author\n3 - Name\n4 - Year");
    int key = sc.nextInt();
    System.out.println("Enter the value");
    String value = sc.next();
    printBook(lib.findBook(key, value));
    
    return startMenu();
  }

  	public void printBookList() {
		for (Book x : lib.bookList) {
			printBook(x);
		}
	}

  public void printBook(Book x) {
		System.out.println(x.getId() + " " + x.getAuthor() + " " + x.getName() + " " + x.getYear());
	}
}
