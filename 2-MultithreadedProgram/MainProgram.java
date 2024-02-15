public class MainProgram {

    // This is the challenge value, you can modify the value if you want

    // The main method, here starts the execution
    public static int threadsCounter = 0;

    public static void main(String[] args) {

        System.out.println("run");
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        // split the array into 3 slices, then create a thread for each slice.
        int[] slice1 = new int[(int) Math.ceil(arr.length / 3) + 1];
        int[] slice2 = new int[(int) Math.ceil(arr.length / 3) + 1];
        int[] slice3 = new int[(int) Math.ceil(arr.length / 3) + 1];
        int i = 0;
        for (; i < Math.ceil(arr.length / 3) + 1; ++i) {
            slice1[i] = arr[i];
        }
        for (int j = 0; i < Math.ceil(arr.length / 3) * 2 + 2; ++i, ++j) {
            slice2[j] = arr[i];
        }
        for (int j = 0; i < arr.length; ++i, ++j) {
            slice3[j] = arr[i];
        }
        ArrayOperationsThread t1_sum = new ArrayOperationsThread(1, slice1, -1);// the third parameter is a dummy value
                                                                                // because here we don't want to search
                                                                                // for
                                                                                // a value.
        ArrayOperationsThread t2_sum = new ArrayOperationsThread(1, slice2, -1);
        ArrayOperationsThread t3_sum = new ArrayOperationsThread(1, slice3, -1);

        // Start all threads.
        t1_sum.start();
        t2_sum.start();
        t3_sum.start();

        try {

            // Wait until the three threads have finished using join() function.
            t1_sum.join();
            t2_sum.join();
            t3_sum.join();
            double sum = t1_sum.getMostRecentComputedValue() + t2_sum.getMostRecentComputedValue()
                    + t3_sum.getMostRecentComputedValue();
            System.out.println("The parallel sum of the array = " + sum);
        } catch (InterruptedException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        int[] slice01 = new int[(int) Math.ceil(arr.length / 2) + 1];
        int[] slice02 = new int[(int) Math.ceil(arr.length / 2) + 1];
        int x = 0;
        for (; x < Math.ceil(arr.length / 2); x++) {
            slice01[x] = arr[x];
        }
        for (int y = 0; x < arr.length; x++, y++) {
            slice02[y] = arr[x];
        }

        ArrayOperationsThread t1_average = new ArrayOperationsThread(2, slice01, -1);
        ArrayOperationsThread t2_average = new ArrayOperationsThread(2, slice02, -1);

        // Start all threads
        t1_average.start();
        t2_average.start();

        // compute and print the average of the array

        try {
            t1_average.join();
            t2_average.join();
            double average = (t1_average.getMostRecentComputedValue() + t2_average.getMostRecentComputedValue()) / 2;
            System.out.println("The parallel average of the array = " + average);
        }
        catch (InterruptedException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        ArrayOperationsThread t1_max = new ArrayOperationsThread(3, slice01, -1);
        ArrayOperationsThread t2_max = new ArrayOperationsThread(3, slice02, -1);

        // Start all threads
        t1_max.start();
        t2_max.start();

        // compute and print the maximum number in the array

        try {
            t1_max.join();
            t2_max.join();
            double max1 = t1_max.getMostRecentComputedValue();
            double max2 = t2_max.getMostRecentComputedValue();
            System.out.println("The max values are " + max1 + " and " + max2);
        }
        catch (InterruptedException e) {
             // Auto-generated catch block
            e.printStackTrace();
        }

        ArrayOperationsThread t1_find = new ArrayOperationsThread(4, slice01, 16);
        ArrayOperationsThread t2_find = new ArrayOperationsThread(4, slice02, 16);

        // Start all threads
        t1_find.start();
        t2_find.start();

        // search for an arbitrary value inside the array.

        boolean stop = false;
        while (!stop) {
            if (t1_find.getMostRecentComputedValue() == 1) {
                System.out.println("found");
                t2_find.stop();
                stop = true;
            }
            else if (t2_find.getMostRecentComputedValue() == 1) {
                System.out.println("found");
                t1_find.stop();
                stop = true;
            }
            else if (!(t1_find.isAlive()) && !(t2_find.isAlive())) {
                System.out.println("not found");
                stop = true;
            }
        }



        }


}
