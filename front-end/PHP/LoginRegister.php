<?php
session_start();

$signupError = $loginError = "";

if ($_SERVER['REQUEST_METHOD'] == "POST") {
    //get database connection
    include 'DataBaseConnection/DataBaseConnection.php';

    //check connection
    if (!$connection) {
        echo "Connection failed";
    }

    if (isset($_POST['signup'])) {
        // Sign Up functionality
        $name = $_POST["name"];
        $email = $_POST["email"];
        $position = $_POST["position"];
        $contactNo = $_POST["contactNo"];
        $password = $_POST["password"];
        $confirmPassword = $_POST["confirmPassword"];

        // Check if passwords match
        if ($password !== $confirmPassword) {
            echo "<script>alert('Passwords do not match!');</script>";
        } else {
            // Check if email exists in database
            $check = "SELECT * FROM user WHERE UserEmail='$email'";
            $result = mysqli_query($connection, $check);

            if (mysqli_num_rows($result) > 0) {
                echo "<script>alert('Email already exists!');</script>";
            } else {
                
                //validate contact number
                if (!preg_match("/^[0-9]{10}+$/", $contactNo)) {
                    echo "<script>alert('Invalid Contact Number!');</script>";
                }

                //validate password and confirm password 8 characters long
                if (strlen($password) < 8 || strlen($confirmPassword) < 8) {
                    echo "<script>alert('Password must be at least 8 characters long.');</script>";
                }

                //validate email
                if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                    echo "<script>alert('Invalid email!');</script>";
                }

                // Insert new user into database
                $insert = "INSERT INTO user(UserEmail, UserName, UserPosition, UserPassword) VALUES ('$email','$name','$position','$password')";

                // Execute the query
                $result = mysqli_query($connection, $insert);

                if ($result) {
                    echo "<script>alert('User registered successfully!');</script>";
                    // Redirect to login page
                    header("Location: index.php");
                } else {
                    echo "<script>alert('Error registering user!');</script>";
                }
            }
        }
    }

    if (isset($_POST['login'])) {
        // Login functionality
        $useremail = $_POST["loginEmail"];
        $password = $_POST["loginPassword"];

        $loginError = "";
        $username = $position = "";

        $sql = "SELECT * FROM user WHERE UserEmail='$useremail' AND UserPassword='$password'";
        $result = mysqli_query($connection, $sql);

        if (mysqli_num_rows($result) > 0) {
            $row = mysqli_fetch_assoc($result);

            // set values to variables
            $useremail = $row['UserEmail'];
            $username = $row['UserName'];
            $position = $row['UserPosition'];

            // Set session variables
            $_SESSION['loggedin'] = true;
            $_SESSION['userEmail'] = $useremail;
            $_SESSION['userName'] = $username;
            $_SESSION['userPosition'] = $position;

            //cookie set for 30 minutes
            setcookie('UserEmail', $useremail, time() + (30 * 60), '/');
            setcookie('UserName', $username, time() + (30 * 60), '/');
            setcookie('UserPosition', $position, time() + (30 * 60), '/');

            header("Location: Dashboard.html");
            exit();
        } else {
            $loginError = "Invalid username or password!";
        }
    }
}
?>
