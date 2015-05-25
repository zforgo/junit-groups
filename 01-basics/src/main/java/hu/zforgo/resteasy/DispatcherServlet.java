package hu.zforgo.resteasy;

import hu.zforgo.resteasy.model.Operation;

import javax.enterprise.context.RequestScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(urlPatterns = {"/vi.pl"}, loadOnStartup = 1)
@RequestScoped
public class DispatcherServlet extends HttpServlet {

	private static final String PARAM_PID = "pid";

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		final String pid = request.getParameter(PARAM_PID);
		if (null != pid && pid.length() > 0) {
			return;
		}

		Operation type = Operation.byPid(pid);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(type.getUri());
		dispatcher.forward(request, response);
	}
}
