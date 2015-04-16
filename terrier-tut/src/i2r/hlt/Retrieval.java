package i2r.hlt;

import i2r.hlt.wrapper.TerrierWrapper;

import java.io.File;
import java.io.IOException;

import org.terrier.matching.ResultSet;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class Retrieval {
	TerrierWrapper terrier;
	public static void main(String[] args) throws IOException, RecognitionException, TokenStreamException {
		Retrieval ret = new Retrieval();
		
		// specify full path to Terrier home directory
		String terrier_home = "/home/parth/workspace/terrier-3.5/";
		
		String indexPath = terrier_home+"/var/index/tut/";
		String prefix = "en";
		String path_to_data = "";
		String lang = "en";
		
		boolean stopword_removal = true;
		boolean stem = true;
		
		ret.terrier = new TerrierWrapper(terrier_home);
		ret.terrier.setIndex(indexPath, prefix);
		if(!new File(indexPath+prefix+".docid.map").exists()) {
			ret.terrier.prepareIndex(path_to_data, "txt", lang, stopword_removal, stem);
		}
		
		ret.terrier.loadIndex(indexPath, prefix, lang);
		ret.terrier.setStopwordRemoval(lang);
		ret.terrier.setStemmer(lang);
		
		System.setProperty("ignore.low.idf.terms", "false");
		ResultSet rs = ret.terrier.getResultSet("Barcelona city", "TF_IDF", false, 8);
		
		int[] docid = rs.getDocids();
		double[] scores = rs.getScores();
		
		for(int i=0; i<docid.length; i++) {
			System.out.println("Docid: " + docid[i] +"\tScore:" + scores[i] + "\tDocName:" + ret.terrier.idMap.get(docid[i]));
		}
		
		
	}
}