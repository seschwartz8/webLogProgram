
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
        // Number of uinique IP addresses
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
    }
}
