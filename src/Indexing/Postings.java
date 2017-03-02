package Indexing;

import Analyzing.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This Class is only currently used to create a posting list file to be written
 * into storage.
 *
 * @author rl07bebb
 */
public class Postings {

   Document doc;

   /**
    * This Constructor is responsible for creating the post class. The
    * constructor takes a document because it will write to this documents file.
    *
    * @param doc
    */
   public Postings(Document doc) {
      this.doc = doc;
   }

   /**
    * This method is responsible for writing the data into the file.
    *
    * @param s
    */
   public void writeList(String s) {
      Scanner scanner = null;
      PrintWriter writer = null;
      try {
         scanner = new Scanner(s);
         writer = new PrintWriter(doc.getFile(), "UTF-8");
         while (scanner.hasNextLine()) {
            writer.println(scanner.nextLine() + "\n");
         }
      } catch (IOException e) {
         System.err.println("Postings.writeList:Error");
         e.toString();
      } finally {
         scanner.close();
         writer.close();
      }
   }

   /**
    * This method is responsible for writing the data into the file.
    */
   public void writeList() {
      try (PrintWriter writer = new PrintWriter(doc.getFile(), "UTF-8")) {
         Iterator i = Dictionary.getHash().entrySet().iterator();
         while (i.hasNext()) {
            writer.println(i.next().toString());
         }
      } catch (IOException e) {
         System.err.println("Postings.writeList:Error");
         e.toString();
      }
   }
   
   /**
    * This method is responsible for writing the data into the file.
    */
   public void writeList(List<Indexing.Terms> ordered) {
      try (PrintWriter writer = new PrintWriter(doc.getFile(), "UTF-8")) {
         for (Indexing.Terms tt :ordered){
            writer.println(tt.getTerm());
         }
      } catch (IOException e) {
         System.err.println("Postings.writeList:Error");
         e.toString();
      }
   }
}
