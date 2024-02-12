CISC324 Lab 4 - ReadMe



Synch.java:

Created a semaphore for eastbound and westbound

Kept the mutex semaphore as it was needed when modifying

eastcount - counter to keep track of how many cars waiting at eastbound

westcount - counter to keep track of how many cars waiting at westbound

eastgreen - boolean to check if the eastbound light is green or red

westgreen - boolean to check if the westbound light is green or red



TrafficSystem.java:

Created a new class in order to make a traffic light system to control the flow of traffic

I began by acquiring the mutex semaphore and setting the eastbound light to green (both lights red by default) and
creating an int called "greentime" which sets to 500 for the units of time the light will be green for

I then created a while loop that checks while the light is still green and there are cars waiting in the eastbound
queue, I release the eastbound semaphore allowing the cars in the queue to cross and decrement the eastcount counter
(by acquiring the mutex)

I also call Synch.timeSim.doSleep(5) with each iteration as it stops the cars in the queue from going all at once and
then decrement the greentime variable by 5 each iteration to keep track of how much time is left until the light changes

Outside the loop I call Synch.timeSim.doSleep(greentime) to let the rest of the time the light is green pass

After this by acquiring the mutex again, I set both lights to red for 100 time units which is how much time it takes to
cross the bridge in case there are still cars crossing

Once all this is done, I repeat the exact same process but for the westbound semaphore this time



MainMethod.java:

Added the eastbound and westbound semaphores with no initial permits as they become available with the conditions of
the code

Created an instance of the TrafficSystem



Car.java:

Added the synchronization to make cars wait if the eastbound or westbound light is red

Checks if the boolean representing if the light is green is set to false or if there are cars ahead in the queue

if either is true, print statement stating car is waiting at its respective location, adding to the counter of cars in
queue, and acquiring the permit for the semaphore so the car doesn't cross until the semaphore is released

