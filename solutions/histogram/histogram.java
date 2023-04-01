package histogram;

import java.io.*;
import java.util.*;

public class histogram {
    
    public static class MergeSort {

        public MergeSort() {
        }

        /** The method for sorting the data */
        public void mergeSort(int[] list) {
            if (list.length > 1) {
                // Merge sort the first half
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
                mergeSort(firstHalf);

                // Merge sort the second half
                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
                mergeSort(secondHalf);

                // Merge firstHalf with secondHalf into list
                merge(firstHalf, secondHalf, list);
            }
        }

        /** Merge two sorted lists */
        public void merge(int[] list1, int[] list2, int[] temp) {
            int current1 = 0; // Current index in list1
            int current2 = 0; // Current index in list2
            int current3 = 0; // Current index in temp

            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1] < list2[current2])
                    temp[current3++] = list1[current1++];
                else
                    temp[current3++] = list2[current2++];
            }

            while (current1 < list1.length)
                temp[current3++] = list1[current1++];

            while (current2 < list2.length)
                temp[current3++] = list2[current2++];
        }

    }

    public static void main(String[] args) throws IOException {
        MergeSort ms = new MergeSort();
        long startime = new Date().getTime();
        File dir = new File("C:\\Users\\USER\\IdeaProjects\\Batman-Team\\solutions\\histogram\\cases");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Scanner sc = null;
                try {
                    sc = new Scanner(new FileInputStream(file));
                    while (sc.hasNextLine()) {
                        String str1 = sc.nextLine();
                        String[] num1 = str1.split(" ");
                        int N = Integer.parseInt(num1[0]);
                        int M = Integer.parseInt(num1[1]);
                        String str2 = sc.nextLine();
                        String[] num2 = str2.split(" ");
                        int[] data = new int[N];
                        for (int i = 0; i < N; i++) {
                            data[i] = Integer.parseInt(num2[i]);
                        }
//                        System.out.println(N);
//                        System.out.println(M);
                        ms.mergeSort(data);
//                        for (int i=1; i<data.length; i++) {
//                            for (int j=0; j<data.length-1; j++) {
//                                if (data[j] > data[j+1]) {
//                                    int hold = data[j];
//                                    data[j] = data[j+1];
//                                    data[j+1] = hold;
//                                }
//                            }
//                        }
//                        for (int i=0; i<data.length; i++) {
//                            System.out.println(data[i]);
//                        }
                        int width = (data[N - 1] - data[0]) / M;
                        int[] interval = new int[M + 1];
                        interval[0] = data[0];
                        for (int i = 1; i < interval.length; i++) {
                            interval[i] = interval[i - 1] + width;
                        }
                        for (int i = 0; i < interval.length; i++) {
                            System.out.print(interval[i] + " ");
                        }
                        System.out.println();
                        int[] count = new int[M];
                        for (int i = 0; i < M; i++) {
                            for (int j = 0; j < data.length; j++) {
                                if (i == M - 1) {
                                    if (data[j] >= interval[i] && data[j] <= interval[i + 1]) {
                                        count[i]++;
                                    }
                                } else {
                                    if (data[j] >= interval[i] && data[j] < interval[i + 1]) {
                                        count[i]++;
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < count.length; i++) {
                            System.out.print(count[i] + " ");
                        }
                        System.out.println();
                    }
                } catch (IOException e) {
                    System.out.println("File got some problems.");
                } finally {
                    if (sc != null)
                        sc.close();
                }
            }
            long Endtime = new Date().getTime();
            long difference = Endtime - startime;
            System.out.println("The execution time is " + difference + "ms");
        }
    }
}

//}
