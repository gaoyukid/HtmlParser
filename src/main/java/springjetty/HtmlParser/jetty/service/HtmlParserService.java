package springjetty.HtmlParser.jetty.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import springjetty.HtmlParser.jetty.entity.ReadResult;
import springjetty.HtmlParser.jetty.util.ResultFormat;

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
	
	public String readPageInFormat(String url, String format)
	{
		String result = "{}";
		ReadResult readResult = readPage(url);
		if(ResultFormat.JSON.equals(format))
		{
			result = gson.toJson(readResult);
		} else if(ResultFormat.TITLE.equals(format)) {
			result = readResult.getTitle();
		} else if(ResultFormat.TEXT.equals(format)) {
			result = readResult.getContent();
		} else {
			// default is to get content
			result = readResult.getContent();
		}
		return result;
	}
	
	public ReadResult readPage(String url)
	{
		ReadResult readResult = new ReadResult();
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
			
			readResult.setContent(content);
			readResult.setTitle(title);
			
		} catch (PageReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readResult;
	}
}
