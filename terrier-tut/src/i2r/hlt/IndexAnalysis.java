package i2r.hlt;

import java.io.File;
import java.io.IOException;

import i2r.hlt.wrapper.TerrierWrapper;

/** To analyse the index for number of documents. Then printing top N terms in those 
 * documents based on TF-IDF scores.
 * 
 * @author Parth Gupta
 *
 */
public class IndexAnalysis {
	TerrierWrapper terrier;
	public static void main(String[] args) throws IOException {
		IndexAnalysis analysis = new IndexAnalysis();
		
		// specify full path to Terrier home directory
		String terrier_home = "C:\\Users\\z.fernando\\Documents\\terrier-4.0\\terrier-4.0-win";
		
		String indexPath = terrier_home+"\\var\\index\\tut\\";
		String lang = "en";
		
		String prefix = "en";
		String path_to_data = "C:\\Users\\z.fernando\\Downloads\\terrier-tut\\terrier-tut\\data";
		
		analysis.terrier = new TerrierWrapper(terrier_home);
		analysis.terrier.setIndex(indexPath, prefix);
		/*if(!new File(indexPath+prefix+".docid.map").exists()) {
			analysis.terrier.prepareIndex(path_to_data, "txt", lang, true, true);
		}*/
		
		analysis.terrier.loadIndex(indexPath, prefix, lang);
		
		
		for(int i: analysis.terrier.idMap.keys()) {
			System.out.println("docid: " + i + "\t" + "DocName: " +analysis.terrier.idMap.get(i));
			String[] top = analysis.terrier.topTerms(i, 5);
			System.out.println("Top 5 Terms (TF-IDF)");
			for(String s: top)
				System.out.println("\t"+s);
			System.out.println();
			
			// Following command prints the terms of the document with docid.
			analysis.terrier.printTermsOfDocument(i);
		}
		
		
		
	}
}