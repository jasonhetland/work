public class ListTest{

   public static void main(String[] args){
      List<String> A = new List<String>();
     // List<String> B = new List<String>();
     // List<List<String>> C = new List<List<String>>();
      int i, j, k;

      A.add(1, "four");
      A.add(2, "three");
      System.out.println("A: "+A);

      System.out.println("A.equals(A) is "+A.equals(A));
      System.out.println("A.size() is "+A.size());
      A.remove(4);
      System.out.println("A.get(2) is "+A.get(2));
     // try{
       //  System.out.println(A.get(200));
    //  }catch(ListIndexOutOfBoundsException e){
      //   System.out.println("Caught Exception: ");
        // System.out.println(e);
        // System.out.println("Continuing without interuption")
 }
