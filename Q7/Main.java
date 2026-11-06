import java.io.*;import java.nio.file.*;import java.util.*;
public class Main {
 static long original(int n){String s="";long t=System.nanoTime();for(int i=0;i<n;i++)s+="ticket"+i+";";return System.nanoTime()-t;}
 static long redesign(int n){StringBuilder b=new StringBuilder();long t=System.nanoTime();for(int i=0;i<n;i++)b.append("ticket").append(i).append(';');return System.nanoTime()-t;}
 public static void main(String[]a)throws Exception{System.out.println("Q7 Diagnose, redesign, validate");System.out.println("bottlenecks,Q1 repeated plus -> intermediate copies; Q2 front shifts; Q3 missing hashCode; Q4 sorting unordered report; Q5 faulty comparator; Q6 starvation");
  System.out.println("workload,method,run,elapsed_ns");for(int n:new int[]{1000,10000,100000}){for(int r=1;r<=10;r++){System.out.println(n+",original,"+r+","+original(n));System.out.println(n+",redesign,"+r+","+redesign(n));}}
  System.out.println("Solution: replace repeated concatenation with a per-ticket StringBuilder; limitation: builder is mutable and not safe for shared concurrent use.");
  System.out.println("Evidence source files present="+Files.exists(Path.of("..","Q1","results.csv"))+" notes="+Files.exists(Path.of("..","Q1","notes.md")));
 }
}
