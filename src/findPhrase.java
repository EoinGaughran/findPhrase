import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class findPhrase{

    public static void main(String[] args) {

        searchFiles();
    }

    private static void searchFiles(){

        List<File> directories = new ArrayList<>();
        directories.add(new File("C:\\Rockfield\\Git\\DockingStation"));
        String term = "dock";
        File[] listOfFiles;
        int results = 0;

        try {

            for (int i = 0; i < directories.size(); i++) {

                listOfFiles = directories.get(i).listFiles();

                if (listOfFiles != null) {
                    for (File listOfFile : listOfFiles) {
                        if (listOfFile.isFile()) {

                            String fileName = listOfFile.getName();
                            if(searchTermMatchesFile(listOfFile, term))
                                results++;


                        } else if (listOfFile.isDirectory()) {

                            directories.add(listOfFile);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {

            e.printStackTrace();
        }

        System.out.println("Files with phrase: " + results);
    }

    private static boolean searchTermMatchesFile(File mFile, String mTerm){

        boolean foundMatch = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(mFile));
            String text = null;
            int count = 0;

            while ((text = reader.readLine()) != null) {
                count++;
                if(text.contains(mTerm)){
                    System.out.println("Line " + count + " in: " + mFile.toString());
                    foundMatch = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundMatch;
    }
}