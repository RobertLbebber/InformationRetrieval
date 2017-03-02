/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzing;

import Indexing.Dictionary;
import Indexing.Postings;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author rl07bebb
 */
public class DocumentAnalyzer {

   /**
    * This method is just to compare the Indexing.Terms.
    */
   private static Comparator<Indexing.Terms> ALPHABETICAL_ORDER = new Comparator<Indexing.Terms>() {
      public int compare(Indexing.Terms str1, Indexing.Terms str2) {
         int res = String.CASE_INSENSITIVE_ORDER.compare(str1.getTerm(), str2.getTerm());
         if (res == 0) {
            res = str1.getTerm().compareTo(str2.getTerm());
         }
         return res;
      }
   };

   public DocumentAnalyzer(File stoplist, File PostingFile, File termsList, File... args) {
      Postings postings = new Postings(new Document(PostingFile));
      Tokenizer token = new Tokenizer(stoplist);

      for (File arg : args) {
         token.readFile(new Document(arg, arg.getName(), arg.length()), null);
      }
      /*
       for (int i = 0; i < 100; i++) {
       token.readFile(new Document(args[i], args[i].getName(), args[i].length()), null);
       }
       /* */

      postings.writeList();

      //makeTermFile(termsList, null);
   }

   /**
    * This method is used to create a text file of all words in the documents
    * and in order.
    *
    * @param termListLoc the location of the text file to be created
    * @param dictionary the contents of the file being created
    */
   public void makeTermFile(File termListLoc, Dictionary dictionary) {
      List<Indexing.Terms> ordered;
      if (dictionary == null) {
         ordered = new ArrayList<>(Dictionary.getHash().values());
      } else {
         ordered = new ArrayList<>(dictionary.getHashInst().values());
      }
      Collections.sort(ordered, ALPHABETICAL_ORDER);
      Postings postings = new Postings(new Document(termListLoc));
      postings.writeList(ordered);
   }
}
