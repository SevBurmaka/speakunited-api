package org.betsev.acp.support;

import org.betsev.acp.business.letter.entity.LetterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * Created by sevburmaka on 1/29/17.
 */
public class LetterUtil {
    private static final Logger LOG = LoggerFactory.getLogger(LetterUtil.class);

    public static void deleteOnAfterDelay(File f){
        Runnable task2 = () -> {
            try {
                Thread.sleep(10000);
            }catch(Exception e){}
            f.delete();
        };
        new Thread(task2).start();
    }

    public static File createHtmlFile(LetterRequest request){
        UUID nameId = UUID.randomUUID();
        File tmp = new File(nameId+".html");
        try {
            FileWriter writer = new FileWriter(tmp);
            writer.write(createHtmlString(request));
            writer.flush();
            writer.close();
        }catch (Exception e){
            LOG.error("Error while attempting to create file for {}",request);
        }
        return tmp;
    }

    public static String createHtmlString(LetterRequest request){
        StringWriter writer = new StringWriter();
        writer.write("<P align=right >"+request.getName()+"</br>");
        writer.write(request.getAddress()+"</br>");
        writer.write("</P>");
        writer.write("<h2>"+request.getHeader()+"</h2>");
        writer.write("<body>"+request.getBody() +"</body>");
        writer.write("<P align=left > Sincerely, </br>"+request.getName()+"</br></P>");
        writer.flush();
        return writer.toString();
    }
}
