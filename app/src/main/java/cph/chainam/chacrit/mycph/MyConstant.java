package cph.chainam.chacrit.mycph;

/**
 * Created by IT on 28/4/2560.
 */

public class MyConstant {

    private String urlGetProductWhereQR = "http://swiftcodingthai.com/cph/getProductWhereQR_chacrit.php";

    private String[] columnProduct = new String[]{"id", "Name", "QR_code", "id_Receive", "Description", "Date_Receive"};
    private String urlGetUserWhereID = "http://swiftcodingthai.com/cph/getUserWhereID.php";

    public String getUrlGetUserWhereID() {
        return urlGetUserWhereID;
    }

    public String[] getColumnProduct() {
        return columnProduct;
    }

    public String getUrlGetProductWhereQR() {





        return urlGetProductWhereQR;
    }
}// Main Class
