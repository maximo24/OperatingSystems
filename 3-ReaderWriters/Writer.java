// This file defines class "writer".

// This code uses
//      class Semaphore, from the java.util.concurrent package in Java 5.0 which defines the behaviour of a 
//                           semaphore, including acquire and release operations.
//      class Synch, which defines the semaphores and variables
//                   needed for synchronizing the readers and writers.
//      class RandomSleep, which defines the doSleep method.


public class Writer extends Thread {
  int myName;  // The variable myName stores the name of this thread.
               // It is initialized in the constructor for class Reader.

  RandomSleep rSleep;  // rSleep can hold an instance of class RandomSleep.



  // This is the constructor for class Writer.  It has an integer parameter,
  // which is the name that is given to this thread.
  public Writer(int name) {
    myName = name;  // copy the parameter value to local variable "MyName"
    rSleep = new RandomSleep();   // Create and instance of RandomSleep.
  }  // end of the constructor for class "Writer"



  public void run () {
    for (int I = 0;  I < 5; I++) {

      // Get permission to write
      System.out.println("Writer " + myName + " wants to write");
      try{
        Synch.mutex.acquire(); // get access to shared variables
      }
      catch(Exception e){}

      // Once a reader or writer becomes active, it is allowed to finish.
      // There is no way to interrupt active readers when a writer arrives:
      // the writer has to wait until all these readers finish.
      if (Synch.active_read > 0 || Synch.active_wrt == 1) {
          Synch.wait_wrt++; // gets put to wait
      }
      // if neither are active, release wrt semaphore and set active to 1
      else {
          Synch.wrt.release();
          Synch.active_wrt = 1;
      }

      Synch.mutex.release(); // done modifying shared variables

      // tries to write
      try{
          Synch.wrt.acquire();
      }
      catch(Exception e){}

      // Simulate the time taken by writing.
      System.out.println("Writer " + myName + " is now writing");
      rSleep.doSleep(1, 200);


      // We're done writing.  Signal the "wrt" semaphore.  If a Reader thread
      // was waiting on "wrt", that reader starts, and allows other waiting
      // readers (who are waiting on "mutex") to start as well.  If a Writer
      // thread was waiting on "wrt", then that writer goes next.  If no one
      // was waiting on wrt, then wrt has the value 1, so the next future
      // reader or writer can go without waiting.
      System.out.println("Writer " + myName + " is finished writing");
      // set active wrt back to 0 after write operation performs
      try {
          Synch.mutex.acquire();
      }
      catch(Exception e){}

      Synch.active_wrt = 0;

      // check if their are more write requests waiting
      if (Synch.wait_wrt > 0) {
          Synch.wrt.release();
          Synch.active_wrt = 1;
          Synch.wait_wrt--;
      }
      // no more write requests? can perform as many read requests in waiting since that point
      else if (Synch.wait_read > 0){
          for (int i = Synch.wait_read; i > 0; i--) {
              Synch.read.release();
              Synch.active_read++;
              Synch.wait_read--;
          }
      }
      Synch.mutex.release();

      // Simulate "doing something else"
      rSleep.doSleep(1, 1000);
    } // end of "for" loop
  }  // end of "run" method
}  // end of class "Writer"

