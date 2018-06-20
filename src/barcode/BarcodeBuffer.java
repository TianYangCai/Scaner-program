package barcode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
*
*���������ݻ�����
* ɨ������ȡ��ɨ��ǹ��������ݺ����ݼ���˻�����
* �������̻߳�Ӵ˻������л�ȡ���ݲ�ִ�����ݵı������
* ���ݵı�������ж���ʵ��
* �˻��������������ڲ�Ҫ��Ϊ���ݱ��������ٶ�����Ӱ��ɨ�������
*
* @author ysc
*/
public class BarcodeBuffer {
	//��������
    private static BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
    /**
    * ����һ�����ݣ��˷�����BarcodeProducter����
    * @param barcode 
    */
    public static void product(String barcode){
        queue.add(barcode);
    }
    /**
    * ����һ�����ݣ����������û�����ݣ��˷��������ȴ����ݵĵ������˷�����BarcodeConsumer����
    * @return 
    */
    public static String consume() throws InterruptedException{
        return queue.take();
    }
}
