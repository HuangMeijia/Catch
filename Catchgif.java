import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Catchgif {
 /**
  * 下载网页上图片
  * @param httpUrl
  * @param urlPath
  */
 public void getHtmlPicture(String httpUrl, String urlPath) {
  URL url;
  BufferedInputStream in;
  FileOutputStream file;
  try {
   // System.out.println("取网络图片");
   String fileName = urlPath.substring(urlPath.lastIndexOf("/"));
   String filePath = "j://gif/" //设置图片下载的根目录
     + urlPath.substring(0, urlPath.lastIndexOf("/"));
   System.out.println(filePath);
   url = new URL(httpUrl + urlPath);

   // 如果该目录不存在,则创建之
   File uploadFilePath = new File(filePath);
   if (uploadFilePath.exists() == false) {
    uploadFilePath.mkdirs();
   }

   in = new BufferedInputStream(url.openStream());
   file = new FileOutputStream(new File(filePath + fileName));

   int t;
   while ((t = in.read()) != -1) {
    file.write(t);
   }
   file.close();
   in.close();
   System.out.println("图片获取成功");
  } catch (MalformedURLException e) {
   e.printStackTrace();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
 /**
  * 获得网页html代码
  * @param httpUrl
  * @return
  * @throws IOException
  */
 public String getHtmlCode(String httpUrl) throws IOException {
  String content = "";
  URL uu = new URL(httpUrl); // 创建URL类对象
  BufferedReader ii = new BufferedReader(new InputStreamReader(uu
    .openStream())); // //使用openStream得到一输入流并由此构造一个BufferedReader对象
  String input;
  while ((input = ii.readLine()) != null) { // 建立读取循环，并判断是否有读取值
   content += input;
  }
  ii.close();
  return content;
 }
 /**
  * 下载网页代码及图片并替换图片链接地址
  * @param url
  * @throws IOException
  */
 public String get(String url) throws IOException {
  String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://.*?/)(.*?.(jpg|JPG|png|PNG|gif|GIF))('|\")";

  String content = this.getHtmlCode(url);
  System.out.println(content);
  // 下载图片
  Pattern pattern = Pattern.compile(searchImgReg);
  Matcher matcher = pattern.matcher(content);
  while (matcher.find()) {
   this.getHtmlPicture(matcher.group(3), matcher.group(4));
  }
  // 修改图片链接地址
  pattern = Pattern.compile(searchImgReg);
  matcher = pattern.matcher(content);
  StringBuffer replaceStr = new StringBuffer();
  while (matcher.find()) {
   matcher.appendReplacement(replaceStr, matcher.group(1) + "='/web_upload/"
     + matcher.group(4) + "'");//逐个动态替换图片链接地址
  }
  matcher.appendTail(replaceStr);//添加尾部
  //System.out.println(replaceStr.toString());
  return replaceStr.toString();
 }
 /**
  * 测试方法
  * @param args
  * @throws IOException
  */
 public static void main(String[] args) throws IOException {
  String url = "http://image.sinajs.cn/newchart/min/n/sz000001.gif";
  Catchgif gcp = new Catchgif();
  gcp.get(url);
 }
}

