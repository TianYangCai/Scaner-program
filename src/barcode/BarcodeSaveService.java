package barcode;

/**
*�����뱣�����
* @author ysc
*/
public interface BarcodeSaveService {
	/**
	* ����������
	* @param barcode 
	*/
    public void save(String barcode);
    /**
    * �������ͷ���Դ�������ݿ����ӣ��ر��ļ����ر��������ӵ�
    */
    public void finish();
}
