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
  * ������ҳ��ͼƬ
  * @param httpUrl
  * @param urlPath
  */
 public void getHtmlPicture(String httpUrl, String urlPath) {
  URL url;
  BufferedInputStream in;
  FileOutputStream file;
  try {
   // System.out.println("ȡ����ͼƬ");
   String fileName = urlPath.substring(urlPath.lastIndexOf("/"));
   String filePath = "j://gif/" //����ͼƬ���صĸ�Ŀ¼
     + urlPath.substring(0, urlPath.lastIndexOf("/"));
   System.out.println(filePath);
   url = new URL(httpUrl + urlPath);

   // �����Ŀ¼������,�򴴽�֮
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
   System.out.println("ͼƬ��ȡ�ɹ�");
  } catch (MalformedURLException e) {
   e.printStackTrace();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
 /**
  * �����ҳhtml����
  * @param httpUrl
  * @return
  * @throws IOException
  */
 public String getHtmlCode(String httpUrl) throws IOException {
  String content = "";
  URL uu = new URL(httpUrl); // ����URL�����
  BufferedReader ii = new BufferedReader(new InputStreamReader(uu
    .openStream())); // //ʹ��openStream�õ�һ���������ɴ˹���һ��BufferedReader����
  String input;
  while ((input = ii.readLine()) != null) { // ������ȡѭ�������ж��Ƿ��ж�ȡֵ
   content += input;
  }
  ii.close();
  return content;
 }
 /**
  * ������ҳ���뼰ͼƬ���滻ͼƬ���ӵ�ַ
  * @param url
  * @throws IOException
  */
 public String get(String url) throws IOException {
  String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://.*?/)(.*?.(jpg|JPG|png|PNG|gif|GIF))('|\")";

  String content = this.getHtmlCode(url);
  System.out.println(content);
  // ����ͼƬ
  Pattern pattern = Pattern.compile(searchImgReg);
  Matcher matcher = pattern.matcher(content);
  while (matcher.find()) {
   this.getHtmlPicture(matcher.group(3), matcher.group(4));
  }
  // �޸�ͼƬ���ӵ�ַ
  pattern = Pattern.compile(searchImgReg);
  matcher = pattern.matcher(content);
  StringBuffer replaceStr = new StringBuffer();
  while (matcher.find()) {
   matcher.appendReplacement(replaceStr, matcher.group(1) + "='/web_upload/"
     + matcher.group(4) + "'");//�����̬�滻ͼƬ���ӵ�ַ
  }
  matcher.appendTail(replaceStr);//���β��
  //System.out.println(replaceStr.toString());
  return replaceStr.toString();
 }
 /**
  * ���Է���
  * @param args
  * @throws IOException
  */
 public static void main(String[] args) throws IOException {
  String url = "http://image.sinajs.cn/newchart/min/n/sz000001.gif";
  Catchgif gcp = new Catchgif();
  gcp.get(url);
 }
}

