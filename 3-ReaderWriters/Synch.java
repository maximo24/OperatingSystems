// This file defines class "Synch".  This class contains all the semaphores
// and variables needed to coordinate the instances of the Reader and Writer
// classes.  

import java.util.concurrent.*;

public class Synch {

  public static Semaphore mutex;
  public static Semaphore wrt;
  public static Semaphore read;
  public static int readcount = 0;
  // create counters for active and waiting readers/writers
  public static int active_read = 0;
  public static int wait_read = 0;
  public static int active_wrt = 0;
  public static int wait_wrt = 0;

}  // end of class "Synch"

