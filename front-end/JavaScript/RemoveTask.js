export default function removeTask() {
    const taskList = document.getElementById('taskList'); //select
    const btnSearchTask = document.getElementById('searchTask');
    const taskNameInput = document.getElementById('taskName');
    const taskDescriptionInput = document.getElementById('taskDescription');
    const taskDateInput = document.getElementById('taskDate');
    const taskPriorityInput = document.getElementById('taskPriority');
    const btnRemoveTask = document.getElementById('removeButton');


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

    btnRemoveTask.addEventListener('click', function() {
        fetch(`http://localhost:8080/tasks/${taskList.value}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
        .then(response => {
            if(response.status === 200){
                alert('Task removed successfully');
                taskNameInput.value = '';
                taskDescriptionInput.value = '';
                taskDateInput.value = '';
                taskPriorityInput.value = '';
                taskList.innerHTML = '';
            }
            else {
                alert('Error removing task');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}