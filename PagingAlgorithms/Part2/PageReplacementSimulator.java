package Part2;

import java.util.InputMismatchException;

public class PageReplacementSimulator {
    private IPageReplacementStrategy pageReplacementStrategy;
    private int[] pages;
    private int numOfFramesInPhysicalMemory;
    private int numOfPages;
    private ReplacementStrategyEnum currentReplacementStrategy;

    public PageReplacementSimulator(ReplacementStrategyEnum currentReplacementStrategy) {
        super();
        this.SetReplacementStrategy(currentReplacementStrategy);
    }

    public PageReplacementSimulator() {
        super();
    }

    public void SetReplacementStrategy(ReplacementStrategyEnum currentReplacementStrategy) {
        this.currentReplacementStrategy = currentReplacementStrategy;
        switch (this.currentReplacementStrategy) {
            case FIFO:
                this.pageReplacementStrategy = new FirstInFirstOut();
                break;
            case LRU:
                this.pageReplacementStrategy = new LeastFrequentlyUsed();
                break;
            case MFU:
                this.pageReplacementStrategy = new MostFrequentlyUsed();
                break;
            default:
                this.pageReplacementStrategy = null;
                throw new TypeNotPresentException("The type you provided is not supported!", null);
        }
    }

    public int GetPageFaultsCount() {
        return this.pageReplacementStrategy.GetPageFaultsCount(this.numOfFramesInPhysicalMemory, this.pages);
    }

    public void GetUserInput() {
        System.out.println("Please enter the number of frames (max of 10): ");
        this.numOfFramesInPhysicalMemory = Integer.parseInt(System.console().readLine());
        if (this.numOfFramesInPhysicalMemory > 10 || this.numOfFramesInPhysicalMemory < 0)
            throw new InputMismatchException("The number of frames must be between 1 and 10 inclusive!");

        System.out.println("Please enter the number of pages you want to process (max of 50): ");
        this.numOfPages = Integer.parseInt(System.console().readLine());
        if (this.numOfFramesInPhysicalMemory > 10 || this.numOfFramesInPhysicalMemory < 0)
            throw new InputMismatchException("The number of pages must be between 1 and 50 inclusive!");

        this.pages = new int[this.numOfPages];
        for (int i = 0; i < this.numOfPages; ++i) {
            int pageNum = Integer.parseInt(System.console().readLine());
            if (pageNum < 0 || pageNum > this.numOfPages)
                throw new InputMismatchException(
                        "The number of pages must be between 0 and " + this.numOfPages + " inclusive!");
            this.pages[i] = pageNum;
        }
    }
}
