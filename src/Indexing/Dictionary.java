package Indexing;

import Analyzing.Document;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class has creates the data structure used to hold the terms which has the reference to the content in the file.
 *
 * @author rl07bebb
 */
public class Dictionary {

    private static HashMap<String, Terms> dictionary = new HashMap<>();
    private HashMap<String, Terms> localDic;

    /**
     * This constructor is only used to create a instancited version of the dictionary for lesser dictionaries.
     */
    public Dictionary() {
        localDic = new HashMap<>();
    }

    /**
     * This method is used to check if a term is new and if so it will create and add the the new term or it will update
     * that term with the new occurrence.
     *
     * @param s        the String of term to be checked
     * @param d        the document of th originating word
     * @param location the location of the term in the document
     * @return
     */
    public static boolean checkNewTerm(String s, Document d, int location) {
        if (dictionary.containsKey(s)) {
            Terms t = dictionary.get(s);
            t.newOccurence(d, location);
            return true;
        } else {
            dictionary.put(s, new Terms(s, d, location));
            return false;
        }
    }

    /**
     * This method is responsible for getting the terms from the dictionary
     *
     * @param t the string of term to be gotten
     * @return
     */
    public static Terms getTerm(String t) {
        return dictionary.get(t);
    }

    /**
     * This method is used to get the dictionary.
     *
     * @return
     */
    public static HashMap<String, Terms> getHash() {
        return dictionary;
    }

    public static String toStringAll() {
        Iterator i = dictionary.entrySet().iterator();
        String s = "";
        while (i.hasNext()) {
            s += i.next().toString();
        }
        return s;
    }

    /**
     * This method is to print all the dictionary terms.
     */
    public static void toPrintAll() {
        Iterator i = dictionary.entrySet().iterator();
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }

    /**
     * This method is used to get the instance of the dictionary.
     *
     * @return
     */
    public HashMap<String, Terms> getHashInst() {
        return localDic;
    }

    /**
     * This method is used to check if a term is new and if so it will create and add the the new term or it will update
     * that term with the new occurrence. This version of the method is for instantiated Dictionaries.
     *
     * @param s        the String of term to be checked
     * @param d        the document of th originating word
     * @param location the location of the term in the document
     * @return
     */
    public boolean checkNewTermInst(String s, Document d, int location) {
        if (localDic.containsKey(s)) {
            Terms t = localDic.get(s);
            t.newOccurence(d, location);
            return true;
        } else {
            localDic.put(s, new Terms(s, d, location));
            return false;
        }
    }

    /**
     * See getTerm
     *
     * @param t
     * @return
     */
    public Terms getTermInst(String t) {
        return localDic.get(t);
    }

    public String toStringAllInst() {
        Iterator i = localDic.entrySet().iterator();
        String s = "";
        while (i.hasNext()) {
            s += i.next().toString();
        }
        return s;
    }

    /**
     * See toPrint
     */
    public void toPrintAllInst() {
        Iterator i = localDic.entrySet().iterator();
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }
}
