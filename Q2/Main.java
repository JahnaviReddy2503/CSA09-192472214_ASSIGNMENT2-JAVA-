import java.util.*;
public class Main {
 static long time(Runnable r){long t=System.nanoTime();r.run();return System.nanoTime()-t;}
 static void row(String w,String op,int n,long x){System.out.println(w+","+op+","+n+","+x);}
 public static void main(String[]a){System.out.println("workload,operation,size,elapsed_ns");
  int[] ns={1000,10000,100000}; for(int n:ns){for(String type:new String[]{"ArrayList","LinkedList","ArrayDeque"}){
    List<Integer> l=type.equals("ArrayList")?new ArrayList<>():new LinkedList<>(); for(int i=0;i<n;i++)l.add(i);
    for(String op:new String[]{"add_end","insert_front","insert_middle","remove_front","remove_middle","get_kth","iterate"}){
      for(int i=0;i<2;i++) { final String o=op; time(()->run(l,o));}
      long s=0;for(int r=1;r<=10;r++){long x=time(()->run(l,op));s+=x;row(type,op,n,x);}System.out.println("average,"+type+" "+op+" "+n+","+(s/10));}
   } }
  System.out.println("Up Next queue:");ArrayDeque<Integer> q=new ArrayDeque<>();long t=System.nanoTime();for(int i=0;i<100000;i++){q.addLast(i);if(i%2==0)q.addFirst(-i);q.pollFirst();}System.out.println("arraydeque_ns="+(System.nanoTime()-t)+" remaining="+q.size());
  System.out.println("Alternative plain array uses shifting O(n) front/middle operations.");
 }
 static void run(List<Integer> l,String op){int n=l.size();switch(op){case"add_end"->l.add(1);case"insert_front"->l.add(0,1);case"insert_middle"->l.add(n/2,1);case"remove_front"->l.remove(0);case"remove_middle"->l.remove(l.size()/2);case"get_kth"->l.get(l.size()/2);case"iterate"->{long x=0;for(int v:l)x+=v;}}}
}
