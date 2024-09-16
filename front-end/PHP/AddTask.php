<?php
// Check cookie
if(isset($_COOKIE['UserEmail'])){
    $UserEmail = $_COOKIE['UserEmail'];
    $UserName = $_COOKIE['UserName'];
    $UserPosition = $_COOKIE['UserPosition'];
} else {
    // Redirect to login page if cookie not set
    echo '<script>
            var confirmMsg = confirm("Your session has timed out. Please log in again.");
            if (confirmMsg) {
                window.location.href = "LoginAndRegister.php";
            }
        </script>';
    exit();
}

// Check if form is submitted
if(isset($_POST['addTask'])){
    // Connect to database
    include 'DataBaseConnection/DataBaseConnection.php';

    // Get form data
    $taskName = $_POST['taskName'];
    $taskDescription = $_POST['taskDescription'];
    $taskDate = $_POST['taskDate'];
    $taskPriority = $_POST['taskPriority'];

    // Validate date (current or future date)
    $currentDate = date("Y-m-d");
    if($taskDate < $currentDate){
        echo '<script>
                alert("Please select a future date.");
                window.location.href = "AddTask.php";
            </script>';
        exit();
    }

    // Get taskId from the database
    $sql = "SELECT MAX(taskId) AS taskId FROM task";
    $result = mysqli_query($connection, $sql);
    if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
            $taskId = $row['taskId'] + 1;
        }
    } else {
        $taskId = 1;
    }

    // Insert data into database
    $sql = "INSERT INTO task (TaskId, TaskName, TaskDescription, TaskPriority, DueDate, TaskStatus, UserEmail) 
            VALUES ('$taskId', '$taskName', '$taskDescription', '$taskPriority', '$taskDate', 'PENDING', '$UserEmail')";
    $result = mysqli_query($connection, $sql);

    if($result){
        echo '<script>
                alert("Task added successfully.");
                window.location.href = "Dashboard.php";
            </script>';
    } else {
        echo '<script>
                alert("Failed to add task.");
                window.location.href = "AddTask.php";
            </script>';
    }

    // Close connection
    mysqli_close($connection);
}
?>
