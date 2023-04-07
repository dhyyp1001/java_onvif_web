package webDatabase;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LoginData extends HttpServlet {
    private AuthenticationService authenticationService;

    @Override
    public void init()throws ServletException{
        authenticationService = new AuthenticationService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestId = request.getParameterNames().nextElement().split(" ")[0];
        String requestPw = request.getParameterNames().nextElement().split(" ")[1];

        boolean authenticated = authenticationService.authenticate(requestId, requestPw);
        if(authenticated){
            response.getWriter().write("ok");
            System.out.println("Login success : username//"+requestId);
            List<CamInfo> camInfoList = authenticationService.listLoad(requestId);

            JSONArray jsonArray = new JSONArray().fromObject(camInfoList);
            response.getWriter().write("//////"+ jsonArray);
        }else{
            response.getWriter().write("error");
            System.out.println("Login fail : username//"+requestId);
        }
    }
}
