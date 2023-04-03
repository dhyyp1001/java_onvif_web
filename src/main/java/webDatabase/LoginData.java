package webDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginData extends HttpServlet {
    private AuthenticationService authenticationService;

    @Override
    public void init()throws ServletException{
        authenticationService = new AuthenticationService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("dh");
        String password = request.getParameter("dh");
        boolean authenticated = authenticationService.authenticate(username, password);
        if(authenticated){
            System.out.println("굳ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ"+username+"  "+password);
        }else{
            System.out.println("실패"+username+"  "+password);
        }
    }
}
