package i2r.hlt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import i2r.hlt.wrapper.TerrierWrapper;

/** Shows how to access the term pipeline.
 * - example of tokeniser
 * - show the example of stopword-removal
 * - example of stemming
 * 
 * @author Parth Gupta
 */
public class TermPipeline {
	TerrierWrapper terrier;
	public static void main(String[] args) throws IOException {
		TermPipeline tp =  new TermPipeline();
		
		tp.terrier = new TerrierWrapper("C:\\Users\\z.fernando\\Documents\\terrier-4.0\\terrier-4.0-win\\");
		
		String indexPath = "C:\\Users\\z.fernando\\Documents\\terrier-4.0\\terrier-4.0-win\\var\\index\\tut";
		String prefix = "en";
		String lang = "en";
		
		tp.terrier.setIndex(indexPath, prefix);
		
		tp.terrier.loadIndex(indexPath, prefix, lang);
		// Tokenizer example..
		// lowecase, punctuation marks (at the end of the terms), terms except [a-z0-9] are removed
		BufferedReader br = new BufferedReader(new FileReader("data/barcelona_fc.txt"));
		String line = "";
		while((line=br.readLine())!=null) {
			if(line.length()>1) {
				System.out.println(line);
				System.out.println("Tokenized: " + tp.terrier.tokenizeTerrier(line));
			}
		}
		
		// Stemmer Example..
		tp.terrier.setStemmer("en");
		
		System.out.println(tp.terrier.stem("play"));
		System.out.println(tp.terrier.stem("playing"));
		System.out.println(tp.terrier.stem("played"));
		
	}
}