<?php
include 'DataBaseConnection/DataBaseConnection.php';

if (!$connection) {
    echo "Connection failed";
}

$sql = "SELECT TaskId, TaskName FROM task WHERE DueDate = CURRENT_DATE AND TaskStatus = 'PENDING' AND UserEmail = '$UserEmail'";
$result = mysqli_query($connection, $sql);

if ($result) {
    while ($row = mysqli_fetch_assoc($result)) {
        echo '<option value="' . $row['TaskId'] . '">' . $row['TaskName'] . '</option>';
    }
} else {
    echo "Error: " . mysqli_error($connection);
}

mysqli_free_result($result);
mysqli_close($connection);
