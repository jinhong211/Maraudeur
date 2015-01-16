package MarauderPNS.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Quentin on 14/01/2015.
 */
public class DataReader {

        /**
         * This method return a string with the information contain in the config file with the given key
         * @param subString
         * @return the information corresponding to the key in the config file
         */
        public String getConfig(String subString) {
            String infor;
            String start = subString + " :";
            int delete = start.length();
            int begin = configReader().lastIndexOf(start);
            int end = configReader().lastIndexOf("!" + subString);
            infor = configReader().substring(begin+delete,end);
            return infor;
        }

        /**
         * This method read all the file config.txt and return a string containing all the information of the file
         * @return a string with all the config.txt information
         */
        private String configReader() {
            String file = "";
            try (InputStream is = getClass().getResourceAsStream("/config.txt")) {
                InputStreamReader ipsr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ipsr);
                String line;
                while ((line=br.readLine())!= null){
                    file = file + line;
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return file;
    }

}
