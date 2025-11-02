# 🧩 Task Tracker CLI

**Task Tracker** is a simple Command Line Interface (CLI) application that helps you manage and organize your daily tasks directly from your terminal.  
You can **add**, **update**, **delete**, and **list** tasks — and mark them as *todo*, *in progress*, or *done*.

This project reinforces your programming fundamentals:
- Working with the filesystem  
- Handling user input  
- Managing JSON data manually  
- Building a CLI-based workflow  

---

## 🚀 Features

The Task Tracker allows you to:

✅ Add new tasks  
✅ Update existing tasks  
✅ Delete tasks  
✅ Mark tasks as **in progress** or **done**  
✅ List all tasks or filter by status  

---

## 🧠 Task Properties

Each task is represented as a JSON object with the following properties:

| Property | Type | Description |
|-----------|------|-------------|
| `id` | Integer | Unique identifier for each task |
| `description` | String | Short description of the task |
| `status` | String | Can be `"todo"`, `"in-progress"`, or `"done"` |
| `createdAt` | String | Date when the task was created |
| `updatedAt` | String | Date when the task was last updated |

### Example JSON structure
```json
[
  {
    "taskId": 1,
    "description": "Finish homework",
    "status": "todo",
    "createdAt": "2025-11-02",
    "updatedAt": "2025-11-02"
  }
]



https://roadmap.sh/projects/task-tracker
