import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;

public class Main {

    public static void printLine(String line, boolean usePrintln) {
        if (usePrintln) {
            System.out.println(line);
        } else {
            System.out.print(line);
        }
    }

    public static void exec(String program) {
        try {
            String end = program.substring(0, program.length() - 5);
          
            Process compilerProcess = Runtime.getRuntime().exec("javac proj/" + end + ".java");
            compilerProcess.waitFor(); 

            ProcessBuilder processBuilder = new ProcessBuilder("java", "proj/"+end);

            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT); 

            Process runtimeProcess = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                printLine(line, true);
            }

            runtimeProcess.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

    public static void main(String[] args) {

      File dir = new File("/home/runner/pirmais/src/main/java/proj");

      System.out.println("");

      Scanner input = new Scanner(System.in);
           
      if (dir.exists() && dir.isDirectory()) {
        File[] directoryListing = dir.listFiles();

          if (true) {
            System.out.print("\033[H\033[2J");  
            System.out.print("\033[H\033[2J");  
            System.out.flush();

              System.out.println("  ____  _     _ _   _               _                    _                           _               ");
              System.out.println(" / ___|| |__ (_) |_| |_ _   _      | | __ ___   ____ _  | |    __ _ _   _ _ __   ___| |__   ___ _ __ ");
              System.out.println(" \\___ \\| '_ \\| | __| __| | | |  _  | |/ _` \\ \\ / / _` | | |   / _` | | | | '_ \\ / __| '_ \\ / _ \\ '__|");
              System.out.println("  ___) | | | | | |_| |_| |_| | | |_| | (_| |\\ V / (_| | | |__| (_| | |_| | | | | (__| | | |  __/ |   ");
              System.out.println(" |____/|_| |_|_|\\__|\\__|\\__, |  \\___/ \\__,_| \\_/ \\__,_| |_____\\__,_|\\__,_|_| |_|\\___|_| |_|\\___|_|   ");
              System.out.println("                        |___/                                                                        ");   
              System.out.println(" MADE BY SIGMATRON™ LIEPTONS™ SOFTWARE™ INCORPORATED™\n");

            int i = 1;
            System.out.println("0 | EXIT");
            if (directoryListing != null) {
              for (File child : directoryListing) {
                  System.out.println(i+" | "+child.getName());
                  i ++;
              }
                int target = -1;
      
                    System.out.print("\nIevadi programas numuru kuru tu gribi palaist: ");
                    try {
                        target = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Nav cipars");
                        input.next();
                    }

                
              System.out.println("");
              if (target == 0) {
                System.out.println("TERMINATED SUCCESSFULLY");
                System.exit(0);
              } if (target < 0) {
                System.out.println("Nepareizs skaitlis");
              } else {
                  if (target > directoryListing.length) {
                      System.out.println("Nepareizs skaitlis");
                  } else {
                      System.out.print("\033[H\033[2J"); 
                      System.out.print("\033[H\033[2J"); 
                      exec(directoryListing[target-1].getName());
                  }
              }
            } else {
              System.out.println("Directory is empty or an I/O error occurred.");
            }
          }                
      } else {
          System.out.println("The path does not exist or is not a directory.");
          System.out.println("ši huina atkal nestrada, Es gribu pakarties");
          System.exit(1);
      }
      input.close();
    }
}
