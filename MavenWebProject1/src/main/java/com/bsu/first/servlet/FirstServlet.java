package com.bsu.first.servlet;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    private static final Logger userLogger = LogManager.getLogger(FirstServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configurator.setRootLevel(Level.INFO);
        userLogger.info("The number provided is: " + request.getParameter("num"));
        response.setContentType("text/html");
        response.getWriter().print("The value was " + request.getParameter("num"));

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("num");
        int square = (int) Math.pow(Integer.parseInt(number), 2);

        Configurator.setRootLevel(Level.INFO);
        userLogger.info("Number: " + number);
        userLogger.info("Square: " + square);

        request.setAttribute("res", square);
        request.setAttribute("n1", Integer.parseInt(number));
        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);

    }
}
