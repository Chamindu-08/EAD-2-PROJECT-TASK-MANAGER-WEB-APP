document.addEventListener('DOMContentLoaded', function() {
    const container = document.getElementById('container');

    //signup
    const signupbtn = document.getElementById('signup');
    const name = document.getElementById('name');
    const email = document.getElementById('email');
    const contactNo = document.getElementById('contactNo');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    
    //login
    const signinForm = document.getElementById('signinForm');
    const loginEmail = document.getElementById('loginEmail');
    const loginPassword = document.getElementById('loginPassword');
    const invalidLogin = document.getElementById('invalidLogin');
    const loginbtn = document.getElementById('login');

    const toggleLogin = document.getElementById('togglelogin');
    const toggleSignup = document.getElementById('toggleregister');

    toggleSignup.addEventListener('click', () => {
        container.classList.add('active');
    });

    toggleLogin.addEventListener('click', () => {
        container.classList.remove('active');
    });

    signupbtn.addEventListener('click', function() {
        if (name.value === '' || email.value === '' || contactNo.value === '' || password.value === '' || confirmPassword.value === '') {
            alert('Please fill in all fields');
        }
        else if(password.value !== confirmPassword.value) {
            alert('Password does not match');
        }
        else {
            fetch('http://localhost:8080/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name.value,
                    email: email.value,
                    contact: contactNo.value,
                    password: password.value
                }),
                credentials: 'include'
            })
            .then(response => {
                if (response.status === 201) {
                    alert('User created successfully');
                    window.location.reload();
                }
            })
        }
    });

    loginbtn.addEventListener('click', function() {
        if (loginEmail.value === '' || loginPassword.value === '') {
            alert('Please fill in all fields');
            return;
        }

        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: loginEmail.value,
                password: loginPassword.value
            }),
            credentials: 'include'
        })
        .then(response => {
            if (response.status === 200) {
                window.location.href = './DashBoard.html';
            }
            else {
                invalidLogin.textContent = 'Invalid email or password';
            }
        })
        
    });

});