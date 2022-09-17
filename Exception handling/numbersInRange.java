import java.util.Scanner;

public class numbersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] nums = scanner.nextLine().split(" ");
        int num1 = Integer.parseInt(nums[0]);
        int num2=Integer.parseInt(nums[1]);
        int[] range = new int [2];
        if(num1>num2){range [0]=num2;range [1] = num1;}
        else{range[0]=num1;range[1]=num2;}
        System.out.println("Range: ["+range[0]+"..."+range[1]+"]");
        String str = scanner.nextLine();

        while(true){

            try{
                int number = Integer.parseInt(str);
                if(number>=range[0]&&number<=range[1]){
                    System.out.println("Valid number: "+number);break;}
                else{
                    System.out.println("Invalid number: "+number);}}
            catch(NumberFormatException num){
                System.out.println("Invalid number: "+str);

            }


            str = scanner.nextLine();

        }
    }
}