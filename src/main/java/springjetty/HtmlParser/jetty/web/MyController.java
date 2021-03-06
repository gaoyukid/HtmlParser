/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package springjetty.HtmlParser.jetty.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springjetty.HtmlParser.jetty.service.HelloWorldService;
import springjetty.HtmlParser.jetty.service.HtmlParserService;

@Controller
@RequestMapping("/")
public class MyController {

	public MyController()
	{
		String name = "abc";
		name.toCharArray();
	}
	
	@Autowired
	private HelloWorldService helloWorldService;

	@Autowired
	private HtmlParserService htmlParserService;

	@RequestMapping("/hello")
	@ResponseBody
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}
	
//	@RequestMapping("/htdocs/css/biaoxin.css")
//	@ResponseBody
//	public String getBiaoXinCss(HttpServletRequest request, HttpServletResponse response) {
//		return helloWorldService.getCssFileCache();
//	}
	
//	@RequestMapping("/htdocs/img/logo.icon")
//	@ResponseBody
//	public String getLogoIcon(HttpServletRequest request, HttpServletResponse response) {
//		return helloWorldService.getCssFileCache();
//	}
	
	
	@RequestMapping("/html")
	@ResponseBody
	public String htmlParser(HttpServletRequest request, HttpServletResponse response) {
		String result = "{}";
		String url = request.getParameter("url");
		String format = request.getParameter("format");
		result = this.htmlParserService.readPageInFormat(url, format);
		response.setContentType("application/json; charset=UTF-8");
		return result;
	}
	
	@RequestMapping("/fullhtml")
	@ResponseBody
	public void getFullHtml(HttpServletRequest request, HttpServletResponse response) {
//		String result = "{}";
		String url = request.getParameter("url");
		htmlParserService.writeFullHtml(url, response);
		
//		return result;
	}
}
