<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Dashboard</title>
    <style>
        /* Simple styling for our navigation */
        nav { background-color: #f0f0f0; padding: 10px; }
        nav a { margin-right: 15px; }
        .stats { display: flex; gap: 20px; }
        .stat-box { border: 1px solid #ccc; padding: 10px; }
    </style>
</head>
<body>

    <nav>
        <a href="dashboard">Dashboard</a>
        <a href="workouts">Workouts</a>
        <a href="profile">Profile</a>
        <a href="logout">Logout</a>
    </nav>

    <h1>Welcome, ${sessionScope.user.username}!</h1>

    <h2>Your Activity Summary</h2>

    <div class="stats">
        <div class="stat-box">
            <strong>Total Workouts</strong>
            <p>${totalWorkouts}</p>
        </div>
        <div class="stat-box">
            <strong>Total Calories Burned</strong>
            <p>${totalCalories}</p>
        </div>
        <div class="stat-box">
            <strong>Total Distance (km)</strong>
            <p>${totalDistance}</p>
        </div>
    </div>

    <div id="chart-container" style="width:100%; max-width:600px; margin-top: 20px;">
        <h2>Your Progress</h2>
        <p>(Charts will be displayed here)</p>
    </div>

</body>
</html>