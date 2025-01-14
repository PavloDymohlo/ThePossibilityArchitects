document.addEventListener('DOMContentLoaded', () => {
    const appContainer = document.getElementById('app');

    const modal = document.createElement('div');
    modal.classList.add('modal');
    document.body.appendChild(modal);

    const modalContent = document.createElement('div');
    modalContent.classList.add('modal-content');
    modal.appendChild(modalContent);

    const closeButton = document.createElement('button');
    closeButton.classList.add('close-modal');
    closeButton.innerHTML = '×';
    closeButton.onclick = () => {
        modal.style.display = 'none';
    };
    modalContent.appendChild(closeButton);

    modal.onclick = (e) => {
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    };

    fetch('/users/active-users')
        .then(response => response.json())
        .then(data => {
            const activeUsers = data[0];

            const userContainer = document.createElement('div');
            userContainer.classList.add('user-container');

            const title = document.createElement('h3');
            title.textContent = 'Active Users';
            userContainer.appendChild(title);

            if (activeUsers && activeUsers.length > 0) {
                activeUsers.forEach(user => {
                    const userElement = document.createElement('p');
                    userElement.textContent = user.userName;
                    userContainer.appendChild(userElement);
                });
            } else {
                const noUsersMessage = document.createElement('p');
                noUsersMessage.textContent = 'No active users found.';
                userContainer.appendChild(noUsersMessage);
            }

            const chooseUserButton = document.createElement('button');
            chooseUserButton.textContent = 'Choose user';

            const spinner = document.createElement('div');
            spinner.id = 'spinner';

            const userNameDisplay = document.createElement('p');
            userNameDisplay.id = 'userNameDisplay';

            chooseUserButton.addEventListener('click', () => {
                spinner.style.display = 'block';
                fetch('/users/livecoder')
                    .then(response => response.json())
                    .then(user => {
                        let countdown = 3;
                        const countdownElement = document.getElementById('countdown');
                        const userNameDisplay = document.getElementById('userNameDisplay');

                        const countdownInterval = setInterval(() => {
                            countdownElement.textContent = `Loading... ${countdown} seconds remaining`;
                            countdown--;

                            if (countdown < 0) {
                                clearInterval(countdownInterval);
                                countdownElement.style.display = 'none';
                                userNameDisplay.textContent = `Chosen user: ${user.userName}`;
                                userNameDisplay.style.display = 'block';
                            }
                        }, 1000);
                    })
                    .catch(error => {

                        const userBlock = document.getElementById('userBlock');
                        userBlock.innerHTML = 'An error occurred while fetching the live coder.';
                    });


            });

            userContainer.appendChild(chooseUserButton);
            userContainer.appendChild(spinner);
            userContainer.appendChild(userNameDisplay);
            appContainer.appendChild(userContainer);

            fetch('/task/active-task')
                .then(response => response.json())
                .then(data => {
                    const tasks = data[0];

                    const taskContainer = document.createElement('div');
                    taskContainer.classList.add('task-container');

                    const mixButton = document.createElement('button');
                    mixButton.textContent = 'Mix Tasks';
                    mixButton.style.backgroundColor = '#007bff';
                    mixButton.style.color = '#fff';

                    const successMessage = document.createElement('p');
                    successMessage.id = 'successMessage';

                    const mixSpinner = document.createElement('div');
                    mixSpinner.id = 'spinner';

                    mixButton.addEventListener('click', () => {
                        mixSpinner.style.display = 'block';
                        fetch('/task/mix', {method: 'POST'})
                            .then(response => response.text())
                            .then(response => {
                                setTimeout(() => {
                                    mixSpinner.style.display = 'none';
                                    successMessage.textContent = 'Завдання перемішано!';
                                    successMessage.style.display = 'block';

                                    setTimeout(() => {
                                        location.reload();
                                    }, 2000);
                                }, 500);
                            })
                            .catch(error => {
                                mixSpinner.style.display = 'none';
                                successMessage.textContent = 'An error occurred while mixing tasks.';
                                successMessage.style.display = 'block';
                            });
                    });

                    taskContainer.appendChild(mixButton);
                    taskContainer.appendChild(mixSpinner);
                    taskContainer.appendChild(successMessage);

                    if (tasks && tasks.length > 0) {
                        tasks.forEach(task => {
                            const taskButton = document.createElement('button');
                            taskButton.textContent = `Task Number: ${task.randomNumber}`;

                            taskButton.addEventListener('click', () => {
                                fetch(`/task/chosen-task?number=${task.randomNumber}`)
                                    .then(response => response.json())
                                    .then(data => {
                                        while (modalContent.childNodes.length > 1) {
                                            modalContent.removeChild(modalContent.lastChild);
                                        }

                                        const author = document.createElement('div');
                                        author.classList.add('modal-author');
                                        author.textContent = `Author: ${data.author}`;

                                        const contentParagraph = document.createElement('div');
                                        contentParagraph.classList.add('modal-task-content');
                                        contentParagraph.textContent = data.content;

                                        modalContent.appendChild(author);
                                        modalContent.appendChild(contentParagraph);
                                        modal.style.display = 'flex';
                                    })
                                    .catch(error => {
                                        while (modalContent.childNodes.length > 1) {
                                            modalContent.removeChild(modalContent.lastChild);
                                        }

                                        const errorMessage = document.createElement('p');
                                        errorMessage.textContent = 'An error occurred while fetching the chosen task.';
                                        errorMessage.style.color = 'red';
                                        modalContent.appendChild(errorMessage);
                                        modal.style.display = 'flex';
                                    });
                            });

                            taskContainer.appendChild(taskButton);
                        });
                    } else {
                        const noTasksMessage = document.createElement('p');
                        noTasksMessage.textContent = 'No active tasks found.';
                        taskContainer.appendChild(noTasksMessage);
                    }

                    appContainer.appendChild(taskContainer);
                })
                .catch(error => {
                    console.error('Error fetching active tasks:', error);
                    const errorMessage = document.createElement('p');
                    errorMessage.textContent = 'An error occurred while fetching active tasks.';
                    errorMessage.style.color = 'red';
                    document.body.appendChild(errorMessage);
                });
        })
        .catch(error => {
            console.error('Error fetching active users:', error);
            const errorMessage = document.createElement('p');
            errorMessage.textContent = 'An error occurred while fetching active users.';
            errorMessage.style.color = 'red';
            document.body.appendChild(errorMessage);
        });
});