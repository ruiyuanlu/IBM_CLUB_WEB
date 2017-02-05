package club.istc.action;
 
import java.io.*;  
import java.util.List;

import org.apache.poi.xwpf.converter.core.*;  
import org.apache.poi.xwpf.converter.xhtml.*;   
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.converter.*;
import org.apache.poi.hwpf.usermodel.*;
import org.w3c.dom.Document;

/**
 * 实现office在线预览的功能
 */

public class WordOnlineConverter {  
  
    //@Test  
    public static void canExtractImage(String fullpath) throws IOException, ParserConfigurationException, TransformerException {
    	File f=new File(fullpath);
    	String path=f.getParent()+"\\";
    	String file=f.getName();
    	System.out.println(path);
    	System.out.println(file);
        if (!f.exists()) {
            throw new FileNotFoundException(); 
        } else {  
            if (f.getName().toLowerCase().endsWith(".docx")) {  
                InputStream in = new FileInputStream(f);  
                XWPFDocument document = new XWPFDocument(in);  
                File imageFolderFile = new File(path+file.substring(0, file.indexOf(".")));  
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));  
                options.setExtractor(new FileImageExtractor(imageFolderFile));  
                OutputStream out = new FileOutputStream(new File(path+file.substring(0, file.indexOf("."))+".html"));  
                XHTMLConverter.getInstance().convert(document, out, options);  
            } else {  
            	InputStream input = new FileInputStream(f);
                HWPFDocument wordDocument = new HWPFDocument(input);
                WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
                wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                    public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                        return file.substring(0, file.indexOf("."))+"/"+suggestedName;
                    }
                });
                wordToHtmlConverter.processDocument(wordDocument);
                List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
                if (pics!= null) {
                	File picdir=new File(path+file.substring(0, file.indexOf(".")));
    		        if (!picdir.exists()) {
    					picdir.mkdirs();
    				}
                    for (int i = 0; i < pics.size(); i++) {
                        Picture pic = (Picture) pics.get(i);
                        try {
                            pic.writeImageContent(new FileOutputStream(picdir+"/"+pic.suggestFullFileName()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Document htmlDocument = wordToHtmlConverter.getDocument();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                DOMSource domSource = new DOMSource(htmlDocument);
                StreamResult streamResult = new StreamResult(outStream);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer serializer = tf.newTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");
                serializer.setOutputProperty(OutputKeys.METHOD, "html");
                serializer.transform(domSource, streamResult);
                outStream.close();
                String content = new String(outStream.toByteArray());
                FileUtils.write(new File(path, file.substring(0, file.indexOf("."))+".html"), content, "utf-8");
            }  
        }  
    }  
}  