import java.util.*;
public class Main {
 static class Team {String name;int points,goalDiff;Team(String n,int p,int g){name=n;points=p;goalDiff=g;}public String toString(){return name+"("+points+","+goalDiff+")";}}
 static class Leaderboard<T>{List<T> data;Comparator<T> c;Leaderboard(List<T>d,Comparator<T>c){data=d;this.c=c;}List<T> ranking(){List<T>x=new ArrayList<>(data);x.sort(c);return x;}}
 static <T extends Comparable<? super T>> T best(List<T>x){return Collections.max(x);}
 static <T> void copyInto(List<? super T> out,List<? extends T> in){out.addAll(in);}
 public static void main(String[]a){Comparator<Team> c=Comparator.comparingInt((Team t)->t.points).reversed().thenComparing(Comparator.comparingInt((Team t)->t.goalDiff).reversed()).thenComparing(t->t.name);
  List<Team> x=new ArrayList<>(List.of(new Team("Panthers",9,4),new Team("Cobras",6,1),new Team("Bisons",9,4),new Team("Eagles",9,6)));Leaderboard<Team> b=new Leaderboard<>(x,c);System.out.println("Expected ranking: "+b.ranking());System.out.println("Original unchanged: "+x);System.out.println("bounded generic and wildcard methods compiled");
  Comparator<Team> bad=(u,v)->u.points-v.points;Comparator<Team> missing=Comparator.comparingInt((Team t)->t.points).reversed();System.out.println("Faulty overflow comparator sign="+bad.compare(new Team("MAX",Integer.MAX_VALUE,0),new Team("MIN",Integer.MIN_VALUE,0)));System.out.println("Missing tie-breaker compare="+missing.compare(new Team("A",9,1),new Team("B",9,8)));
 }
}
