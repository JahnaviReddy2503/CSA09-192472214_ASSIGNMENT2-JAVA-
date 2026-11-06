import java.util.*;
public class Main {
 static long ns(Runnable r){long t=System.nanoTime();r.run();return System.nanoTime()-t;}
 public static void main(String[]a){System.out.println("workload,map,run,lookup_ns,code_report_ns,insertion_report_ns,order_correct");
  for(int n:new int[]{1000,10000,100000})for(String kind:new String[]{"HashMap","TreeMap","LinkedHashMap"}){Map<String,String> m=kind.equals("HashMap")?new HashMap<>():kind.equals("TreeMap")?new TreeMap<>():new LinkedHashMap<>();for(int i=0;i<n;i++)m.put(String.format("%06d",i),"Medicine"+i);
   for(int r=1;r<=10;r++){long t=ns(()->{for(int i=0;i<100000;i++)m.get(String.format("%06d",i%n));});long c=ns(()->{List<String> k=new ArrayList<>(m.keySet());if(kind.equals("HashMap")||kind.equals("LinkedHashMap"))Collections.sort(k);});long ins=ns(()->{for(String k:m.keySet()){}});
    boolean ok=kind.equals("TreeMap")||kind.equals("HashMap");System.out.println(n+","+kind+","+r+","+t+","+c+","+ins+","+ok);}
  }
  System.out.println("Alternative sorted array: binarySearch O(log n), insertion shifting O(n), code report O(n). Hashtable is synchronized legacy and disallows nulls.");
 }
}
