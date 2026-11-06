import java.util.*;
public class Main {
 static class Patient {String name;int sev,arrival;Patient(String n,int s,int a){name=n;sev=s;arrival=a;}public String toString(){return name+"(S"+sev+")";}}
 static Comparator<Patient> c=Comparator.comparingInt((Patient p)->p.sev).thenComparingInt(p->p.arrival);
 public static void main(String[]a){PriorityQueue<Patient> q=new PriorityQueue<>(c);String[]n={"Meena","Kumar","Lata","Ravi","John","Asha","Ira","Omar"};int[]s={1,1,2,3,3,5,4,2};for(int i=0;i<n.length;i++)q.add(new Patient(n[i],s[i],i));System.out.println("Direct PriorityQueue: "+q);System.out.print("Treatment order: ");while(!q.isEmpty())System.out.print(q.poll()+" ");System.out.println();
  System.out.println("workload,run,add_poll_ns");for(int z:new int[]{1000,10000,100000}){for(int r=1;r<=10;r++){long t=System.nanoTime();PriorityQueue<Patient> p=new PriorityQueue<>(c);for(int i=0;i<z;i++)p.add(new Patient("P"+i,1+i%5,i));while(!p.isEmpty())p.poll();System.out.println(z+","+r+","+(System.nanoTime()-t));}}
  PriorityQueue<Patient> star=new PriorityQueue<>(c);star.add(new Patient("old severity5",5,0));for(int i=1;i<=20;i++)star.add(new Patient("new S1",1,i));System.out.println("Starvation poll first="+star.poll());
  System.out.println("Fair aging rule: effective severity=max(1,severity-(waitMinutes/10)); recalculate priorities periodically.");
  long t=System.nanoTime();PriorityQueue<Patient> day=new PriorityQueue<>(c);for(int i=0;i<5000;i++)day.add(new Patient("D"+i,1+i%5,i));while(!day.isEmpty())day.poll();System.out.println("5000/day simulation ns="+(System.nanoTime()-t)+" deployment=priority queue with aging");
 }
}
