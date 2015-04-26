import java.util.*;


public class TestProgAB2
{

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      Boolean escape = true; //used to escape while loop
      String input;
      //Integer v;

      TreeAB T = new TreeAB(3,5);    // Make a 3,8-tree

      T.put("a", 1); T.checkTree();
      T.put("c", 3); T.checkTree();
      T.put("b", 2); T.checkTree();

      T.printTree();

      T.put("d", 8); T.checkTree();

      T.put("e", 6); T.checkTree();
      T.put("f", 6); T.checkTree();
      T.put("g", 6); T.checkTree();
      T.put("h", 6); T.checkTree();
      T.put("i", 6); T.checkTree();
      T.put("j", 6); T.checkTree();
      T.put("k", 6); T.checkTree();
      T.put("l", 6); T.checkTree();
      T.put("m", 6); T.checkTree();
      T.put("n", 6); T.checkTree();
      T.put("o", 6); T.checkTree();
      T.put("p", 6); T.checkTree();
     // T.put("s", 6); T.checkTree();

      T.printTree();

      while ( escape )
      {
         System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
         System.out.println("Enter a key to remove: ");
         System.out.print("or enter \"esc\" to end program ");
         input = in.next();
         if (input == "esc"){
        	 escape = false;
        	 break;
         }
         System.out.println("remove(" + input + "):");
         T.remove(input);
         System.out.println();
         System.out.println("== After remove(" + input + "):");
         T.printTree();
         System.out.println();
         System.out.println();
         System.out.println();
         T.checkTree();
      }//closes while
      
      in.close();
      
   }

}