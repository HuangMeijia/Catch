import java.awt.AWTEvent;  
import java.awt.Frame;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.WindowEvent;  
import java.awt.image.ImageProducer;  
import java.io.IOException;  
import java.net.URL;  

import javax.swing.JPanel;
  
public class getUrlImage extends Frame{  
    /** 
     * 读取网页图片 
     */  
	
    private Image img;  
    public void paint(Graphics g){  
      g.drawImage(img, 20, 20, this);//显示图像  
     }  
     public void processWindowsEvent(WindowEvent e){  
      //处理windows窗口事件  
      super.processWindowEvent(e);  
      if (e.getID() == WindowEvent.WINDOW_CLOSING){  
       System.exit(0);  
      }  
     }  
     public static void main(String[] args) throws IOException{  
    	 JPanel jp = new JPanel();
    	 
      String path = "http://image.sinajs.cn/newchart/min/n/000001.gif";  
      URL url = new URL(path);  
      getUrlImage urlt = new getUrlImage();  
      urlt.img = urlt.createImage((ImageProducer)url.getContent());  
      urlt.enableEvents(AWTEvent.WINDOW_EVENT_MASK);  
      urlt.setSize(600,600);//设定窗口的大小  
      urlt.setVisible(true);//Shows or hides this Window depending on the value of parameter  
     }  
    }  
