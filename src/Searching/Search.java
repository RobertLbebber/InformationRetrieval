/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Searching;

import Analyzing.Document;
import Analyzing.Tokenizer;
import Indexing.Dictionary;
import Indexing.Postings;
import Indexing.Terms;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class operates two searching types. One Search is from the driver and
 * will output a response in the Console. The other search type will read a
 * search file and output the file into the posting file that it receives.
 *
 * @author rl07bebb
 */
public class Search {

   private Pattern pattern = Pattern.compile("[\\W\\d\\s]");

   /**
    * This method is used to search the terms from the search file contained in
    * the dictionary.
    *
    * @param query the user search request.
    * @param postings
    * @return
    */
   public String searchFromPosting(File query, File postings) {
      String results = "";
      Dictionary queryList = new Dictionary();
      int count = 0;
      try (Scanner scanner = new Scanner(query)) {
         while (scanner.hasNext()) {
            count++;
            queryList.checkNewTermInst(scanner.next(), new Document(query), count);
         }

         Scanner postingsScan = new Scanner(postings);
         while (postingsScan.hasNextLine()) {
            String words = postingsScan.nextLine();
            String word = new String(words);
            Matcher match = pattern.matcher(word);
            if (match.find()) {
               word = match.group();
            }

            Terms term = queryList.getTermInst(word);
            if (term != null) {
               results += words + "\n";
            }
         }
      } catch (IOException e) {
         System.err.println("Tokenizer.readFile():Error");
         e.toString();
      }
      return results;
   }

   /**
    * This method is used to search the dictionary of the terms sent from the
    * query made in the Driver class.
    *
    * @param query the terms sent from the query made by the user
    * @return the resulting posting file
    */
   public String search(String query) {
      String results = "";
      Dictionary queryList = new Dictionary();
      int count = 0;
      new Tokenizer().readQuery(results, queryList);
      Scanner scanner = new Scanner(query);
      while (scanner.hasNext()) {
         String word = scanner.next().toLowerCase();
         word = pattern.matcher(word).replaceAll("");
         queryList.checkNewTermInst(word, new Document(query), count);
      }
      Iterator i = queryList.getHash().values().iterator();
      while (i.hasNext()) {
         Terms t = (Terms) i.next();
         Terms term = Dictionary.getTerm(t.getTerm());
         if (term != null) {
            results += t.getTerm() + "=" + term.toString() + "\n";
         }
      }
      return results;
   }

   /**
    * This method rights the search into a resulting file.
    *
    * @param results
    * @param doc
    */
   public void toFile(String results, Document doc) {
      new Postings(doc).writeList(results);
   }
}
