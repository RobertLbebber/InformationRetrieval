/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexing;

/**
 *
 * @author rl07bebb
 */

import Analyzing.Document;

import java.util.ArrayList;

/**
 * This class is responsible for creating the data that holds the terms and their related information
 */
public class Terms {

   private ArrayList<Document> docList;
   private final String TERM;
   private ArrayList<Integer> frequency;
   private ArrayList<Integer> firstOccurence;
   private Document lastSeen;

   /**
    * This Constructor Creates the terms with the first instance of the term in a documents and its location.
    *
    * @param s
    * @param d
    * @param l
    */
   public Terms(String s, Document d, int l) {
      docList = new ArrayList<>();
      firstOccurence = new ArrayList<>();
      frequency= new ArrayList<>();
      firstOccurence.add(l);
      TERM = s;
      docList.add(d);
      frequency.add(1);
      lastSeen=d;
   }

   /**
    * This method is responsible for incrementing the method and checking if the document is new one.
    *
    * @param d the document the term is found in
    * @param l the word location that the word is found in
    */
   public void newOccurence(Document d, int l) {
      if (!d.equals(lastSeen)) {
         lastSeen=d;
         docList.add(d);
         firstOccurence.add(l);
         frequency.add(1);
      }else{
         int i=frequency.get(frequency.size()-1);
         i++;
         frequency.set(frequency.size()-1,i);
      }
   }

   /**
    * This method is responsible for checking if the document is new or not.
    *
    * @param d the document the term is found in
    * @return
    */
   public boolean isNewDoc(Document d) {
      return docList.contains(d);
   }

   /**
    * This method is responsible for getting the Term's name as a String.
    *
    * @return
    */
   public String getTerm() {
      return TERM;
   }

   /**
    * This method is responsible for returning the string with its data.
    *
    * @return
    */
   @Override
   public String toString() {
      String s = "Term                      ";
      if (s.length() > TERM.length()) {
         s = s.substring(0, s.length() - TERM.length());
      } 
      s += " Number of Documents holding Term: " + docList.size()+"\t";
      for (int i = 0; i < docList.size(); i++) {
         s += " <Doc: " + docList.get(i).getDocID() + "| FirstSeen: " + firstOccurence.get(i) +  "| Freq: " + frequency.get(i) + ">\t";
      }
      s += "\n";
      return s;
   }
}
