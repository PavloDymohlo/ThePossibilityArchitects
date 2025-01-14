# The Possibility Architects - Project Overview

## Project Description

The project was created specifically for the group "The Possibility Architects" on the platform [Mastermindy](https://mastermindy.com). The goal of the project is to randomly select a user who will perform a task, demonstrating it to the entire group in real-time.

## Workflow

1. **Main Page:**
    - Each group member selects their name and enters a task in the text input box.
    - After entering the task, the user clicks the **"SAVE"** button to submit it.

2. **Host Action:**
    - After saving the tasks, the host (who can be anyone in the group) clicks the **"Go to task"** button to proceed to the next page.

3. **Task and User Selection:**
    - On this page, the host clicks the **"Choose user"** button to randomly select a participant to perform a task.
    - The randomizer picks a user whose **"activity"** field is set to **true**.
    - After selection, the user picks a task number, and the host clicks the chosen task.

4. **Task Display:**
    - The task description and the name of the user who created the task appear on the screen.

5. **Round Progression:**
    - After each user and task selection, they will not participate until the end of the round.
    - When the **"Mix Task"** button is clicked, the random number of each task is changed.
    - No one in the group knows which task corresponds to which number.

## How It Works in Code

There are two tables in the database:
- **Users Table**
- **Tasks Table**

### Task Saving:
- When a user enters their task and clicks **"SAVE"**, the task text is encoded in **JSON format** on the server.
- The task is then stored in a **.txt file**, and the file is named after the user who entered the task.

### User Selection:
- When the **"Choose user"** button is clicked, the randomizer selects one of the users whose **"activity"** field is set to **true**.
- After selection, the **"activity"** flag is changed to **false**, and the participant will not take part again until the end of the round.

### Task Handling:
- When a task is opened, its **"activity"** field is also set to **false**, making it inactive until the end of the round.

### Task Mixing:
- When the **"Mix Task"** button is clicked, the randomizer changes the number of each task.

## Conclusion

This project aims to provide an interactive and randomized way for users to complete tasks and engage with their group in real-time, while maintaining a fair and dynamic experience for everyone involved.
