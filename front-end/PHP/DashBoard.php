<?php
//check cookie
// if (isset($_COOKIE['UserEmail'])) {
//     $UserEmail = $_COOKIE['UserEmail'];
//     $UserName = $_COOKIE['UserName'];
//     $UserPosition = $_COOKIE['UserPosition'];
// } else {
//     //redirect to login page
//     echo '<script>
//             var confirmMsg = confirm("Your session has timed out. Please log in again.");
//             if (confirmMsg) {
//                 window.location.href = "LoginAndRegister.php";
//             }
//         </script>';
//     exit();
// }

//calculate task count
include 'DataBaseConnection/DataBaseConnection.php';

//get completed tasks
$sql = "SELECT COUNT(TaskStatus) AS TaskCompleted FROM task WHERE TaskStatus = 'COMPLETED' AND UserEmail = '$UserEmail' AND DueDate = CURDATE()";
$result = mysqli_query($connection, $sql);
$taskCompleted = mysqli_fetch_assoc($result)['TaskCompleted'] ?? 0;

//get pending tasks
$sql = "SELECT COUNT(TaskStatus) AS TaskPending FROM task WHERE TaskStatus = 'PENDING' AND UserEmail = '$UserEmail' AND DueDate = CURDATE()";
$result = mysqli_query($connection, $sql);
$taskPending = mysqli_fetch_assoc($result)['TaskPending'] ?? 0;

//get overdue tasks
$sql = "SELECT COUNT(TaskStatus) AS TaskOverdue FROM task WHERE TaskStatus = 'OVERDUE' AND UserEmail = '$UserEmail' AND DueDate = CURDATE()";
$result = mysqli_query($connection, $sql);
$taskOverdue = mysqli_fetch_assoc($result)['TaskOverdue'] ?? 0;

mysqli_close($connection);

//LinkedList class to manage tasks
class Node {
    public $TaskName, $TaskStatus, $TaskPriority, $Next;
    public function __construct($TaskName, $TaskStatus, $TaskPriority) {
        $this->TaskName = $TaskName;
        $this->TaskStatus = $TaskStatus;
        $this->TaskPriority = $TaskPriority;
        $this->Next = null;
    }
}

class LinkedList {
    public $Head;
    public function __construct() {
        $this->Head = null;
    }

    public function Add($taskName, $taskStatus, $taskPriority) {
        $newNode = new Node($taskName, $taskStatus, $taskPriority);
        if ($this->Head == null) {
            $this->Head = $newNode;
        } else {
            $current = $this->Head;
            while ($current->Next != null) {
                $current = $current->Next;
            }
            $current->Next = $newNode;
        }
    }

    public function Display() {
        $current = $this->Head;
        while ($current != null) {
            // Task status icon
            $taskIcon = $current->TaskStatus == 'COMPLETED' ? 
                        '<span class="tasksIcon done"><span class="material-symbols-outlined">check</span></span>' :
                        '<span class="tasksIcon pending"><span class="material-symbols-outlined">pending</span></span>';

            // Task priority icon
            $taskStar = $current->TaskPriority == 'High' ?
                        '<span class="tasksStar full"><span class="material-symbols-outlined">star</span></span>' :
                        '<span class="tasksStar"><span class="material-symbols-outlined">star</span></span>';

            echo '<li>
                    <span class="tasksIconName">
                        ' . $taskIcon . '
                        <span class="tasksName">' . $current->TaskName . '</span>
                    </span>
                    ' . $taskStar . '
                </li>';
            $current = $current->Next;
        }
    }
}

// Task list display
$list = new LinkedList();
include 'DataBaseConnection/DataBaseConnection.php';
$sql = "SELECT TaskName, TaskStatus, TaskPriority FROM task WHERE UserEmail = '$UserEmail' AND DueDate = CURDATE()";
$result = mysqli_query($connection, $sql);
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $list->Add($row['TaskName'], $row['TaskStatus'], $row['TaskPriority']);
    }
}
mysqli_close($connection);

?>