import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Random;

public class Main {
    //======================================[ IESTATĪJUMI ]===============================================

    static public int gaidisanas_laiks_pec_kura_sak_stradat = 5; // sekundēs
    static public boolean atgriesties_launcheri_pec_palaišanas = true; // vai pec kadas programas palaišanas atgriezties launcheri

    //====================================================================================================

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

            ProcessBuilder processBuilder = new ProcessBuilder("java", "proj/" + end);

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
            TimeUnit.SECONDS.sleep(gaidisanas_laiks_pec_kura_sak_stradat);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        boolean stop = false;
        File dir = new File(System.getProperty("user.dir") + "/src/main/java/proj");
        Scanner input = new Scanner(System.in);

        if (dir.exists() && dir.isDirectory()) {
            File[] directoryListing = dir.listFiles();

            while (stop == false) {
                if (!atgriesties_launcheri_pec_palaišanas) {
                    stop = true;
                }

                System.out.print("\033[H\033[2J");
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("  ____  _     _ _   _               _                    _                           _               ");
                System.out.println(" / ___|| |__ (_) |_| |_ _   _      | | __ ___   ____ _  | |    __ _ _   _ _ __   ___| |__   ___ _ __ ");
                System.out.println(" \\___ \\| '_ \\| | __| __| | | |  _  | |/ _` \\ \\ / / _` | | |   / _` | | | | '_ \\ / __| '_ \\ / _ \\ '__|");
                System.out.println("  ___) | | | | | |_| |_| |_| | | |_| | (_| |\\ V / (_| | | |__| (_| | |_| | | | | (__| | | |  __/ |   ");
                System.out.println(" |____/|_| |_|_|\\__|\\__|\\__, |  \\___/ \\__,_| \\_/ \\__,_| |_____\\__,_|\\__,_|_| |_|\\___|_| |_|\\___|_|   ");
                System.out.println("                        |___/                                                                        ");
                System.out.println(" MADE BY SIGMATRON™ LIEPTONS™ SOFTWARE™ INCORPORATED™ - V.1.0.0\n");

                int i = 1;
                System.out.println("0 | EXIT");
                if (directoryListing != null) {
                    for (File child: directoryListing) {
                        System.out.println(i + " | " + child.getName());
                        i++;
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
                    }
                    if (target < 0) {
                        System.out.println("Nepareizs skaitlis");
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (target > directoryListing.length) {
                            System.out.println("Nepareizs skaitlis");
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Random rand = new Random(); 
                            int value = rand.nextInt(50); 
                            if (value ==2) {
                              System.out.println("This feature is only availible to platnum users of our software, please upgrade to platnum to use this feature");
                              System.out.println("50% DISCOUNT ON PLATNUM, ONLY 3 BITCOINS A MONTH!");
                              System.exit(0);
                            } else {
                              System.out.print("\033[H\033[2J");
                              System.out.print("\033[H\033[2J");
                              exec(directoryListing[target - 1].getName());
                            }
                        }
                    }
                } else {
                    System.out.println("Directory is empty or an I/O error occurred.");
                }
            }
        } else {
            File dir2 = new File(System.getProperty("user.dir") + "/src/main/java/arhivs");
            String fileName = "piemers.java";
            String content = "package proj;\n\npublic class piemers {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}";

            System.out.println("The path does not exist or is not a directory.");
            System.out.println("uztaisa proj un arhivs folderus");

            if (dir.exists()) {
                if (dir.isDirectory()) {
                    System.out.println("This dosent make sense");
                } else {
                    System.out.println("The path exists, but it's not a directory, delete proj and arhivs files before");
                }
            } else {
                if (dir.mkdirs() && dir2.mkdirs()) {
                    System.out.println("Directory created successfully.");
                    File newJavaFile = new File(dir, fileName);
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter(newJavaFile));
                        writer.write(content);
                        System.out.println("File created and content written to " + newJavaFile.getAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("Error creating or writing to the file: " + e.getMessage());
                    } finally {
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (IOException e) {
                                System.out.println("Error closing the writer: " + e.getMessage());
                            }
                        }
                    }
                    System.out.println("Relaunch the app");
                } else {
                    System.out.println("Failed to create the directory.");
                }
            }
            System.exit(1);
        }
        input.close();
    }
}
