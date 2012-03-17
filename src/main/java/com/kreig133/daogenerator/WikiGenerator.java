package com.kreig133.daogenerator;

import com.kreig133.daogenerator.gui.GuiUtils;

import javax.swing.*;
import java.io.IOException;

/**
 * @author eshangareev
 * @version 1.0
 */
public class WikiGenerator {
    public static void main( String[] args ) throws IOException {
        final JFileChooser fileChooser = GuiUtils.getFileChooser();
        if( fileChooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ){
            DaoGenerator.getCurrentOperationSettings().setSourcePath( fileChooser.getSelectedFile().getAbsolutePath() );
            final String[] xmlFileNamesInDirectory = DaoGenerator.getXmlFileNamesInDirectory();


            for ( String s : xmlFileNamesInDirectory ) {
                final String[] cmdarray = { "cmd", "/C",
                        "java  -classpath DaoGenerator-2.3.jar org.apache.xalan.xslt.Process " +
                                "-IN " + DaoGenerator.getCurrentOperationSettings().getSourcePath()+ "\\" + s +
                                " -XSL XmlToWiki.xsl " +
                                "-OUT "+DaoGenerator.getCurrentOperationSettings().getSourcePath()+ "\\" + s + ".txt"  };
                for ( String s1 : cmdarray ) {
                    System.out.println( s1 );
                }

                System.out.println(" END ");
                Process p = Runtime.getRuntime().exec( cmdarray );
            }
        }
    }
}