package barcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
*把条形码数据保存到文件的实现（参考实现）
* 可根据自己的需求做一个自定义实现
* 只要实现BarcodeSaveService接口的方法
* 并在barcode.save.services文件中指定使用的实现类即可
* @author ysc
*/
public class BarcodeSaveToFile implements BarcodeSaveService{
    private Writer writer;
    
    /**
    * 保存到文件
    * @param barcode 
    * 在这里的barcode 是我们需要处理的 二维码的字符串
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
    * 关闭文件
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
