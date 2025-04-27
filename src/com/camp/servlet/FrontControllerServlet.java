package com.camp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	request.setCharacterEncoding("UTF-8");

        String cmd = request.getParameter("cmd");

        if (cmd == null || cmd.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameter: cmd");
            return;
        }

        Action action = ActionFactory.getAction(cmd);
        System.out.println("[FrontController] cmd 파라미터 = " + cmd);
        System.out.println("[FrontController] 생성된 Action = " + action.getClass().getName());

        System.out.println("[FrontController] 실행할 Action: " + action);
        String view = action.execute(request);

        if (view != null) {
        	 if (view.startsWith("redirect:")) {
                 String redirectUrl = view.substring("redirect:".length());
                 response.sendRedirect(redirectUrl);
                 return;
             }
            if (view.equals("jsonView")) {
                response.setContentType("application/json; charset=UTF-8");
                String result = (String) request.getAttribute("result");
                response.getWriter().write(result);
                return;
            }

            if (view.endsWith(".jsp")) {
                response.setContentType("text/html; charset=UTF-8");
            } else if (view.endsWith(".json")) {
                response.setContentType("application/json; charset=UTF-8");
            } else if (view.endsWith(".html")) {
                response.setContentType("text/html; charset=UTF-8");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/" + view);
            dispatcher.forward(request, response);
        }
    }
}
