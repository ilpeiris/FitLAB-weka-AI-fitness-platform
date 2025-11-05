package com.fitlife.servlets;


import com.fitlife.User;
import com.fitlife.Goal;
import com.fitlife.dao.GoalDAO;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/goals")
public class GoalServlet extends HttpServlet {

    private GoalDAO goalDAO;

    @Override
    public void init() {
        goalDAO = new GoalDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

  
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
          
            int goalId = Integer.parseInt(request.getParameter("id"));
            goalDAO.deleteGoal(goalId);
           
            response.sendRedirect("goals"); 

        } else {
          
            List<Goal> allGoals = goalDAO.getGoalsByUserId(user.getUserId());

           
            request.setAttribute("goalList", allGoals);
            request.getRequestDispatcher("goals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

       
        String goalType = request.getParameter("goalType");
        double targetValue = Double.parseDouble(request.getParameter("targetValue"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

       
        Goal newGoal = new Goal();
        newGoal.setUserId(user.getUserId());
        newGoal.setGoalType(goalType);
        newGoal.setTargetValue(targetValue);
        newGoal.setStartDate(startDate);
        newGoal.setEndDate(endDate);

     
        goalDAO.addGoal(newGoal);

       
        response.sendRedirect("goals");
    }
}