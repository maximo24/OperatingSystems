package Part2;
import java.util.ArrayList;

public class LeastFrequentlyUsed implements IPageReplacementStrategy {
    @Override
    /**
     * Returns page faults count using LRU page replacement algorithm
     * 
     * @param numOfFramesInPhysicalMemory: the physical memory capacity expressed in
     *                                     number of frames. Initially, all
     *                                     numOfFramesInPhysicalMemory are free
     * @param pages:                       the pages references that need to be
     *                                     allocated to the available frames
     */
    public int GetPageFaultsCount(int numOfFramesInPhysicalMemory, int[] pages) {
        // TODO implement Least Frequently Used (LRU) page replacement algorithm

        int pageFaultCount = 0;
        ArrayList<Integer> LRU = new ArrayList<>();

        for (int i = 0; i < pages.length; i++) {
            boolean found = false;
            for (int j = 0; j < LRU.size(); j++) {
                if (LRU.indexOf(pages[i]) != -1) {
                    found = true;
                    LRU.remove(Integer.valueOf(pages[i]));
                    LRU.add(pages[i]);
                }
            }
            if (!found) {
                pageFaultCount++;
                if (LRU.size() == numOfFramesInPhysicalMemory) {
                    LRU.remove(0);
                }
                LRU.add(pages[i]);
            }
        }
        return pageFaultCount;
    }
}
