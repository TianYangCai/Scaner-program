package barcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *ɨ�����������Ѵ˼��������õ�web.xml��
 * 
    <listener>
        <listener-class>barcode.ScanBarcodeListener</listener-class>
    </listener>
 * @author ysc
 */
public class ScanBarcodeListener  implements ServletContextListener{
    private BarcodeProducter barcodeProducter;
    private BarcodeConsumer barcodeConsumer;
    
    /**
    * tomcat����
    * @param sce 
    */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        barcodeProducter=new BarcodeProducter();
        barcodeProducter.startProduct();
        barcodeConsumer=new BarcodeConsumer();
        barcodeConsumer.startConsume();
    }
    /**
    * tomcat�ر�
    * @param sce 
    */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        barcodeProducter.stopProduct();
        barcodeConsumer.stopConsume();
    }
    /**
    * �����ڴ��ļ������в���
    * @param args
    * @throws Exception 
    */
    public static void main(String[] args) throws Exception {
        BarcodeProducter producter=new BarcodeProducter();
        BarcodeConsumer consumer=new BarcodeConsumer();
        
        producter.startProduct();
        consumer.startConsume();
        
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter '<exit>' Quit the program");
        
        //�������ж������Ƿ���exit���˳�
        String line=reader.readLine();
        while(line!=null){
            if("exit".equals(line)){
            	
                producter.stopProduct();
                consumer.stopConsume();
                System.exit(0);
            }
            line=reader.readLine();
        }
    }
}
