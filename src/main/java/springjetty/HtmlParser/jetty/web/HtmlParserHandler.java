package springjetty.HtmlParser.jetty.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;

import springjetty.HtmlParser.jetty.service.HtmlParserService;

public class HtmlParserHandler extends AbstractHandler {
	
	@Autowired
	private HtmlParserService htmlParserService;
	
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (baseRequest.isHandled())
		{
			return;			
		}
		if(target != null && target.equals("/fullhtml"))
		{
			String url = request.getParameter("url");
			htmlParserService.writeFullHtml(url, response);			
		} 
	}
}