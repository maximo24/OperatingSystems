
public class TrafficSystem extends Thread {

    public TrafficSystem() {
        Synch.timeSim.threadStart();
    }

    public void run () {
        for (int I=1; I<= 5; I++) {
            try{
                Synch.mutex.acquire(); // get access to shared variables
            }
            catch(Exception e){}

            // set eastbound light green
            Synch.eastgreen = true;
            System.out.println("At time " + Synch.timeSim.curTime() + " eastbound light is now green\n");

            // set the time a light is green for
            int greentime = 500;

            // while the light is still green and there are cars in eastbound queue
            // cars start to cross with a pause of 5 units between each other
            while(greentime > 0 && Synch.eastcount > 0) {
                // release the eastbound semaphore
                Synch.eastbound.release();
                Synch.mutex.release();
                // simulate the delay between the next car in the queue
                Synch.timeSim.doSleep(5);

                try{
                    Synch.mutex.acquire();
                }
                catch(Exception e){}
                Synch.eastcount--;
                // keep track of time until light turns red
                greentime = greentime - 5;
            }
            Synch.mutex.release();
            // simulate time left until red
            Synch.timeSim.doSleep(greentime);

            try{
                Synch.mutex.acquire();
            }
            catch(Exception e){}
            // turn both lights red
            Synch.westgreen = false;
            Synch.eastgreen = false;
            System.out.println("At time " + Synch.timeSim.curTime() + " both lights are now red\n");
            // simulate time both lights are red
            Synch.mutex.release();
            Synch.timeSim.doSleep(100);

            try{
                Synch.mutex.acquire();
            }
            catch(Exception e){}
            // set westbound light green
            Synch.westgreen = true;
            System.out.println("At time " + Synch.timeSim.curTime() + " westbound light is now green\n");

            greentime = 500;

            while(greentime > 0 && Synch.westcount > 0) {
                Synch.westbound.release();
                Synch.mutex.release();
                Synch.timeSim.doSleep(5);
                try{
                    Synch.mutex.acquire();
                }
                catch(Exception e){}
                Synch.westcount--;
                greentime = greentime - 5;
            }
            Synch.mutex.release();
            Synch.timeSim.doSleep(greentime);

            try{
                Synch.mutex.acquire();
            }
            catch(Exception e){}
            // set both lights red
            Synch.westgreen = false;
            Synch.eastgreen = false;
            // simulate time both lights are red
            System.out.println("At time " + Synch.timeSim.curTime() + " both lights are now red\n");
            Synch.mutex.release();
            Synch.timeSim.doSleep(100);

        } // end of "for" loop
    } // end of "run" method
} // end of class "TrafficSystem"