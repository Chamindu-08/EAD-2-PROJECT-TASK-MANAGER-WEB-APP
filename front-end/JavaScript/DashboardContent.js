export default function dashBoardContent() {
    const countCompleted = document.getElementById('countCompleted');
    const countPending = document.getElementById('countPending');
    const countOverdue = document.getElementById('countOverdue');

    const taskList = document.getElementById('taskList'); //ul

    fetch('http://localhost:8080/tasks',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include'
    })
    .then(response => response.json())
    .then(data => {
        let completed = 0;
        let pending = 0;
        let overdue = 0;

        data.forEach(task => {
            if(task.completed == true){
                completed++;
            }
            else if(task.completed === false){
                pending++;
            }

            // else if(task.completed === 'Overdue'){
            //     overdue++;
            // }

            const taskItem = document.createElement('li');
            const taskName = document.createElement('span');
            const taskID = document.createElement('span');

            taskName.textContent = task.name;
            taskID.textContent = task.id;

            taskItem.appendChild(taskName);


            const li = document.createElement('li');
            li.appendChild(taskName);
            li.appendChild(taskID);

            if(task.completed === true){
                li.style.color = 'green';
            }

            taskList.appendChild(li);
        });

        countCompleted.textContent = completed;
        countPending.textContent = pending;
        countOverdue.textContent = overdue;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
