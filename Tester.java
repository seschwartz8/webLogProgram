
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // Print all parsed records
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
        // Print number of uinique IP addresses
        LogAnalyzer analyzer2 = new LogAnalyzer();
        analyzer2.readFile("short-test_log");
        int uniqueIps = analyzer2.countUniqueIps();
        System.out.println(uniqueIps);
        // Print log entries with higher record than given num
        int num = 200;
        LogAnalyzer analyzer3 = new LogAnalyzer();
        analyzer3.readFile("short-test_log");
        analyzer3.printAllHigherThanNum(num);
        // Print number of unique IP address visits on specified day
        LogAnalyzer analyzer4 = new LogAnalyzer();
        analyzer4.readFile("weblog-short_log");
        System.out.println(analyzer4.uniqueIPVisitsOnDay("Sep 30").size());
        // Print number of unique IP addresses with statu in range of low to high (inclusive)
        LogAnalyzer analyzer5 = new LogAnalyzer();
        analyzer5.readFile("short-test_log");
        System.out.println(analyzer5.countUniqueIPsInRange(200,299));
        // Prints number of visits for each IP address
        LogAnalyzer analyzer6 = new LogAnalyzer();
        analyzer6.readFile("short-test_log");
        HashMap<String,Integer> counts = analyzer6.countVisitsPerIP();
        System.out.println(counts);
        // Prints max number of visits by single IP address
        LogAnalyzer analyzer7 = new LogAnalyzer();
        analyzer7.readFile("weblog3-short_log");
        int maxVisits = analyzer7.mostVisitsByIP(analyzer7.countVisitsPerIP());
        System.out.println(maxVisits);
        // Prints list of addresses that visited website max times
        LogAnalyzer analyzer8 = new LogAnalyzer();
        analyzer8.readFile("weblog3-short_log");
        ArrayList<String> maxIPs = analyzer8.iPsMostVisits(analyzer8.countVisitsPerIP());
        System.out.println(maxIPs);
        // Prints HashMap with addresses that visited a site each day
        LogAnalyzer analyzer9 = new LogAnalyzer();
        analyzer9.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> iPsEachDay = analyzer9.iPsForDays();
        System.out.println(iPsEachDay);
    }
}
