import java.util.*;


public class TestProgAB1
{

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      
      String k;
      //Integer v;

      TreeAB T = new TreeAB(3,7);    // Make a 3,7-tree

      T.put("a", 1); T.checkTree();
      T.put("c", 3); T.checkTree();
      T.put("m", 6); T.checkTree();
      T.put("l", 6); T.checkTree();
      T.put("d", 4); T.checkTree();
      T.put("e", 5); T.checkTree();
      T.put("j", 6); T.checkTree();
      T.put("f", 6); T.checkTree();

      T.put("i", 9); T.checkTree();
      T.put("b", 2); T.checkTree();
      T.put("g", 7); T.checkTree();

      T.printTree();

      T.put("h", 8); T.checkTree();
      T.put("k", 6); T.checkTree();
      T.put("ka", 6); T.checkTree();
      T.put("kb", 6); T.checkTree();
      T.put("ca", 6); T.checkTree();
      T.put("ba", 6); T.checkTree();
      T.put("bb", 6); T.checkTree();
      T.put("aa", 6); T.checkTree();
      T.put("ax", 6); T.checkTree();
      T.put("ae", 6); T.checkTree();
      T.put("ad", 6); T.checkTree();
      T.put("ac", 6); T.checkTree();
      T.put("ab", 6); T.checkTree();
      T.put("af", 6); T.checkTree();

      T.printTree();

      while ( true )
      {
         System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
         System.out.print("Enter a key: ");
         k = in.next();
         System.out.println("remove(" + k + "):");
         T.remove(k);
         System.out.println();
         System.out.println("== After remove(" + k + "):");
         T.printTree();
         System.out.println();
         System.out.println();
         System.out.println();
         T.checkTree();
      }
   }

}