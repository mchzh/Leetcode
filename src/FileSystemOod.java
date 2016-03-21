import java.util.ArrayList;

public class FileSystemOod {

}
abstract class Entry {
	// common variable
	private Directory parent;  // except the own above layer content
	private String name;
	protected long created;
	
	protected long lastUpdated;
	protected long lastAccessed;
	
	public Entry(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
		created = System.currentTimeMillis();
		lastAccessed = System.currentTimeMillis();
		lastUpdated = System.currentTimeMillis();
	}
	// 1 get full path (recursion)
	// 2 getter and setter func
	// 3 delete func
	// 4 size func
	// entry is every sub part except "/"
	// directory is a group of many entry using arraylist to represent
	public boolean delete() {
		if(parent == null) return false;
		return parent.deleteEntry(this); // from the list to delete a sigle entry
	}
	public abstract int size(); // abstract method for extend class to use common
	public String getFullPath() {
		if(parent == null) {
			return "/" + name;
		} else {
			return parent.getFullPath() + "/" + name;
		}
	}
	
	// getter and setter
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public long getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	// change name func
	public void changeNmae(String n) { name = n; }
	public String getName() {
		return this.name;
	}
}
class File extends Entry {
	private int size;
	private String filename;
	
	

	public File(String name, Directory parent, int size) {
		super(name, parent);
		// TODO Auto-generated constructor stub
		this.size = size;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	// getter and setter
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
class Directory extends Entry {
	private ArrayList<Entry> directorylist;
	
	// getter and setter variable
	public ArrayList<Entry> getDirectorylist() {
		return directorylist;
	}
	public void setDirectorylist(ArrayList<Entry> directorylist) {
		this.directorylist = directorylist;
	}
	public Directory(String name, Directory parent) {
		super(name, parent);
		// TODO Auto-generated constructor stub
		directorylist = new ArrayList<Entry>();
	}
	public void addEntry(Entry entry) {
		directorylist.add(entry);
	}
	public boolean deleteEntry(Entry entry) {
		return directorylist.remove(entry);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		for(Entry e : directorylist) {
			size += e.size();
		}
		return size;
	}
	// recursion to count the file numbers
	public int numberOfFiles() {
		// it need to cosider directory and file two situation
		int filecount = 0;
		for(Entry e : directorylist) {
			if(e instanceof Directory) {
				filecount++; // directory is also a file
				Directory d = (Directory) e;  // critical beiging for recursiong preparing
				filecount += d.numberOfFiles();
			} else {
				if(e instanceof File) {
					filecount++;
				}
			}
		}
		return filecount;
	}
}