package i2r.hlt;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;

import redis.clients.jedis.Jedis;

import com.nytlabs.corpus.NYTCorpusDocument;
import com.nytlabs.corpus.NYTCorpusDocumentParser;

public class NYTDocTest {

	public static void main(String[] args) {

		NYTCorpusDocumentParser nytdocParser = new NYTCorpusDocumentParser();
		NYTCorpusDocument nytdoc = nytdocParser.parseNYTCorpusDocumentFromFile(new File("C:\\Users\\z.fernando\\Documents\\terrier-4.0\\nyt\\data\\1990\\03\\03\\0331303.xml"), false);
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("pub date:" + nytdoc.getPublicationDate());
		System.out.println("alternate url:" + nytdoc.getAlternateURL());
		System.out.println("url:" + nytdoc.getUrl().toString());
		URL url = nytdoc.getUrl();
		System.out.println(url.getQuery().split("=")[1]);
		System.out.println("Guid:"+ nytdoc.getGuid());
		System.out.println("headline:"+ nytdoc.getHeadline());
		System.out.println("descriptors:"+ nytdoc.getDescriptors().toString().toLowerCase()+nytdoc.getOnlinePeople()+nytdoc.getPeople());
		System.out.println("online descriptors:"+ nytdoc.getOnlineDescriptors());
		System.out.println("general online descriptors:"+ nytdoc.getGeneralOnlineDescriptors());
		System.out.println("Normalized Byline:"+ nytdoc.getNormalizedByline());
		System.out.println(""+ nytdoc.getLocations() + nytdoc.getBiographicalCategories() + nytdoc.getOnlineOrganizations());
		System.out.println("Body:" + nytdoc.getBody());
		System.out.println("Lead Para: " + nytdoc.getLeadParagraph());
		/*Jedis jedis = new Jedis("pharos.l3s.uni-hannover.de");
		jedis.select(1);
		String j = jedis.get("ent:9B0DE3D71031F931A35752C0A961948260");
		System.out.println(j);
		if(!jedis.isConnected())
			jedis.connect();
*/
		

	}

}
