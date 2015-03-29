public class Node
{
   public Entry[] e;
   public Node[] child;
   public Node   parent;
   public int a = TreeAB.a;
   public int b = TreeAB.b;

   /* =====================================================
      Node(): a 2-4 node contains 4 subtress (and 3 keys)
      ===================================================== */
   public Node()
   {
      int i;

      e = new Entry[b-1];
      child = new Node[b];

      for ( i = 0; i < b-1 ; i++ )
      {
         e[i] = null;
      }

      for ( i = 0; i < b ; i++ )
        child[i] = null;
      
    	parent = null;
        
   }

   public String toString()
   {
	   String node= "(";
	   if (e[0] == null)
		   node = "(-)";
	   else
		   node = "(" + e[0];
	 for (int i = 1; i<b-1; i++)
	 {
		 if (e[i] == null)
			 node = node + "," + "(-)";
		 else
		 node = node + "," + e[i];
	 }
     return ( node + ")" );
   }
}
