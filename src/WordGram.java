
import java.util.Arrays;
/**
 * WordGram objects represent a k-gram of strings/words.
 * @author Antoni Dabrowski
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value
	
	/**
	 * Create object WordGram containing size strings copied from the array source
	 * @param source is an array of strings 
	 * @param start is the index of source from which strings start being copied
	 * @param size is the number of strings that will end up in WordGram
	 */
	public WordGram(String[] source, int start, int size) {
		
		myWords = new String[size];
		myWords = Arrays.copyOfRange(source, start, start+size);
		myHash = 0;
		myToString = null;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * This method will return the order or size of the WordGram
	 * @return length of myWords, the size of the WordGram
	 */
	public int length(){
		return myWords.length;
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		if (Arrays.equals(wg.myWords, this.myWords)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode(){
		myHash = this.toString().hashCode();
		return myHash;
	}
	

	/**
	 * create a new WordGram object wg with same number of entries as original WordGram(this) whose first k-1 entries are the
	 *   same as the last k-1 entries of the original WordGram(this), and whose last entry is the parameter last.
	 * @param last is last String of returned WordGram
	 * @return the new WordGram wg
	 */
	public WordGram shiftAdd(String last) {
		String[] wgNew = new String[this.length()];
		for (int i = 0; i < this.length() - 1; i++) {
			wgNew[i] = myWords[i+1];
		}
		wgNew[this.length() - 1] = last;
		WordGram wg = new WordGram(wgNew,0,this.length());
		//System.out.print(wg);  used this to test new wordgram and make sure it is being created correctly
		return wg;
	}

	@Override
	public String toString(){
		myToString = String.join(" ", myWords);
		return myToString;
	}
}
