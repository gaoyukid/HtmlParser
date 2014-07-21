package springjetty.HtmlParser.jetty.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import springjetty.HtmlParser.jetty.entity.ReadResult;

import com.basistech.readability.HttpPageReader;
import com.basistech.readability.PageReadException;
import com.basistech.readability.PageReader;
import com.basistech.readability.Readability;
import com.basistech.readability.TikaCharsetDetector;
import com.google.gson.Gson;

@Component
public class HtmlParserService{
	//the logger
    private static final Logger LOG = LoggerFactory.getLogger(HtmlParserService.class); 
    
	private Readability readability;
	
	private PageReader pageReader;
	
	private Gson gson;
	
	public HtmlParserService()
	{
		pageReader = new HttpPageReader();
		pageReader.setCharsetDetector(new TikaCharsetDetector());
		readability = new Readability();
        readability.setPageReader(pageReader);
        readability.setReadAllPages(false);
        gson = new Gson();
	}
	
	public String readPage(String url)
	{
		String result = "{}";
		try {
			readability.processDocument(url);
			
			String title = readability.getTitle();
			String content = readability.getArticleText();
			
			if(LOG.isDebugEnabled())
			{
				LOG.debug("----- title ------");
				LOG.debug(title);
				LOG.debug("----- content ------");
				LOG.debug(content);
			}
			ReadResult readResult = new ReadResult();
			readResult.setContent(content);
			readResult.setTitle(title);
//			StringBuffer sb = new StringBuffer();
//			
//			sb.append("<!DOCTYPE html><html>");
//			sb.append("<head><title>");
//			sb.append(title);
//			sb.append("</title><meta http-equiv=\"Content-Type\" "
//					+ "content=\"text/html; charset=UTF-8\" />");
//			sb.append("<body>");
//			sb.append(content);
//			sb.append("</body>");
//			sb.append("</html>");
			
			result = gson.toJson(readResult);
		} catch (PageReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
