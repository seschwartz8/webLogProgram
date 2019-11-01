
/**
 * Sarah Schwartz (code for WebLogParser provided by Duke)
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
             if ( ( !uniqueIPsOnDay.contains(address) ) && ( dateStr.contains(someday) ) ){
                 uniqueIPsOnDay.add(address);
             }
         }
         return uniqueIPsOnDay;
     }
    
     public int countUniqueIPsInRange(int low, int high){
         // SUMMARY: returns number of unique IPs in records that have status code between low to high (inclusive)
         ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
         for (LogEntry le : records){
             String address = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if ( (!uniqueIPsInRange.contains(address) ) && ( statusCode >= low ) && ( statusCode <= high ) ){
                 uniqueIPsInRange.add(address);
             } 
         }
         return uniqueIPsInRange.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         // SUMMARY: returns HashMap of IP addresses to their counts of visits
         HashMap<String,Integer> visitsPerIP = new HashMap<String,Integer>();
         for (LogEntry le: records) {
             String address = le.getIpAddress();
             if (!visitsPerIP.containsKey(address)) {
                 visitsPerIP.put(address, 1);
             } else {
                 visitsPerIP.put(address, visitsPerIP.get(address) + 1);
             }
         }
         return visitsPerIP;
     }
     
     public int mostVisitsByIP(HashMap<String,Integer> visitsPerIP){
         // SUMMARY: returns maximum number of visits to website by single IP address
         int maxVisits = 0;
         for (Integer visits : visitsPerIP.values()){
             if (visits > maxVisits) {
                 maxVisits = visits;
             }
         }
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> visitsPerIP){
         // SUMMARY: returns IP addresses that visited website max number of times
         ArrayList<String> mostVisitsIPs = new ArrayList<String>();
         int max = mostVisitsByIP(visitsPerIP);
         for (String address : visitsPerIP.keySet()){
             if (visitsPerIP.get(address).equals(max)){
                 mostVisitsIPs.add(address);
             }
         }
         return mostVisitsIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         // SUMMARY: returns HashMap mapping dates to the IPs that visited on that day (including repeat visits)
         HashMap<String,ArrayList<String>> iPsEachDay = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records){
             String fullDate = le.getAccessTime().toString();
             // Just take the Day/Month/Date (e.g. "Wed Sep 04") part of the date
             String date = fullDate.substring(0, 10);
             String address = le.getIpAddress();
             if (!iPsEachDay.containsKey(date)){
                 // If the map doesn't contain a date yet, add it and start a list of addresses
                 ArrayList<String> firstAddresses = new ArrayList<String>();
                 firstAddresses.add(address);
                 iPsEachDay.put(date, firstAddresses);
             } else {
                 // If the map does contain the date, add the current address to the list of addresses at that date
                 ArrayList<String> currentAddresses = iPsEachDay.get(date);
                 currentAddresses.add(address);
                 iPsEachDay.replace(date, currentAddresses);
             }
         }
         return iPsEachDay;
     }
     
     public String dayWithMostIPVisits (HashMap<String,ArrayList<String>> iPsEachDay){
         // SUMMARY: returns day with most visits to website (if tie, returns any such day)
         int maxVisits = 0;
         String maxDate = "";
         for (String date : iPsEachDay.keySet()){
             if (iPsEachDay.get(date).size() > maxVisits){
                 maxVisits = iPsEachDay.get(date).size();
                 maxDate = date;
             }
         }
         return maxDate;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String,ArrayList<String>> iPsEachDay, String date){
         // SUMMARY: returns list of IP addresses that had most accesses on given day
         HashMap<String,Integer> visitsPerIP = new HashMap<String,Integer>();
         // Find given date, get its list of visits (targetVisits) and count visits per address in visitsPerIPOnDay
         ArrayList<String> targetVisits = iPsEachDay.get(date);
         for (String address : targetVisits){
            if (!visitsPerIP.containsKey(address)){
                visitsPerIP.put(address, 1);
            } else {
                visitsPerIP.replace(address, visitsPerIP.get(address) + 1);
            }
         }
         // Analyze data in visitsPerIPOnDay to get the IPs with the most visits on the given day
         ArrayList<String> iPsWithMostVisitsOnDay = iPsMostVisits(visitsPerIP);
         return iPsWithMostVisitsOnDay;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
