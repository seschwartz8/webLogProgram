
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public ArrayList<LogEntry> readFile(String filename) {
         // SUMMARY: reads each line of file and parses into ArrayList of log entries
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry entry = WebLogParser.parseEntry(line);
             records.add(entry);
         }
         return records;
     }
     
     public int countUniqueIps() {
         // SUMMARY: returns number of unique IP addresses in records ArrayList
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records){
             String address = le.getIpAddress();
             if (!uniqueIps.contains(address)){
                 uniqueIps.add(address);
             }
         }
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
        // SUMMARY: prints log entry if the status code is higher than given num
         for (LogEntry le : records){
             int statusCode = le.getStatusCode();
             if ( statusCode > num ) {
                 System.out.println(le);
             }
        }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         // SUMMARY: returns ArrayList of unique IP addresses that had access on given day
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for (LogEntry le : records){
             String address = le.getIpAddress();
             Date date = le.getAccessTime();
             String dateStr = date.toString();
             if ((!uniqueIPsOnDay.contains(address)) && (dateStr.contains(someday))){
                 uniqueIPsOnDay.add(address);
             }
         }
         return uniqueIPsOnDay;
     }
    
     
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
