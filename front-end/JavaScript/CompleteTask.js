export default function completeTask(){
    const taskList = document.getElementById('taskList'); //select
    const btnSearchTask = document.getElementById('searchTask');

    const taskNameInput = document.getElementById('taskName');
    const taskDescriptionInput = document.getElementById('taskDescription');
    const taskDateInput = document.getElementById('taskDate');
    const taskPriorityInput = document.getElementById('taskPriority');

    const btnCompleteTask = document.getElementById('btnComplete');

    fetch('http://localhost:8080/tasks/nocomplete',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include'
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(task => {
            const taskItem = document.createElement('option');
            taskItem.value = task.id;
            taskItem.textContent = task.name;
            taskList.appendChild(taskItem);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });


    btnSearchTask.addEventListener('click', function() {
        fetch(`http://localhost:8080/tasks/${taskList.value}`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
        .then(response => response.json())
        .then(data => {
            taskNameInput.value = data.name;
            taskDescriptionInput.value = data.description;
            taskDateInput.value = data.date;
            taskPriorityInput.value = data.priority;
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });

    btnCompleteTask.addEventListener('click', function() {
        fetch(`http://localhost:8080/tasks/${taskList.value}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
        .then(response => {
            if(response.status === 200){
                alert('Task completed successfully');
            } 
            else {
                alert('Failed to complete task');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}