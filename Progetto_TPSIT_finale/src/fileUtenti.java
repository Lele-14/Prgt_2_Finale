import java.io.*;
import java.util.*;
public class fileUtenti {
    Vector <File> vettoreFile = new Vector <File> (20, 10);
    
    public File creaFileUtente(Utente u) throws IOException {
        String nomeFile= u.getCognome()+"_"+u.getNome()+".txt";
        File file = new File(nomeFile);
        FileWriter fw = new FileWriter(file);
        fw.write(u.toString()+"\n");
        fw.close();
        return file;
    }

    public void aggiungiFile(File file) {
        vettoreFile.addElement(file);
    }

    public File getFile (int i) {
        if (vettoreFile.isEmpty()) {
            return null;
        } else {
            return vettoreFile.elementAt(i);
        }
    }

  

    public void appendToFile(Utente u, String textToAppend) {
        String nomeFile= u.getCognome()+"_"+u.getNome()+".txt";
        try (FileWriter fw = new FileWriter(nomeFile, true)) {  
            fw.write(textToAppend + "\n");  // Aggiunge il testo con un a capo
            //System.out.println("Testo aggiunto con successo!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}