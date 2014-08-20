package springjetty.HtmlParser.jetty.util;

public enum ResultFormat {
	TEXT("text"), JSON("json"), TITLE("title"), HTML("html");
	
	private String name;
	private ResultFormat(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	public static boolean isEqualWith(String name, ResultFormat resultFormat)
	{
		return resultFormat.getName().equals(name);
	}

	public boolean equals(String name)
	{
		return this.name.equals(name);
	}
}
