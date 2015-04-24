import java.net.*;
import java.io.*;
public class Catch
{ 
	public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return json.toString();  
    }  
	public static void main (String args[]) 
	{
		String token="";
		String total="";
		try{
			
				URL gis = new URL("http://www.iwencai.com/stockpick/search?typed=1&preParams=&ts=1&f=1&qs=1&selfsectsn=&querytype=&searchfilter=&tid=stockpick&w=pe");//你要报错的网页
				BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
				//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//保存的路径
				String line="";
				while( (line = in.readLine()) != null )
				{
			    if(line.indexOf("token")>0)
			    {		    			    
			    String[] str = line.split("\"token\":\"");
			    String[] str1 = str[1].split("\",\"");
			    String[] strtotal = str[0].split("\"total\":");
			    String[] strtotal1 = strtotal[1].split(",");
			    total = strtotal1[0];			    
			    token = str1[0];
				System.out.print(token+"----");
				System.out.print(total+"----");
			    	break;
			    }
				}
				in.close();
				
			}
		
		catch(Exception e){
				System.out.println(e); 
			}
		try{
			
			URL gis = new URL("http://www.iwencai.com/stockpick/cache?token="+token+"&p=1&perpage="+total);//你要报错的网页
			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//保存的路径
			 String json = loadJson("http://www.iwencai.com/stockpick/cache?token="+token+"&p=1&perpage="+total);  
		        System.out.println(json); 
			String line="";
			String jsonstr[] = json.split("\"list\"");
			String jsonstr1[] = jsonstr[1].split("]]");
			String jsonstr2 = jsonstr1[0];
			String data[] = jsonstr2.split("],");
			for(int i=0;i<data.length;i++)
				System.out.print(data[i]+"\n");
		}
	
	catch(Exception e){
			System.out.println(e); 
		}
	}
}