package barcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
*�����������ݱ��浽�ļ���ʵ�֣��ο�ʵ�֣�
* �ɸ����Լ���������һ���Զ���ʵ��
* ֻҪʵ��BarcodeSaveService�ӿڵķ���
* ����barcode.save.services�ļ���ָ��ʹ�õ�ʵ���༴��
* @author ysc
*/
public class BarcodeSaveToFile implements BarcodeSaveService{
    private Writer writer;
    
    /**
    * ���浽�ļ�
    * @param barcode 
    * �������barcode ��������Ҫ����� ��ά����ַ���
    */
    @Override
    public void save(String barcode) {
        try {
            if(writer==null){
                System.out.println("Open the file");
                writer=new OutputStreamWriter(new FileOutputStream("d:/barcode.txt",true));
            }
            writer.write(barcode+"\n");
            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
    * �ر��ļ�
    */
    @Override
    public void finish() {
        System.out.println("Close the file");
        try {
            if(writer!=null){
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
