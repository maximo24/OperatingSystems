ReadMe


Part 1:


For mine and the users convenience I switched the longs to be an int representation of the exponent.


For numFrames, because the division of exponents with the same base is to subtract the exponent, I subtracted 
physicalMemorySize by pageSize

To get the number of paging levels I divided the exponent value by 8 to get the gb representation and then loop 
divided by 2 to get the exponent value with base 2, I then took pageTableSize by adding the exponent to the numFrames
exponent and looped the pagingLevels counter until the pageTableSize was less than the numFrames which then successfully
gave the number of paging levels

To get the bits required I simply took the numFrames exponent and divided it by the pagingLevels

To get the byteSize all I needed to do was simply add the bitsRequired to my counter variable that was used when looping
the gb integer to find the exponent of the total gb from base 2

To get the number of pages required for the program I divided the programSizeInBytes by the page size which I computed
2^pageSize to get... I then checked if the division of these two variables would lead to a decimal result and if it did 
I added another page to allocate the remainder memory that would not take up an entire page.

To print the information about a virtual address I used the information collected from the user to understand how the 
virtual address would be split. Understanding the sections and offset I split the binary string taken by the user into
the correct parts by indexing from start and end variables and if the string array was at its last value, the substring 
indexes changed by the offset

Having this array of split binary sections I made 2 more loops. The first loop went through the indexes of the tableEntry
array and the second loop looped through each char in the string at the given index which I then used the proper equation
for converting binary to decimal and printed the decimal value for each section.

 

Part 2:

For FIFO I initialized a page fault counter at the beginning as well as making an arraylist. I then looped through the 
page references and created another loop which looped through the frames to check if the reference value was found. If 
the reference value was not found I incremented the page fault counter and then checked if the array list size was the same
as the number of frames in physical memory and if it was, I removed the frame value that had been added the earliest.

For LRU I did pretty much the exact same thing as the FIFO program except when a page reference was found, I took the value 
out of the frame and put it back in so when it comes time to replace a value, the program takes the earliest referenced value
out because I'm putting the referenced frames back in as new.

For MFU I had to do things differently, I created two integer arrays: frequency was to calculate how many times a frame at
the specific index has been hit and frame is the page references held in the frame. I started by initializing every value in 
frequency to 0 as nothing has been referenced/used yet and initialize all the values in the frame as -1 to act as null/empty.
I then had a for loop over the rest of the program to go through all the page references and a for loop to check if the page 
reference was found in a frame and if it was, the frequency at the same index as the found frame got incremented. I then added
a for loop when there wasnt a value found and make sure that the frames are not empty... If the frames were empty, add to the 
fault count and assign the page reference to the empty frame. If all the frames were being used up however, add to the page 
fault count and go through the frequency array to check for the index with the highest value. The index of the highest value 
then gets the frame of same index replacced with the new page reference and frequency value from that index gets sent back to 
zero as it has not been referenced/used yet.


