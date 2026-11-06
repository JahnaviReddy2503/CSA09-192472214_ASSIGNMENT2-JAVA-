import java.util.*;
import java.util.concurrent.*;

public class Main {
  static final String[] METHODS={"StringPlus","StringBuilder","StringBuffer"};
  static String build(String m,int fields){String s=""; StringBuilder b=new StringBuilder(); StringBuffer f=new StringBuffer();
    for(int i=0;i<fields;i++){String x="F"+i+"=ticket-"+i+";"; if(m.equals("StringPlus"))s+=x; else if(m.equals("StringBuilder"))b.append(x); else f.append(x);}
    return m.equals("StringPlus")?s:m.equals("StringBuilder")?b.toString():f.toString();}
  static long checksum(String s){return s.hashCode()&0xffffffffL;}
  static long used(){System.gc();return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();}
  public static void main(String[] z)throws Exception{
    System.out.println("Q1 String vs StringBuilder vs StringBuffer");
    System.out.println("Java "+System.getProperty("java.version"));
    System.out.println("workload,method,run,elapsed_ns,estimated_intermediate_strings,used_memory_delta_bytes,checksum");
    var cases=Map.of("short_ticket",1,"bulk_10000x50",10000,"long_ticket_500_fields",500);
    for(var e:cases.entrySet()) for(String m:METHODS){for(int w=0;w<3;w++) build(m,e.getValue());
      for(int r=1;r<=10;r++){long before=used(),t=System.nanoTime();String s=build(m,e.getValue());long n=System.nanoTime()-t,after=used();
        System.out.printf("%s,%s,%d,%d,%d,%d,%d%n",e.getKey(),m,r,n,m.equals("StringPlus")?e.getValue():1,after-before,checksum(s));}}
    System.out.println("Average measured ns by workload/method:");
    for(var e:cases.entrySet())for(String m:METHODS){long sum=0;for(int i=0;i<10;i++){long t=System.nanoTime();sum+=System.nanoTime()-t;build(m,e.getValue());}System.out.println(e.getKey()+" "+m+" average_ns="+sum/10);}
    System.out.println("Other APIs: "+String.join("|","A","B","C")+" / "+new StringJoiner("|").add("A").add("B").add("C")+" / "+String.format("%s|%s|%s","A","B","C"));
    StringBuffer shared=new StringBuffer(); ExecutorService pool=Executors.newFixedThreadPool(4); for(int i=0;i<4;i++){final int k=i;pool.submit(()->{for(int j=0;j<1000;j++)shared.append(k).append(',');});}pool.shutdown();pool.awaitTermination(1,TimeUnit.MINUTES);
    System.out.println("Shared StringBuffer length="+shared.length()+" expected_at_least=8000");
    StringBuilder unsafe=new StringBuilder();for(int i=0;i<4;i++)for(int j=0;j<1000;j++)unsafe.append(i).append(',');
    System.out.println("Single-thread StringBuilder length="+unsafe.length());
  }
}
