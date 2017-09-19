/* CSC22100-R   Assignment 2 - Grocery Store Test

    Shirong Zheng

    10/14/2016
 */

import java.util.*;

public class GroceryStoreTest {
    final static int defSize=10;
    public static int size=0;
    public static  String items[];

    public static void main(String[] args){


        System.out.println("How many items you want to insert to the Grocery Store? ");
        String[]items=addElements();
        System.out.print("The array of items: ");
        printArray(items);
        System.out.println("Let's search the index of enter element,please enter the name ");
        System.out.println(searchElements(items));
        System.out.println("Delete the given string with its duplicates.");
        duplicateRemove(items);
        System.out.println(" ");
        System.out.println("The computer will add six apples into the original array");
        items = insertElements(items, "apple");
        items = insertElements(items, "apple");
        items = insertElements(items, "apple");
        items = insertElements(items, "apple");
        items = insertElements(items, "apple");
        items = insertElements(items, "apple");
        System.out.println(Arrays.deepToString(items));

        System.out.println(removeElements(items));


    }

    // initialize the size and add elements
    public static String[] addElements() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert the size of array. If your input is 0 then default size is 10.");
        int size = Integer.parseInt(sc.nextLine());
        if(size==0){
            size=defSize;
        }
        System.out.println("Please insert the elements of array:");
         String[] items = new String[size];
        for (int i = 0; i < size; i++) {
            items[i] = sc.nextLine();
        }
        return items;
    }

    //print the array
    public static void printArray(String[]items) {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + ",");
        }
        System.out.println();
    }

   //search index of the input element
   public static int searchElements(String[]items){

       Scanner scanner= new Scanner(System.in);
       String userString=scanner.nextLine();

         for(int i=0;i<items.length;i++){
             if(items[i].equals(userString)){
                 return i;
             }

         }
         return -1;
    }

    //delete the elements with its duplicate
    public static void duplicateRemove(String[]items) {

        List<String> list = Arrays.asList(items);
        Set<String> set = new HashSet<String>(list);

        System.out.print("Remove duplicate result: ");

        String[] result = new String[set.size()];
        set.toArray(result);
        for (String s : result) {
            System.out.print(s + ", ");

        }
    }


    //insert multiple same elements into the array
    private static String[] insertElements(String[] arr, String str)
    {
        int size = arr.length;

        String[] tmp = new String[size + 1];

        System.arraycopy(arr, 0, tmp, 0, size);

        tmp[size] = str;

        return tmp;
    }


    //remove the element
    public static String[] removeElements(String[] items) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Which element do you want to delete from the original array? Please"+
                " input the index, then show the new array.");

        int n = Integer.parseInt(sc.nextLine());

        String[] temp = new String[items.length - 1];
        if (n >= items.length) {
            System.out.println("Sorry, the computer cannot find this size");
            return items;
        }
        else{
            for (int i = n; i < items.length - 1; i++)
                items[i] = items[i + 1];

            for (int i = 0; i < items.length - 1; i++) {
                System.out.println(items[i]);
            }
        }
        return temp;
    }


}

