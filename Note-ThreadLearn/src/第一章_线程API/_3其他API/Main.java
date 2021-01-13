package _3其他API;

import java.util.Arrays;
import java.util.Scanner;

/**
 * create by cairuojin on 2018/11/28
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.next();
        }
        Arrays.sort(arr);
        String data = "";
        for (int i = n; i > 0; i--){
            data+= arr[i-1];
        }
        System.out.println(data);
    }
}
