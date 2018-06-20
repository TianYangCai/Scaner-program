package barcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
*���������̻߳�Ӵ˻������л�ȡ���ݲ�ִ�����ݵı������
* ���ݵı������BarcodeSaveService�ӿڶ����save����
*
* @author ysc
*/
public class BarcodeConsumer {
	//�������߳�
    private Thread thread;
  //���ݱ�����񣨿��ж����
    private List<BarcodeSaveService> barcodeSaveServices=new ArrayList<BarcodeSaveService>();
    private boolean quit;
    
    /**
    * ֹͣ�������߳�
    * �˷�����tomcat�رյ�ʱ�򱻵���
    */
    public void stopConsume(){
        if(thread!=null){
            thread.interrupt();
          //�ͷ���Դ
            for(BarcodeSaveService barcodeSaveService : barcodeSaveServices){
                barcodeSaveService.finish();
            }
        }
    }
    /**
    * �����������߳�
    * �˷�����tomcat������ʱ�򱻵���
    */
    public void startConsume(){
    	//��ֹ�ظ�����
        if(thread!=null && thread.isAlive()){
            return;
        }
        System.out.println("Start the QR code consumer thread");
        
        System.out.println("Register the QR code save service");
        registerBarcodeSaveServcie();
        
        thread=new Thread(){
            @Override
            public void run(){
                while(!quit){
                    try{
                    	
                    	
                    	//�������barcode������Ŀ����ַ���
                    	//һֱ�����run����ѭ����ֻҪ������quit
                    	//��������û�����ݵ�ʱ�򣬴˷���������
                        String barcode=BarcodeBuffer.consume();
                        
                        
                        
                        
                        if(barcodeSaveServices.isEmpty()){
                            System.out.println("Havn't register any QR code save service");
                        }
                        for(BarcodeSaveService barcodeSaveService : barcodeSaveServices){
                            barcodeSaveService.save(barcode);
                        }
                    }catch(InterruptedException e){
                        quit=true;
                    }
                }
                System.out.println("Quit the QR code consumer thread");
            }
        };
        thread.setName("consumer");
        thread.start();
    }

    /**
    * �������̴߳ӻ�������ȡ�����ݺ���Ҫ���ñ����������ݽ��д���
    */
    private void registerBarcodeSaveServcie() {
        List<String> classes=getBarcodeSaveServcieImplClasses();
        System.out.println("The number of the QR save service "+classes.size());
        for(String clazz : classes){
            try{
                BarcodeSaveService barcodeSaveService=(BarcodeSaveService)Class.forName(clazz).newInstance();
                barcodeSaveServices.add(barcodeSaveService);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
    * ����·���µ�barcode.save.services�ļ��л�ȡ�����������
    * @return ����������ʵ������
    */
    private List<String> getBarcodeSaveServcieImplClasses() {
        List<String> result=new ArrayList<String>();
        BufferedReader reader = null;
        
        InputStream in=null;
        try {
        	//����WEB-INF/classes�µ�"barcode.save.services"�Ḳ��jar���е�"barcode.save.services"
            URL url=Thread.currentThread().getContextClassLoader().getResource("barcode.save.services");
            System.out.println("url:"+url.getPath());
            in=url.openStream();
            if(in==null){
                System.out.println("Do not find the file configuration in this route");
                return result;
            }
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line=reader.readLine();
            while(line!=null){
            	//���Կ��к���#�ſ�ʼ��ע����
                if(!"".equals(line.trim()) && !line.trim().startsWith("#")){
                    result.add(line);
                }
                line=reader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(reader!=null){
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
