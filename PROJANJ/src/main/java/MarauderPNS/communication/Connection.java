package MarauderPNS.communication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Garance on 15/01/2015.
 */
public class Connection {

    public Connection() {

    }

    public String createHash(String pwd) {
        String code = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(pwd.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            code = sb.toString();
        }
        catch(NoSuchAlgorithmException nSAException) {
                    nSAException.printStackTrace();
                }

        return code;
    }
}
