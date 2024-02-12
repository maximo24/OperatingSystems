import Part1.PagingStatisticalProcessor;
import Part2.PageReplacementSimulator;
import Part2.ReplacementStrategyEnum;
import Part2.FirstInFirstOut;
import Part2.LeastFrequentlyUsed;
import Part2.MostFrequentlyUsed;

/**
 * You don't have to change anything here.
 */
public class MainMethod {

    public static void Part1() {
        int physicalMemorySize, pageSize;
        double programSizeInBytes;
        int pageOffsetNumOfBits, part1Choice;
        String virtualAddress;
        System.out.println("Part 1 is activated!");
        System.out.println("Please enter the physical memory size in bytes: 2^");
        physicalMemorySize = Integer.parseInt(System.console().readLine());

        System.out.println("Please enter the page size in bytes: 2^");
        pageSize = Integer.parseInt(System.console().readLine());

        System.out.println("Please enter number of bits for the page offset of the virtual address: ");
        pageOffsetNumOfBits = Integer.parseInt(System.console().readLine());

        PagingStatisticalProcessor pagingStatisticalProcessor = new PagingStatisticalProcessor(
                physicalMemorySize, pageSize, pageOffsetNumOfBits);

        System.out.println(
                "Now enter your choice: \n\t- Enter 1 to print some statistics about the paging design. \n\t- Enter 2 to print some information about a given program specs\n\t- Enter 3 to print some information about a given virtual address. ");
        part1Choice = Integer.parseInt(System.console().readLine());
        switch (part1Choice) {
            case 1:
                pagingStatisticalProcessor.PrintStatisticalInformation();
            case 2:
                System.out.println("Please enter the size of the program in bytes: ");
                programSizeInBytes = Integer.parseInt(System.console().readLine());
                pagingStatisticalProcessor.PrintInformationAboutAProgram(programSizeInBytes);
                break;
            case 3:
                System.out.println("Please enter a valid virtual address: ");
                virtualAddress = System.console().readLine();
                pagingStatisticalProcessor.PrintInformationAboutAVirtualAddress(virtualAddress);
                break;
            default:
                break;
        }
    }

    public static void Part2() {
        int algorithmChoice, pageFaultsCount;
        System.out.println("Part 2 is activated!");
        PageReplacementSimulator pageReplacementSimulator = new PageReplacementSimulator();
        System.out.println(
                "Please choose the page replacement algorithm: \n\t- Enter 1 for FIFO.\n\t- Enter 2 for LRU. \n\t- Enter 3 for MFU\n\t- Enter 4 for all three algorithms");
        algorithmChoice = Integer.parseInt(System.console().readLine());
        // Get input from user
        pageReplacementSimulator.GetUserInput();
        switch (algorithmChoice) {
            case 1:
                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.FIFO);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for the selected algorithm is: " + pageFaultsCount);
                break;
            case 2:
                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.LRU);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for the selected algorithm is: " + pageFaultsCount);
                break;
            case 3:
                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.MFU);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for the selected algorithm is: " + pageFaultsCount);
                break;
            case 4:
                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.FIFO);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for FIFO algorithm is: " + pageFaultsCount);

                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.LRU);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for LRU algorithm is: " + pageFaultsCount);

                pageReplacementSimulator.SetReplacementStrategy(ReplacementStrategyEnum.MFU);
                pageFaultsCount = pageReplacementSimulator.GetPageFaultsCount();
                System.out.println("Page faults count for MFU algorithm is: " + pageFaultsCount);
        }
    }

    public static void main(String[] args) {
        int partChoice = 0;

        do {
            System.out.println(
                    "Please enter your choice: \n\t- Enter 1 for Part 1.\n\t- Enter 2 for Part 2.\n\t- and Enter 0 to exit. ");
            partChoice = Integer.parseInt(System.console().readLine());
            switch (partChoice) {
                case 1:
                    Part1();
                    break;
                case 2:
                    Part2();
                    break;
                default:
                    break;
            }
        } while (partChoice != 0);
        System.out.println("The end of the simulation...");
    }
}
