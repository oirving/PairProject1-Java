import Untils.BasicWordCount;
import Bean.Word;
public class Main {
	

	public static void main(String[] args) {
		String fileName = "testInput.txt";
		
		BasicWordCount basicWordCount = new BasicWordCount();
		long num = basicWordCount.characterCount(fileName);
		System.out.println("characters:"+num);
		System.out.println("words:"+basicWordCount.wordCount(fileName));
		System.out.println("lines:number");
		Word[] topTenWord = basicWordCount.topTenWord(fileName);
		for(int i = topTenWord.length - 1; i >= 0; i--) {
			System.out.println("<"+topTenWord[i].getKey()+">:"+topTenWord[i].getCountNum());
		}
	}
	
	
}