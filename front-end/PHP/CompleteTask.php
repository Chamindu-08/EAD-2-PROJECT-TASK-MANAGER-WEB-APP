<?php
// Check cookie
if (isset($_COOKIE['UserEmail'])) {
    $UserEmail = $_COOKIE['UserEmail'];
    $UserName = $_COOKIE['UserName'];
    $UserPosition = $_COOKIE['UserPosition'];
} else {
    // Redirect to login page
    echo '<script>
            var confirmMsg = confirm("Your session has timed out. Please log in again.");
            if (confirmMsg) {
                window.location.href = "LoginAndRegister.php";
            }
          </script>';
    exit();
}

// Initialize variables
$TaskName = $TaskDescription = $DueDate = $TaskPriority = '';
$TaskId = '';

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['taskPriority'])) {
    // Database connection
    include 'DataBaseConnection/DataBaseConnection.php';

    if (!$connection) {
        echo "Connection failed";
    }

    $TaskId = $_POST['taskPriority'];

    // Query to retrieve task details
    $sql = "SELECT TaskName, TaskDescription, DueDate, TaskPriority FROM task WHERE TaskId = '$TaskId'";
    $result = mysqli_query($connection, $sql);

    if ($result) {
        if ($row = mysqli_fetch_assoc($result)) {
            $TaskName = $row['TaskName'];
            $TaskDescription = $row['TaskDescription'];
            $DueDate = $row['DueDate'];
            $TaskPriority = $row['TaskPriority'];
        }
    } else {
        echo "Error: " . mysqli_error($connection);
    }

    // Close the database connection
    mysqli_close($connection);
}

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['taskId'])) {
    // Database connection
    include 'DataBaseConnection/DataBaseConnection.php';

    if (!$connection) {
        echo "Connection failed";
    }

    $TaskId = $_POST['taskId'];

    // Query to remove task
    $sql = "UPDATE task SET TaskStatus='COMPLETED' WHERE TaskId='$TaskId'";
    $result = mysqli_query($connection, $sql);

    if ($result) {
        echo "<script>alert('Task complete successfully!');</script>";
        // Clear the displayed task information
        $TaskName = $TaskDescription = $DueDate = $TaskPriority = '';
    } else {
        echo "Error: " . mysqli_error($connection);
    }

    // Close the database connection
    mysqli_close($connection);
}
?>
