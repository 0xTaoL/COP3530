import java.util.*;
import java.io.*;

public class Project2 {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        System.out.print("1. Merge sort\n2. Bubble sort\n3. Insertion sort\n");
        int choice = input.nextInt();
        input.nextLine();
        System.out.print("File to sort:\n");
        String filename = input.nextLine();
        System.out.print("# of items: \n");
        int size = input.nextInt();
        int[] list = new int[size];

        for (int x = 0; x < 1; x++) {
            try {

                File file = new File(filename);
                Scanner scanner = new Scanner(file);
                for (int i = 0; i < size; i++) {
                    list[i] = scanner.nextInt();
                }
            } catch (Exception nonInt) {
                System.err.println("Non-integer found, exiting");
                System.exit(0);
            }

            if (choice == 1) {
                long startTime = System.nanoTime();
                mergeSort(list, 0, list.length - 1);
                long elapsedTime = System.nanoTime() - startTime;
                System.out.println(elapsedTime);
                System.out.println(Arrays.toString(list));

            }
            else if (choice == 2) {
                long startTime = System.nanoTime();
                bubbleSort(list, size);
                long elapsedTime = System.nanoTime() - startTime;
                System.out.println(elapsedTime);
          		System.out.println(Arrays.toString(list));
            }
            else if (choice == 3) {
                long startTime = System.nanoTime();
                insertionSort(list);
                long elapsedTime = System.nanoTime() - startTime;
                System.out.println(elapsedTime);
                System.out.println(Arrays.toString(list));
            }
        }
    }

    private static void mergeSort(int[] list, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(list, low, mid);
            mergeSort(list, mid + 1, high);
            merge(list, low, mid, high);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void bubbleSort(int[] arr, int n) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }

    }

    private static void insertionSort(int[] arr){
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
}