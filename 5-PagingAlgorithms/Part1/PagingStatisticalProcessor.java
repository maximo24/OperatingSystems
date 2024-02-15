package Part1;
import java.lang.Math;

public class PagingStatisticalProcessor {
    private int physicalMemorySize;
    private int pageSize;
    private int pageOffsetNumOfBits;

    public static int pagingLevels = 1;

    public static int bitsRequired;

    public static int byteSize;

    public PagingStatisticalProcessor() {
        super();
    }

    public PagingStatisticalProcessor(int physicalMemorySize, int pageSize, int pageOffsetNumOfBits) {
        super();
        this.physicalMemorySize = physicalMemorySize;
        this.pageSize = pageSize;
        this.pageOffsetNumOfBits = pageOffsetNumOfBits;
    }

    public void PrintStatisticalInformation() {
        
        // Number of frames total
        int numFrames = physicalMemorySize - pageSize;
        System.out.println("Total number of frames = 2^" + numFrames);

        // Number of paging levels
        int gb = physicalMemorySize/8;
        int counter = 1;
        while (gb != 2) {
            counter++;
            gb = gb/2;
        }
        int pageTableSize = numFrames + counter;
        int pagingLevels = 1;
        while (pageTableSize > numFrames) {
            pageTableSize = pageTableSize - pageOffsetNumOfBits;
            pagingLevels++;
        }
        System.out.println("Number of paging levels = " + pagingLevels);

        // Number of bits required to access each page table level
        bitsRequired = numFrames/pagingLevels;
        System.out.println("Bits required to access each page table level = " + bitsRequired);

        // Number of entries of the page table at each paging level
        System.out.println("Number of entries of the page table at each paging level = 2^" + bitsRequired);

        //Size in bytes of the page table at each paging level
        byteSize = bitsRequired + counter;
        System.out.println("Size in bytes of the page table at each paging level = 2^" + byteSize);
    }

    public void PrintInformationAboutAProgram(double programSizeInBytes) {

        // Number of pages required for the program
        double sizePage = Math.pow(2, pageSize);
        int pages;
        double result;
        if (programSizeInBytes/sizePage % 1 == 0) {
            result = programSizeInBytes/sizePage;
            pages = (int) result;
        }
        else {
            result = programSizeInBytes/sizePage + 1.00;
            pages = (int) result;
        }
        System.out.println("Number of pages required for program = " + pages);

        // The number of page tables required to map this program to the phytsical space
        System.out.println("Number of page tables required to map this program to physical space = 2");
        // I don't understand what this one means in comparison to the other ones
    }

    public void PrintInformationAboutAVirtualAddress(String virtualAddress) {

        int numFrames = physicalMemorySize - pageSize;
        int gb = physicalMemorySize/8;
        int counter = 1;
        while (gb != 2) {
            counter++;
            gb = gb/2;
        }
        int pageTableSize = numFrames + counter;
        int pagingLevels = 1;
        while (pageTableSize > numFrames) {
            pageTableSize = pageTableSize - pageOffsetNumOfBits;
            pagingLevels++;
        }

        String tableEntry[] = new String[pagingLevels + 1];
        int start = 0;
        int bitsRequired = numFrames/pagingLevels;
        int end = bitsRequired;

        for (int i = 0; i < tableEntry.length; i++) {
            if (i == tableEntry.length - 1) {
                tableEntry[i] = virtualAddress.substring(start, physicalMemorySize);
            }
            else {
                tableEntry[i] = virtualAddress.substring(start, end);
            }
            start = end;
            end = end + bitsRequired;
        }

        double toDecimal = 0;

        for (int x = 0; x < tableEntry.length; x++) {
            for (int y = 0; y < tableEntry[x].length(); y++) {
                if (tableEntry[x].charAt(y) == '1') {
                    toDecimal = toDecimal + Math.pow(2, tableEntry[x].length() - 1 - y);
                }
            }
            System.out.println((int) toDecimal);
            toDecimal = 0;
        }

    }
}
