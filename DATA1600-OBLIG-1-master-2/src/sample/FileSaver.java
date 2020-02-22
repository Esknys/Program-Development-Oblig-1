package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    // objects: Liste med objekter, ? tillater alle typer lister
    // Bruker List sin toString metode. List bruker videre toString til objektene i listen
    public static void save(String string, File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(string);
        writer.close();

        }
    }