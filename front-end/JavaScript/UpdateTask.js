export default function updateTask() {
    const taskList = document.getElementById('taskList'); //select
    const btnSearchTask = document.getElementById('searchTask');

    const taskNameInput = document.getElementById('taskName');
    const taskDescriptionInput = document.getElementById('taskDescription');
    const taskDateInput = document.getElementById('taskDate');
    const taskPriorityInput = document.getElementById('taskPriority');
    const btnUpdateTask = document.getElementById('updateTask');

    fetch('http://localhost:8080/tasks',{
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

    btnUpdateTask.addEventListener('click', function() {
        fetch(`http://localhost:8080/tasks/${taskList.value}`,{
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials : 'include',
            body: JSON.stringify({
                name: taskNameInput.value,
                description: taskDescriptionInput.value,
                date: taskDateInput.value,
                priority: taskPriorityInput.value
            })
        })
        .then(response => {
            if(response.status === 200){
                alert('Task updated successfully');
            }
            else {
                alert('Failed to update task');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}