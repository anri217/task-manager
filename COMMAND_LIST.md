# Command List
This is a list of commands that are used to communicate between server and client.
_____
### Commands from Server to Client
+ GetAll. CommandId = 0. Content = List<Task>. This command is created to send all tasks stored in Journal to client.
+ Notification. CommandId = 1. Content = Task. This command is created to send to client a task that has expired the plannedDate.
+ Error. CommandId = 99. Content = String. This command is created to send to client a message about errors that occurred on server.
+ Disconnect. CommandId = 71. Content doesn't matter. This command is crated to close connection.
________________
### Command from Client to Server
+ GetAll. CommandId = 0. Content doesn't matter. This command is created to query server for all stored tasks.
+ AddTask. CommandId = 1. Content = Task. This command is created to add task to server Journal.
+ DeleteTask. CommandId = 2. Content = Task. This command is created to delete task from server Journal.
+ ChangeTask. CommandId = 3. Content = Task. This command is created to change task in server Journal.
+ CancelTask. CommandId = 4. Content = Task. This command is created to cancel task in server Journal.
+ Disconnect. CommandId = 5. Content  = current port. This command is created to notify server about disconnect.