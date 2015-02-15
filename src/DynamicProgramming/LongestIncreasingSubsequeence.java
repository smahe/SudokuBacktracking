package DynamicProgramming;

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequeence {
        private static int a[];
        public static void init(int x[],int n){a = new int[n]; a=x;}

        public static void increasingsubs(){
            int L[] = new int[a.length]; // Max length sequence before 'i'th element
            L[0] = 1;
            for(int i = 1;i<a.length;i++){
                int max = 0;    // assuming no such element exists before i
                for(int j = 0;j<i;j++){
                    if(a[j]<a[i] && L[j]>max){  // finding the longest increasing subsequece till i
                        max = L[j];
                    }
                }
                L[i] = 1 + max; //max gives longest increasing subsequence till i-1 (+1) forincluding i if a[j]<a[j] j<i
            }
            int maxlength = 0;

            for(int k = 0;k<L.length;k++){
                if(maxlength < L[k])
                    maxlength = L[k];
            }
            System.out.println(maxlength); //Time : O(n^2)
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(new InputStreamReader(System.in));
            int n = in.nextInt();
            int a[] = new int[n];
            for(int i = 0;i<n;i++){
                a[i] = in.nextInt();
            }
            LongestIncreasingSubsequeence.init(a,n);
            LongestIncreasingSubsequeence.increasingsubs();
        }
    }

