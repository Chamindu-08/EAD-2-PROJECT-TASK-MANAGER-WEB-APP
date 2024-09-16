export default function addTask(){
    const taskNameInput = document.getElementById('taskName');
    const taskDescriptionInput = document.getElementById('taskDescription');
    const taskDateInput = document.getElementById('taskDate');
    const taskPriorityInput = document.getElementById('taskPriority');
    const btnAddTask = document.getElementById('addTask');

    btnAddTask.addEventListener('click', function() {
        if(taskNameInput.value === '' || taskDescriptionInput.value === '' || taskDateInput.value === '' || taskPriorityInput.value === ''){
            alert('Please fill in all fields');
            return;
        }

        fetch('http://localhost:8080/tasks/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: taskNameInput.value,
                description: taskDescriptionInput.value,
                date: taskDateInput.value,
                priority: taskPriorityInput.value
            }),
            credentials: 'include'
        })
        .then(response => {
            if(response.status === 201){
                alert('Task added successfully');
            } 
            else {
                alert('Failed to add task');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}