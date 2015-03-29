//import java.util.*;


public class TreeAB
{
    /* =================================================
       Variables
       ================================================= */
    public static int a;  // lower bound on node size
    public static int b;  // upper bound on node size

    public Node root;  // Root of the tree
    public Node searchEP;   // Last node visited by findEntry()

    /* =================================================
       Constructor
       ================================================= */
    public TreeAB(int a, int b)
    {
      
      TreeAB.a = a;
      TreeAB.b = b;
      root = null;
    }

    boolean print = false;

    public Entry keySearch(String k)
    {
    int lev = 0;
    int i;
    Node curr;

//  System.out.println("enter keySearch(" + k + ")");
    searchEP = root;

    curr = root;
    while ( curr != null )
    {
       searchEP = curr;

       if ( print )
       {
          for ( i = 0; i < lev; i++ )
          {
             System.out.print("     ");
          }
          lev++;
          System.out.print( searchEP );
       }
       for (i = 0; i < b-1; i++)
       {
         /* ============================================================
            It is important to know that a node looks like this:
   
            Node:   child[0] entry[0] child[1] entry[1] ...

     0-node: null     null     null     null ...
     1-node: impossible !!!
            2-node: child[0] entry[0] child[1] null ...
     3-node: child[0] entry[0] child[1] entry[1] child[2] null ...
     ============================================================= */ 
//        System.out.println("i = "+i);
          if ( curr.e[i] != null && k.compareTo( curr.e[i].key ) < 0 )
          {
//           System.out.println("Search subtree "+i+" of node " + curr);

             if ( print )
                System.out.println(" ---- traverse LEFT subtree of "
                		+curr.e[i]);

             curr = curr.child[i];    
             break;                 // end to for
          }

          if ( curr.e[i] != null && k.compareTo( curr.e[i].key ) == 0 )
          {
             if ( print )
                System.out.println(" ---- FOUND: " +curr.e[i]);

             return( curr.e[i] );    
          }

          if ( i == b-2 || curr.e[i+1] == null ) // e[i] is last value key
          {
        	  // System.out.println("Search subtree "+i+" of node " + curr);
             if ( print )
                System.out.println(" ---- traverse RIGHT subtree of "
                		+curr.e[i]);

             curr = curr.child[i+1];    
             break;                 // end to for
          }
       }
    }

    if ( print )
       System.out.println("\n===== Not found... Search ended at node: " 
    		   			+ searchEP);

//  System.out.println("exit keySearch(" + k + ")");
    return(null);   // k not found
 }



    /* ================================================
       get(k): return value associated with key k
       ================================================ */
    public Integer get(String k)
    {
      Entry e;
      e = keySearch(k);
      
      if(e==null)
        return null;
      else
        return(e.value);

    }


    /* ================================================
       put(k): insert (k,v)
       ================================================ */
    public Integer put(String k, Integer v)
    {
      Node n;
      Entry e;
      
      if (root == null)
      {
        n = new Node();
        
        n.e[0] = new Entry(k, v);
        
        root = n;
        n.parent = n;
        return null;
      }
      e = keySearch(k);
      
      //System.out.println("Last node visited = "+searchEP);
      
      if (e != null && k.compareTo(e.key) == 0)
      {
        Integer ov;
        
        System.out.print("update value of " + k + 
                         " in entry " +e);
        
        ov = e.value;
        e.value = v;
        
        return ov;
      }
      //if e already exists, update it
      if (e != null && k.compareTo(e.key) == 0)
      {
        Integer ov;
        
        System.out.print("update value of " + k + 
                         " in entry " +e);
        
        ov = e.value;
        e.value = v;
        
        return ov;
      }
      //key not found, enter it into the last position the search key was in
      e = new Entry(k,v);
      insertEntryInThisNode(e, null, searchEP);
        
      return null;
        
    }
      
      public void insertEntryInThisNode(Entry e, Node rightSubTree, Node p)
      {  
    	  int i;
    	  Entry n;
    	  Node q;
    	  
         /* System.out.println("*** Insert entry " + e + ", R subtree " 
        		  + rightSubTree + " in node " + p);*/
          
          //int l = ((int)Math.ceil(b/2));
          //if there's room
    	  if (p.e[b-2] == null)
    	  {
    		  insertEntryDirectlyInNode(e, rightSubTree, p );
    	  }  
    	  else
    	  {//you have to split a node
    		  Entry[] x_e = new Entry[b];
    		  Node[] x_child = new Node[b+1];
    		  
    		  x_child[0] = p.child[0];
    		  
    		  i = 0;
    		  while(i<(b-1) && p.e[i].key.compareTo(e.key)< 0 )
    		  	{
    			  x_e[i] = p.e[i];
    			  x_child[i+1] = p.child[i+1];
    			  i++;
    		  }
    		  
    		  x_e[i] = e;
    		  x_child[i+1] = rightSubTree;
    		  
    		  while (i<b-1)
    		  {
    			  x_e[i+1] = p.e[i];
    			  x_child[i+2] = p.child[i+1];
    			  i++;
    		  }
    		  int l = ((int) Math.ceil(b/2));
    		 /* System.out.print(" ---- Transitional node = ");
              for (i=0; i<b; i++)
              {
                 if ( i != b-2 )
                    System.out.print( x_e[i].key + " ");
                 else
                    System.out.print( "(" + x_e[i].key + ") ");
              }*/
              System.out.println();
    		  i=0;
    		  while (i < b-1)
    		  {
    			  p.child[i] = x_child[i];
    			  p.e[i] = x_e[i];
    			  i++;
    		  }
    		  p.child[b-2] = x_child[b-2];
    		 // System.out.println("****" + p.child[b-2]);
    		  p.e[b-2] = null;
    		  //p.e[b-3] = null;
    		  p.child[b-1] = null;
    		  
    		  n = x_e[b-2];
    		  
    		  q =new Node();
    		  
    		  q.child[0] = x_child[b-1];
    		  q.e[0] = x_e[b-1];
    		  q.child[1] = x_child[b];
    		  
    		  
    		  for (int j = 0; j<l; j++)
    		  {
    		  if ( q.child[j] != null)
    			  q.child[j].parent = q;
    		  //if(q.child[1] != null)
    			  //q.child[1].parent = q;
    		  }
    		  if(p==root)
    		  {
    			  //System.out.println("p==root");
    			  Node newRoot = new Node();
    			  
    			  newRoot.child[0] = p;
    			  newRoot.e[0] = n;
    			  newRoot.child[1] = q;
    			  
    			  p.parent = newRoot;
    			  q.parent = newRoot;
    			  
    			  root = newRoot;
    		  }
    		  else
    		  {
    			  q.parent = p.parent;
    			  insertEntryInThisNode(n, q, p.parent);
    		  }
    		  
    		  
    	  }
      }

public void insertEntryDirectlyInNode(Entry e, Node rightChild, Node p)
{
	
	/*System.out.println("DIRECT Insert entry " + e + ", R subtree " 
			   + rightChild + " in node " + p);*/
	int i;
	//int l = ((int) Math.ceil(b/2));
	//boolean set = false;

	for (int k= 0; k<= b; k++)
	{
		if(p.e[k] == null || 
			(p.e[k] != null && e.key.compareTo(p.e[k].key) <0))
		{
			for(i = b-2; i > k; i--)
			{
				//System.out.println("i: " + i + "k: " + k );
				p.e[i] = p.e[i-1];
				p.child[i+1] = p.child[i];
			}
			p.e[k]=e;
			p.child[k+1] = rightChild;
			//set = true;
			break;
		}
		/*else if (set == false)
		{
			System.out.println("****");
			p.e[b-2] = e;
			p.child[b-1] = rightChild;
		}*/
	}
		/*if ( set == true){
			p.e[b-1] =e;
			p.child[b-2] = rightChild;
		}*/
			
		if (rightChild != null)
		{
			rightChild.parent = p;
		}
		//System.out.println("Result: " + p);

	}
    /* =================================================
       remove(k)
       ================================================= */
   public Integer remove(String k)
   {
     Entry e;
     Node n;
     int p;
     
     e = keySearch(k);
     
     if (e == null)
     {
       return null;
     }
     
     Entry old = e;
     
     n = searchEP;
     
     for (p = 0; p < b-1; p++)
       if (n.e[p] == e)
          break;
     
     if (n.child[0] == null)
     {
       for(int i = p; i<b-2; i++)
         n.e[i] = n.e[i+1];
         n.e[b-2] = null;
       
     }
     else
     {
       Node l;
       
       System.out.println("pos = " + p);
       l = n.child[p+1];
       while (l.child[0] != null)
         l = l.child[0];
       
       System.out.println("Replace " + n.e[p] + "with: " + l.e[0]);
       
       n.e[p] = l.e[0];
       
       for (int i = 0; i<b-2; i++)
         l.e[i] = l.e[i+1];
       l.e[b-2] = null;
       
       n = l;
       
       System.out.println("Result: n = " + n);
     }
       //underflow?
       
       if (n.e[0] == null)
       {
    	   
         handleUnderflow(n, null);
       }
       return old.value;

   }
     
     
   public void handleUnderflow (Node p, Node z)
   {
	   if (p==root)
	   {
		   root = z;
		   return;
	   }
	   
	   Node parent;
	   int pos;
	   
	   parent = p.parent;
	   
	   for (pos = 0; pos < b ; pos++)
	   {
		   if (parent.child[pos] == p)
			   break;
	   }
	   
	      System.out.println("\n========== handle underflow in:");
	      System.out.println("p = " + p);
	      System.out.println("parent = " + parent);
	      System.out.println("pos = " + pos);
	      System.out.println("Subtree Z = " + z);

	   if ((pos <= 2 && parent.child[pos+1] != null) 
			   && parent.child[pos+1].e[1] != null)
	   {
		   Node R_sibling = parent.child[pos+1];
		   
		   p.child[0] = z;
		   if (z != null)
			   z.parent = p;
		   p.e[0] = parent.e[pos];
		   p.child[1] = R_sibling.child[0];
		   if (p.child[1] != null)
			   p.child[1].parent = p;
		   parent.e[pos] = R_sibling.e[0];
		   
		   
		   for (int i = 0; i<b-2; i++)
		   {
			   R_sibling.e[i] = R_sibling.e[i+1];
			   R_sibling.child[i] = R_sibling.child[i+1];
		   }
		   R_sibling.e[b-2] = null;
		   R_sibling.child[b-1] = null;
		   return;
	   }
	   else if (pos >0
		        && parent.child[pos-1].e[1] != null)
	   {
		   Node l_sibling = parent.child[pos-1];
		   int last;
		   
		   for (last = 0; last < b-1; last++)
			   if (l_sibling.e[last] == null)
				   break;
		   if (last >=b-1 || l_sibling.e[last] == null)
			   last--;
		   
		   p.child [0] = l_sibling.child[last+1];
		   if (p.child[0] != null)
			   p.child[0].parent = p;
		   p.e[0] = parent.e[pos-1];
		   
		   p.child[1] = z;
		   if (z!= null)
			   z.parent=p;
		   
		   parent.e[pos-1] = l_sibling.e[last];
		   
		   l_sibling.e[last] = null;
		   l_sibling.child[last+1] = null;
		   return;
	   }

	   else if (pos!= b-1
			   && parent.child[pos+1] != null)
	   {
		   
		   Node r_sibling = parent.child[pos+1];
	   
	   System.out.println("Underflow !!! ===>  merge with r " +
	   		"sibling " + r_sibling);
	   
	   p.child[0] = z;
	   if (z!= null)
		   z.parent = p;
	   

       p.e[0] = parent.e[pos];                // Transfer parent's entry

       p.child[1] = r_sibling.child[0];       // Transfer sibling's subtree
       if ( p.child[1] != null )
   p.child[1] .parent = p;

       p.e[1] = r_sibling.e[0];  // Transfer sibling (ONLY) entry

       p.child[2] = r_sibling.child[1];       // Transfer sibling's subtree
       if ( p.child[2] != null )
   p.child[2] .parent = p;

/* ======================================
   Delete parent.e[pos] in parent node
   ====================================== */
       for ( int i = pos; i < 2; i++ )
{
   parent.e[i] = parent.e[i+1];
   parent.child[i+1] = parent.child[i+2];
       }
       parent.e[2] = null;
       parent.child[3] = null;

if ( parent.e[0] == null )
           handleUnderflow(parent, p);
    }
    else // pos == 3, we must merge LEFT ...
    {
       /* -----------------------------------------------------------
          Legend to understand the transfer operation: (assume pos=1)

                          pos-1       pos
                            |          |
                            V          V
              parent --> | T0 | *e0* | T1 | e1 | T2 | e2 | T3 |
                            |          |
                      L_sibling        p

          Entry sandwiched between pos-1 and pos is moved into p !!!
          ----------------------------------------------------------- */
       Node l_sibling = parent.child[pos-1]; // L_sibling has 1 entry !

       System.out.println("Underflow !!!! ===> MERGE with L sibling "
                              + l_sibling);

       l_sibling.e[1] =  parent.e[pos-1];     // Transfer parent's entry
       l_sibling.child[2] = z;  // Hang Z
       if ( z != null ) z.parent = p;

/* ======================================
   Delete parent.e[pos] in parent node
   ====================================== */
       for ( int i = pos-1; i < 2; i++ )
{
   parent.e[i] = parent.e[i+1];
   parent.child[i+1] = parent.child[i+2];
       }
       parent.e[2] = null;
       parent.child[3] = null;

if ( parent.e[0] == null )
          handleUnderflow(parent, l_sibling);
    }
 }


    /* ==================================================================
       printTree(): print the tree (same format as 2,4-tree example)
       ================================================================== */
    int MaxLevel;

    void padding ( String s, int n )
    {
       int i;
 
       for ( i = 0; i < n; i++ )
          System.out.print( s );
    }


    void printSub (Node p, int id, int level ) 
    {
       if ( level > MaxLevel )
    	   MaxLevel = level;

       int i;
       int l = ((int) Math.ceil(b/2));
       if ( p == null)
          return;
       //for (i = b-1; i>l-1; i--)
      // {
      // }
       for (i = b-2; i>=l; i--)
    	   if (p.child[i] != null){
    	   printSub ( p.child[i], i, level + 1 );
    	   }
         
       if (p.child[l] != null)
       {
    	   
    	   padding( "             ", level );
    	   System.out.println("" + id + ":" + p);
        
    	   if ( id == 0 && level == MaxLevel )
    		   System.out.println();
    	   
    	   for(i = l-1; i>0; i--){
    	   if ( p.child[i] != null )
    	   {
           printSub ( p.child[i], i, level + 1 );
    	   }
    	   }
    	   
    	   
        }
       
         else 
         {
        	 for (i = l-1; i>0; i--){
        	 if (p.child[i] != null)
        	 {
             printSub ( p.child[i], i, level + 1 );
        	 }
        	 }
           
        	 padding( "             ", level );
        	 System.out.println("" + id + ":" + p);
           
        	 if ( id == 0 && level == MaxLevel )
             System.out.println();
         }
        // for (i = l-1; i>=0; i--)
         //{ 
       
        	 if ( p.child[0] != null )
         {
             printSub ( p.child[0], 0, level + 1 );
           }

    }

    public void printTree()
    {
       MaxLevel = 0;
       System.out.println();
       printSub( root, 0, 0);
    }
    public boolean error;
    /* ==================================================================
       checkTree(): check the a,b-tree for consistency
       ================================================================== */
    public void checkTree()
    {
       if ( b <= 0 )
       {
          System.out.println("Error: Your constructor did not set the value b");
   return;
       }

       error = false;
       checkSub( root );  // The real check alg. is recursive
       if ( error )
   System.exit(1);
       System.out.println();
    }
 
    public void checkSub (Node p) 
    {
       int i;

       if ( p == null)
          return;

       // Check parent child relationship for node
       for ( i = 0; i < b; i++ )
   if ( p.child[i] != null )
   {
      if ( p.child[i].parent != p )
      {
         printTree();
         System.out.println("---------------------------");
         System.out.println("Error:");
         System.out.println("p: " + p);
         System.out.println("p.child[" + i + "] = " + p.child[i]);
         System.out.println("BUT: p.child[" + i + "].parent = " 
   + p.child[i].parent);
             }
          }

       // Recurse
       for ( i = 0; i < b; i++ )
   if ( p.child[i] != null )
      checkSub(  p.child[i] );
    }

}