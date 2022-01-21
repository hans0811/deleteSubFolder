

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Hans
 */
public class deleteSubFolder {


    public static List<File> findDirWithName(String name, File root){
        List<File> res = new ArrayList<>();

        for(File file:root.listFiles()){
            if(file.isDirectory()) {
                if(file.getName().equals(name)){
                    res.add(file);
                }
                res.addAll(findDirWithName(name, file));
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {

        if(args.length == 0){
            System.out.println("Please enter directory name and path... ");
            return;
        }

        String dirName = args[0];
        String dirPath = args[1];
//        String dirName = "test";
//        String dirPath = "C:\\Users\\hans.tsai\\Desktop\\AppScan\\eloan-platform";

        List<File> files = findDirWithName(dirName, new File(dirPath));


        for(File f : files){
            System.out.println(f);

            Files.walk(Paths.get(f.getPath()))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

            f.delete();

            System.out.println("Delete " + f);
        }
    }
}
