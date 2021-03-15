/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author Welcome
 */
public class MyRequestListener implements ServletRequestListener {

    final String filename = "C:\\Users\\Asus\\Desktop\\J3.L.P0014\\filter.txt";
    Map<String, String> dispatch = null;

    public Map<String, String> getDispatch() {
        return dispatch;
    }

    public MyRequestListener() {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        dispatch = new HashMap<>();
        FileReader f = null;
        BufferedReader bf = null;
        try {
            f = new FileReader(filename);
            bf = new BufferedReader(f);
            String line;
            StringTokenizer stk;
            while ((line = bf.readLine()) != null) {
                stk = new StringTokenizer(line, "-");
                dispatch.put(stk.nextToken(), stk.nextToken());
                sre.getServletContext().setAttribute("FILTER", dispatch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

}
