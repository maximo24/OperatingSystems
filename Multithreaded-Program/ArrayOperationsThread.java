public class ArrayOperationsThread extends Thread {
    private int currentOperation = 0;
    private int[] currentArray;
    private double computedValue = 0;
    private int searchValue = 0;
    private int threadID = -1;

    ArrayOperationsThread(int currentOperation, int[] currentArray, int searchValue) {
        this.currentOperation = currentOperation;
        this.currentArray = currentArray;
        this.searchValue = searchValue;
        this.threadID = ++MainProgram.threadsCounter;
    }

    public double getMostRecentComputedValue() {
        return this.computedValue;
    }

    public double computeArraySum() {
        for (int i = 0; i < currentArray.length; i++) {
            computedValue = computedValue + currentArray[i];
        }
        return this.computedValue;
    }

    public double computeArrayAverage() { // got wrong value when trying to make a function call to computeArraySum()
        int sum = 0;
        for (int i = 0; i < currentArray.length; i++) {
            sum = sum + currentArray[i];
        }
        computedValue = sum / currentArray.length;
        return this.computedValue;
    }

    public double computeArrayMaximum() {
        int max = currentArray[0];
        for (int i = 1; i < currentArray.length; i++) {
            if (currentArray[i] > max) {
                max = currentArray[i];
            }
            computedValue = max;
        }
        return this.computedValue;
    }

    public boolean searchForValueInArray() {
        // TODO: Search for "searchValue" inside the array "currentArray". Return true
        // if value found and false otherwise
        int i = 0;
        while (i < currentArray.length) {
            if (currentArray[i] == searchValue) {
                return true;
            }
            else {
                i = i + 1;
            }
        }
        return false;
    }

    @Override
    public void run() {
        switch (this.currentOperation) {
            // Compute the sum of array if currentOperation == 1
            case 1:
                this.computedValue = this.computeArraySum();
                break;
            // Compute the average of array if currentOperation == 2
            case 2:
                this.computedValue = this.computeArrayAverage();
                break;
                // Compute the maximum of the array if currentOperation == 3
            case 3:
                this.computedValue = this.computeArrayMaximum();
                break;
                // Search for a value inside the array if currentOperation == 4
            case 4:
                this.computedValue = this.searchForValueInArray() == true ? 1 : 0;// if the return value is true, assign
                break;                                                            // 1 to "computedValue" and 0
                                                                                  // otherwise

            default:
                System.out.println("Thread " + this.threadID + ": error occured! the operation value "
                        + this.currentOperation + " is not supported");
        }
    }
}
