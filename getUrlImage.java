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
     * ��ȡ��ҳͼƬ 
     */  
	
    private Image img;  
    public void paint(Graphics g){  
      g.drawImage(img, 20, 20, this);//��ʾͼ��  
     }  
     public void processWindowsEvent(WindowEvent e){  
      //����windows�����¼�  
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
      urlt.setSize(600,600);//�趨���ڵĴ�С  
      urlt.setVisible(true);//Shows or hides this Window depending on the value of parameter  
     }  
    }  
