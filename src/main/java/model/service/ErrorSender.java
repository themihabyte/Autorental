package model.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorSender {
    public void sendErrorToJSP(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               String errorMessage, String URI) throws ServletException, IOException {
        httpServletRequest.setAttribute("error_message", errorMessage);
        httpServletRequest.getRequestDispatcher(URI).forward(httpServletRequest,
                httpServletResponse);
    }
}
