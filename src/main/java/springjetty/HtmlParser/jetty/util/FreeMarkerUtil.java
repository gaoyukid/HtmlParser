package springjetty.HtmlParser.jetty.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import springjetty.HtmlParser.jetty.entity.ReadResult;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class FreeMarkerUtil {
    Configuration freeMarkerCfg = new Configuration();
    Template template = null;
    public FreeMarkerUtil() {
        freeMarkerCfg.setClassForTemplateLoading(getClass(),"");
        freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
        try {
            template = freeMarkerCfg.getTemplate("FullHtml.ftl");
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }
    
   
    
    public void generateFullHtml(ReadResult readResult, OutputStream outputStream) {
    	try {
    		Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("readResult", readResult);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
			template.process(parameters, writer);
            writer.flush();
            writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void generateRequest(ReadResult obj) {

        String reqFileName = "mytest.html";
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("readResult", obj);

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(reqFileName), "UTF-8");
            template.process(parameters, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();// TODO
        } catch (TemplateException e) {
            e.printStackTrace();// TODO
        }

    }
    
}
