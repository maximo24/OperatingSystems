Process Communication Issue:

1. 
The program is only outputting the total for pid != 0, so only half of the process is being outputted... A(int y) and B(int y) results need to be combined

2.
I fixed the issue by waiting for the child process to complete and created a new variable called totalFromChild that took the utilized the WEXITSTATUS function
and under the child process wrote exit(total) which then sent the total from the child process to the parent process and combined the total + the total from child

3.
When n becomes 26, the returned sum becomes incorrect. This is because WEXITSTATUS return value is limited to 8-bits or one byte so after n reaches 26, the value of 
totalFromChild exceeds 255 and counts from 0. 

4.
Switching the values of A() and B() the value of n (when the returned sum becomes incorrect) is 46


Race-Condition Issue:

1.
Output 1 = 6192
Output 2 = 6288
Output 3 = 6412
Output 4 = 6365
Output 5 = 6304
Output 6 = 6326
- The child processes are all trying to perform their operations at the same time which is causing the program to not work correctly.
  The operations have to be done in the proper sequence in order for the program to perform correctly

2. 
The value of n is 5000. This can happen if fork() fails for all the child processes where the final value of n will result in 5000.
