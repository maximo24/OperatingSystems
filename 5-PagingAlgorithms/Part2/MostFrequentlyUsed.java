package Part2;
import java.util.ArrayList;

public class MostFrequentlyUsed implements IPageReplacementStrategy {
    @Override
    /**
     * Returns page faults count using MFU page replacement algorithm
     * 
     * @param numOfFramesInPhysicalMemory: the physical memory capacity expressed in
     *                                     number of frames. Initially, all
     *                                     numOfFramesInPhysicalMemory are free
     * @param pages:                       the pages references that need to be
     *                                     allocated to the available frames
     */
    public int GetPageFaultsCount(int numOfFramesInPhysicalMemory, int[] pages) {
        // implementing Most Frequently Used (MFU) page replacement algorithm

        int pageFaultCount = 0;
        int [] frequency = new int[numOfFramesInPhysicalMemory];
        int [] frame = new int[numOfFramesInPhysicalMemory];

        for (int h = 0; h < frame.length; h++) {
            frequency[h] = 0;
            frame[h] = -1;
        }

        for(int i = 0; i < pages.length; i++) {
            boolean found = false;
            int max = 0;
            for(int j = 0; j < numOfFramesInPhysicalMemory; j++) {
                if (frame[j] == pages[i]){
                    frequency[j]++;
                    found = true;
                    break;
                }
            }
            if(!found){
                for(int k = 0; k < numOfFramesInPhysicalMemory; k++) {
                    if(frame[k] == -1) {
                        pageFaultCount++;
                        frame[k] = pages[i];
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                pageFaultCount++;
                for(int l = 1; l < numOfFramesInPhysicalMemory; l++) {
                    if (frequency[l] > frequency[max]) {
                        max = l;
                    }
                }
                frame[max] = pages[i];
                frequency[max] = 0;
            }
        }
        return pageFaultCount;
    }

}
