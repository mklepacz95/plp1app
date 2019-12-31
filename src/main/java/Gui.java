import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JPanel implements ActionListener {
    JButton files;
    JButton solr;

    JFileChooser chooser;
    String choosertitle;

    UnzipFiles unzipFiles = new UnzipFiles();
    XmlProcessor xmlProcessor = new XmlProcessor();

    public Gui() {

        files = new JButton("Wybierz plik");
        files.setPreferredSize(new Dimension(300,20));
        files.addActionListener(this);
        add(files);

        solr = new JButton("Plik SOLR");
        solr.setPreferredSize(new Dimension(150,20));
        solr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("C:\\Users\\m.klepacz\\Desktop\\przyrosty\\!2019"));
                chooser.setDialogTitle(choosertitle);
                if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                    unzipFiles.unzip(chooser.getSelectedFile().getPath());
                    System.out.println("Wybrano: " + chooser.getSelectedFile());
                    xmlProcessor.deleteNodeFromSolrXml();
                }
                else System.out.println("Nie wybrano");
            }
        });
        add(solr);


    }

    public Dimension getPreferredSize(){
        return new Dimension(150, 150);
    }


    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("C:\\Users\\m.klepacz\\Desktop\\przyrosty\\!2019"));
        chooser.setDialogTitle(choosertitle);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            unzipFiles.unzip(chooser.getSelectedFile().getPath());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
            xmlProcessor.deleteNodefromXml();
        }
        else {
            System.out.println("No Selection ");
        }
    }

}