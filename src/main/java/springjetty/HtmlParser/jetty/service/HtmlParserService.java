package springjetty.HtmlParser.jetty.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springjetty.HtmlParser.jetty.dao.PageReader;
import springjetty.HtmlParser.jetty.entity.ReadResult;
import springjetty.HtmlParser.jetty.util.FreeMarkerUtil;
import springjetty.HtmlParser.jetty.util.ResultFormat;

import com.google.gson.Gson;
import com.sree.textbytes.network.HtmlFetcher;
import com.sree.textbytes.readabilityBUNDLE.Article;
import com.sree.textbytes.readabilityBUNDLE.ContentExtractor;

@Component
public class HtmlParserService{
	//the logger
    private static final Logger LOG = LoggerFactory.getLogger(HtmlParserService.class); 
    
    @Autowired
    private ContentExtractor contentExtractor;
    
	@Autowired
	private FreeMarkerUtil freeMarkerUtil;
	
	@Autowired
	private PageReader pageReader;
	
	private Gson gson;
	
	public HtmlParserService()
	{
//		pageReader = new HttpPageReader();
//		pageReader.setCharsetDetector(new TikaCharsetDetector());
//		readability = new Readability();
//        readability.setPageReader(pageReader);
//        readability.setReadAllPages(false);
//        readability.setCleanHtmlTags(false);
//        gson = new Gson();
	}
	
	public void writeFullHtml(String url, HttpServletResponse response)
	{
		ReadResult readResult = readPage(url);
		response.setContentType("text/html; charset=UTF-8");
		try {
			freeMarkerUtil.generateFullHtml(readResult, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		} else if(ResultFormat.HTML.equals(format)) {
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
			
			Article article = new Article();
			String html = pageReader.readPage(url);
//			HtmlFetcher htmlFetcher = new HtmlFetcher();
//			String html = htmlFetcher.getHtml(url, 0);

			article = contentExtractor.extractContent(html, "ReadabilityCommaLength");
			
			String title = article.getTitle();
			String content = article.getCleanedArticleText();
			if(LOG.isDebugEnabled())
			{
				LOG.debug("----- title ------");
				LOG.debug(title);
				LOG.debug("----- content ------");
				LOG.debug(content);
			}
			
			readResult.setContent(content);
			readResult.setTitle(title);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readResult;
	}
}
