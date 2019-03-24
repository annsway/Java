public class fake_swap_ex {
   public static void main(String[] args) {
      int a = 5;
      int b = 7;
      // swap(a,b);
      fake_swap(a,b);
      System.out.println(a + ", " + b);
   }
   // Note: Java is pass by value, so the swap method only swaps the parameters a and b, not the original values of a and b in main.

   public static void fake_swap(int aa, int bb) {
      int temp = aa;
      aa = bb;
      bb = temp;
   }
}
