/*
  CSC22100-R  Assignment 1 - Hello World
  Shirong Zheng
  09/24/2016
 */

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Please enter your first name:");
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        System.out.print("Hello,"+name+", Welcome to Java!" +'\n');

        System.out.println("Please choose the 1-4 for math operation" +
                "(1:Add, 2:Subtract, 3:Divide, 4:Multiply )");
        int sign;
        Scanner s=new Scanner(System.in);
        sign=s.nextInt();
        int number1;
        int number2;
        System.out.println("Please insert two numbers:");
        Scanner in=new Scanner(System.in);
        number1=in.nextInt();
        number2=in.nextInt();
        System.out.println("The first number is "+number1+ ", and the second number is " +number2);
        System.out.println("The result is:");
        switch(sign){
            case 1:
                System.out.print(number1 + number2);
                break;
            case 2:
                System.out.print(number1 - number2);
                break;
            case 3:
                System.out.print(number1 / number2);
                break;
            case 4:
                System.out.print(number1 * number2);
                break;
            default:
                System.out.print("The computer cannot understand your command, please try it again!");
                break;

        }

        System.out.println('\n'+"Please enter a number in string(ex.123): ");
        String n=scanner.nextLine();
        double nconverted=Double.parseDouble(n);
        System.out.println("The double variable of this number is: " +nconverted);

    }


}


