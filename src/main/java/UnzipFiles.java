import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFiles {

    public void unzip(String pathToFile) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(pathToFile));
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            byte[] buffer = new byte[1024];
            int read = 0;
            while (zipEntry != null) {
                BufferedOutputStream bufferedOutputStream;
                System.out.println();
                if(pathToFile.contains("SOLR")) {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("file/tmpSolr.xml"));

                } else bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("file/tmp.xml"));
                while((read = zipInputStream.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer,0,read);
                }
                bufferedOutputStream.close();
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();

        } catch (FileNotFoundException e) {
            try {
                throw new FileNotFoundException("Nie znalezniono pliku pod sciezka: " + pathToFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
