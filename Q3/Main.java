import java.util.*;
public class Main {
 static class Broken {final String r,n;Broken(String r,String n){this.r=r;this.n=n;}public boolean equals(Object o){return o instanceof Broken b&&r.equals(b.r);}}
 static class Student {private final String regNo,name;Student(String r,String n){regNo=r;name=n;}public boolean equals(Object o){return this==o||o instanceof Student s&&regNo.equals(s.regNo);}public int hashCode(){return regNo.hashCode();}public String toString(){return regNo+" "+name;}}
 public static void main(String[]x){System.out.println("Q3 Attendance de-duplication");List<String[]> in=List.of(new String[]{"A01","Ali"},new String[]{"A02","Bea"},new String[]{"A01","Ali"});
  Set<Broken> before=new HashSet<>();for(var s:in)before.add(new Broken(s[0],s[1]));System.out.println("Before hashCode: Unique students: "+before.size());
  Set<Student> after=new HashSet<>();for(var s:in)after.add(new Student(s[0],s[1]));System.out.println("After hashCode: Unique students: "+after.size());
  System.out.println("T1 distinct="+new HashSet<>(List.of(new Student("1","A"),new Student("2","B"))).size());
  System.out.println("T2 duplicates="+after.size());Student m=new Student("3","C");Set<Student>s=new HashSet<>();s.add(m);System.out.println("T3 contains="+s.contains(m)+" remove="+s.remove(m));
  System.out.println("Alternatives: TreeSet comparator O(log n); HashMap<String,Student> explicit key.");
 }
}
