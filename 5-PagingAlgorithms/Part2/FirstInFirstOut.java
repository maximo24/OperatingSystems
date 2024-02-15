package Part2;
import java.util.ArrayList;

public class FirstInFirstOut implements IPageReplacementStrategy {
    @Override
    /**
     * Returns page faults count using FIFO page replacement algorithm
     * 
     * @param numOfFramesInPhysicalMemory: the physical memory capacity expressed in
     *                                     number of frames. Initially, all
     *                                     numOfFramesInPhysicalMemory are free
     * @param pages:                       the pages references that need to be
     *                                     allocated to the available frames
     */
    public int GetPageFaultsCount(int numOfFramesInPhysicalMemory, int[] pages) {
        // implementing First In First Out (FIFO) page replacement algorithm

        int pageFaultCount = 0;
        ArrayList<Integer> FIFO = new ArrayList<>();

        for (int i = 0; i < pages.length; i++) {
            boolean found = false;
            for (int j = 0; j < FIFO.size(); j++) {
                if (FIFO.indexOf(pages[i]) != -1) {
                    found = true;
                }
            }
            if (!found) {
                pageFaultCount++;
                if (FIFO.size() == numOfFramesInPhysicalMemory) {
                    FIFO.remove(0);
                }
                FIFO.add(pages[i]);
            }
        }
        return pageFaultCount;
    }
}

