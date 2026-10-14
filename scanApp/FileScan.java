import javax.swing.JFileChooser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
public class FileScan{
    public static void main(String[]args){
        Path path;
        if(args.length==1){
            path = Path.of(args[0]);
            if(!Files.isRegularFile(path)){
                System.out.println("ERROR: "+args[0]+" is not a regular file.");
                return;
            }
        }
        else if(args.length==0){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("/home/rudra-prajapati/IdeaProjects/Lab_14_CMD_Scripting/scanApp"));
            if (chooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION){
                return;
            }
            path=chooser.getSelectedFile().toPath();
        }
        else{
            System.out.println("ERROR: Can only work with a single file at a time.");
            return;
        }
        try{
            List<String>contents=Files.readAllLines(path);
            int lineCount = 0;
            int wordCount = 0;
            int characterCount = 0;
            for (String line : contents){
                System.out.println(line);
                lineCount++;
                if (!line.trim().isEmpty()){
                    wordCount += line.trim().split("\\s+").length;
                }
                characterCount += line.length();
            }
            System.out.println("\nSummary of the file:\nFile name: "+path.getFileName()+"\nTotal number of lines in the file: "+lineCount+"\nTotal number of words in the file: "+wordCount+"\nTotal number of characters in the file: " + characterCount);
        }
        catch (IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}