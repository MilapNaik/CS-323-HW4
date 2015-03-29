public class Entry
{
   public String key;
   public Integer value;

   public Entry(String k, Integer v)
   {
      key = k;
      value = v;
   }

   public String toString()
   {
      return "(" + key + "," + value + ")";
   }
}
