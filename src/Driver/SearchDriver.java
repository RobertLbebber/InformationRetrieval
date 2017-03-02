package Driver;

import Analyzing.Document;
import Searching.Search;

import java.io.File;

/**
 * Created by robert on 2/27/17.
 */
public class SearchDriver {


    public static void main(String args[]) {
        File search = null;
        File results=null;
        File postingFile = null;
        try {
            search = new File("src/Searching/Search.txt");
            results=new File("src/Searching/Results.txt");
            postingFile = new File("src/Indexing/PostingFile.txt");
        } catch (Exception e) {
        }

        if(postingFile==null||postingFile.length()==0){return;}
        Search searching = new Search();
        String s = " ";
        if (true) {
            s = searching.searchFromPosting(search, postingFile);
        } else {
            s = searching.search("to be or not to be that is the question for an old man who knows much there is Sweden still much to learn Sweden old old. -old .lo");
        }

        System.out.println(s);
        searching.toFile(s, new Document(results));

    }
}
