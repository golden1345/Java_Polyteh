import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Library implements AutoCloseable {
	List<Book> bookList = new ArrayList<>();
	File file = new File("rsc/booksfile");
	
	Library() {
		readBookList();
	}

	public Boolean exists(int id) {
		Boolean result = false;
		for (Book x : bookList) {
			if (x.getId() == id) {
				result = true;
				break;
			}
		}
		return result;
	}

	private void readBookList() {
		file.getParentFile().mkdir();
		try (FileInputStream fin = new FileInputStream(file);
				 ObjectInputStream ois = new ObjectInputStream(fin)) {
			file.createNewFile();
			bookList = (List<Book>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FILE WILL BE CREATED AUTOMATICALLY\nDont worry.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void writeBookList(List<Book> books) {
		file.getParentFile().mkdir();
		try (FileOutputStream fout = new FileOutputStream(file);
				 ObjectOutputStream	oos = new ObjectOutputStream(fout)) {
			file.createNewFile();
			oos.writeObject(books);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FILE WILL BE CREATED AUTOMATICALLY\nDont worry.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBook(Book newBook) {
		bookList.add(newBook);
		writeBookList(bookList);
	}

	public void addBook(int id, String author, String name, int year) {
		addBook(new Book(id, author, name, year));
	}

	public void removeBook(int id) {
		for (Book x : bookList) {
			if (x.getId() == id) {
				bookList.remove(x);
				writeBookList(bookList);
				break;
			}
		}
	}

	public void editBook(int id, int key, String value) {
		for (Book x : bookList) {
			if (x.getId() == id) {
				int index = bookList.indexOf(x);
				switch (key) {
				case 1:
					bookList.get(index).setId(Integer.parseInt(value));
					break;
				case 2:
					bookList.get(index).setAuthor(value);
					break;
				case 3:
					bookList.get(index).setName(value);
					break;
				case 4:
					bookList.get(index).setYear(Integer.parseInt(value));
					break;
				default:
					break;
				}
				writeBookList(bookList);
				break;
			}
		}
	}

	public Book findBook(int key, String value) {
		Book result = new Book();
		for (Book x : bookList) {
			switch (key) {
			case 1:
				if (x.getId() == Integer.parseInt(value)) {
					result = x;
				}
				break;
			case 2:
				if (x.getAuthor() == value) {
					result = x;
				}
				break;
			case 3:
				if (x.getName() == value) {
					result = x;
				}
				break;
			case 4:
				if (x.getYear() == Integer.parseInt(value)) {
					result = x;
				}
				break;
			default:
				break;
			}
		}
		return result;
	}

	@Override
	public void close() throws Exception {
		System.out.println("Closed");
	}
}
