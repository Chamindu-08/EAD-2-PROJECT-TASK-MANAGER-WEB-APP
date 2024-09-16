document.addEventListener('DOMContentLoaded', function() {
    const root = document.querySelector('.root');
    const pageName = document.getElementById('pageName');
    const username = document.getElementById('username');

    const dashboardContentBtn = document.getElementById('btnDashboard');
    const addTaskBtn = document.getElementById('btnAddTask');
    const completeTaskBtn = document.getElementById('btnCompleteTask');
    const updateTaskBtn = document.getElementById('btnUpdateTask');
    const removeTaskBtn = document.getElementById('btnRemoveTask');
    const profileBtn = document.getElementById('btnProfile');
    const logoutBtn = document.getElementById('bntLogout');

    const jsFiles = {
        DashboardContent : './JavaScript/DashboardContent.js', 
        AddTask : './JavaScript/AddTask.js', 
        CompleteTask : './JavaScript/CompleteTask.js', 
        UpdateTask : './JavaScript/UpdateTask.js', 
        RemoveTask : './JavaScript/RemoveTask.js', 
        ViewProfile : './JavaScript/ViewProfile.js'
    };
     


    dashboardContentBtn.addEventListener('click', function() {
        pageName.textContent = 'Dashboard';
        removeOtherActiveBtns(dashboardContentBtn);
        dashboardContentBtn.classList.add('active');

        fetch('./Includes/DashBoardContent.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.DashboardContent);
            import('./DashboardContent.js')
            .then(module => {
                module.default();
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });


    addTaskBtn.addEventListener('click', function() {
        pageName.textContent = 'Add Task';
        removeOtherActiveBtns(addTaskBtn);
        addTaskBtn.classList.add('active');

        fetch('./Includes/AddTask.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.AddTask);
            import('./AddTask.js')
            .then(module => {
                module.default();
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    completeTaskBtn.addEventListener('click', function() {
        pageName.textContent = 'Complete Task';
        removeOtherActiveBtns(completeTaskBtn);
        completeTaskBtn.classList.add('active');

        fetch('./Includes/CompleteTask.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.CompleteTask);
            import('./CompleteTask.js')
            .then(module => {
                module.default();
            })
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    updateTaskBtn.addEventListener('click', function() {
        pageName.textContent = 'Update Task';
        removeOtherActiveBtns(updateTaskBtn);
        updateTaskBtn.classList.add('active');

        fetch('./Includes/UpdateTask.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.UpdateTask);
            import('./UpdateTask.js')
            .then(module => {
                module.default();
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    removeTaskBtn.addEventListener('click', function() {
        pageName.textContent = 'Remove Task';
        removeOtherActiveBtns(removeTaskBtn);
        removeTaskBtn.classList.add('active');

        fetch('./Includes/RemoveTask.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.RemoveTask);
            import('./RemoveTask.js')
            .then(module => {
                module.default();
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    profileBtn.addEventListener('click', function() {
        pageName.textContent = 'Profile';
        removeOtherActiveBtns(profileBtn);
        profileBtn.classList.add('active');

        fetch('./Includes/ViewProfile.html')
        .then(response => response.text())
        .then(data => {
            root.innerHTML = data;
            // addJsToPage(jsFiles.ViewProfile);
            import('./ViewProfile.js')
            .then(module => {
                module.default();
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    logoutBtn.addEventListener('click', function() {
        fetch('http://localhost:8080/auth/logout',{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
        .then(response => {
            if(response.status === 200){
                window.location.href = './LoginRegister.html';
            }
            else {
                alert('Error logging out');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    dashboardContentBtn.click();


    function removeOtherActiveBtns(current){
        const buttons = [dashboardContentBtn, addTaskBtn, completeTaskBtn, updateTaskBtn, removeTaskBtn, profileBtn];
        buttons.forEach(button => {
            if(button !== current){
                button.classList.remove('active');
            }
        });
    }

    function addJsToPage(src){
        const script = document.createElement('script');
        script.src = src;
        script.async = true;
        script.type = 'text/javascript';

        for(const key in jsFiles){
            if(document.querySelector(`script[src="${jsFiles[key]}"]`)){
                document.querySelector(`script[src="${jsFiles[key]}"]`).remove();
            }
        }

        document.head.appendChild(script);
    }




    //get user details
    fetch('http://localhost:8080/users/current',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include'
    })
    .then(response => response.json())
    .then(data => {
        username.textContent = data.name;
    })
    .catch(error => {
        console.error('Error:', error);
    });

});