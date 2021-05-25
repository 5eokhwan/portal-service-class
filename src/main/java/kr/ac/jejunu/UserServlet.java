package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import java.io.IOException;

@Controller("/userServlet")
public class UserServlet extends GenericServlet {
    @Autowired
    private UserDao userDao;
    @Override
    public void destroy() {
        System.out.println("*************** destroy *****************");
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("*************** init *****************");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(("kr.ac.jejunu"));
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        User user = userDao.findById(1);
        System.out.println("*************** service *****************");
        StringBuffer response = new StringBuffer();
        response.append("<html>");
        response.append("<body>");
        response.append("<h1>");
        response.append(String.format("Hello %s !!!", user.getName()));
        response.append("</h1>");
        response.append("</body>");
        response.append("</html>");
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().println(response.toString());
    }
}