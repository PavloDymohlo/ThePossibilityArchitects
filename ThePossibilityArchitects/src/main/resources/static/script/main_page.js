document.addEventListener('DOMContentLoaded', () => {
    const goToTaskButton = document.createElement('button');
    goToTaskButton.textContent = 'Go to task';
    goToTaskButton.className = 'go-to-task-button';

    goToTaskButton.addEventListener('click', () => {
        window.location.href = '/task-page';
    });

    document.body.appendChild(goToTaskButton);

    fetch('/users/all-users')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const users = data[0];

            const mainContainer = document.createElement('div');
            mainContainer.className = 'main-container';

            const userContainer = document.createElement('div');
            userContainer.className = 'user-container';

            const userMenu = document.createElement('div');
            userMenu.className = 'user-menu';

            const userNameHeader = document.createElement('h2');
            userNameHeader.className = 'user-name-header';

            const textArea = document.createElement('textarea');
            textArea.className = 'text-area';

            const saveButton = document.createElement('button');
            saveButton.textContent = 'SAVE';
            saveButton.className = 'save-button';

            saveButton.addEventListener('click', () => {
                const userName = userNameHeader.textContent;
                const textContent = textArea.value;

                if (!userName) {
                    alert('No user selected!');
                    return;
                }

                fetch('/users/save-text', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        userName: userName,
                        taskText: textContent
                    }),
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(data => {
                        alert(data.message || 'Text saved successfully!');
                    })
                    .catch(error => {
                        console.error('Error saving text:', error);
                        alert('An error occurred while saving the text.');
                    });
            });

            userMenu.appendChild(userNameHeader);
            userMenu.appendChild(textArea);
            userMenu.appendChild(saveButton);

            users.forEach(user => {
                const userButton = document.createElement('button');
                userButton.textContent = user.userName;
                userButton.className = 'user-button';

                userButton.addEventListener('click', () => {
                    userNameHeader.textContent = user.userName;
                    textArea.value = '';
                });

                userContainer.appendChild(userButton);
            });

            mainContainer.appendChild(userContainer);
            mainContainer.appendChild(userMenu);
            document.body.appendChild(mainContainer);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
});