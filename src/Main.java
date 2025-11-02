import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "tasks.json";
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks(); // read from file

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to my Task Tracker!");
        System.out.println("Please enter the number of:");
        System.out.println("1-Add a new Task\t 2-Update a Task\t 3-Get info about all Tasks\t 4-End the program");

        int chooseTask = input.nextInt();
        input.nextLine();

        if (chooseTask == 1) {
            Task newTask = new Task();
            newTask.addTask();
            tasks.add(newTask);
            saveTasks();
        }
        else if (chooseTask == 2) {
            System.out.print("Enter task ID to update: ");
            int id = input.nextInt();
            input.nextLine();

            Task t = findTaskById(id);
            if (t == null) {
                System.out.println("❌ Task not found.");
            } else {
                t.updateTask();
                saveTasks();
            }
        }
        else if (chooseTask == 3) {
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("\n📋 All Tasks:");
                for (Task t : tasks) {
                    System.out.println(t);
                }
            }
        }
        else if (chooseTask == 4) {
            System.out.println("👋 Program ended. Goodbye!");
        }
        else {
            System.out.println("INVALID, PLEASE ENTER A VALID NUMBER");
        }

        input.close();
    }

    // ===========================================================
    // 🔹 Save tasks to JSON file manually
    // ===========================================================
    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                writer.write("  {\n");
                writer.write("    \"taskId\": " + t.getTaskId() + ",\n");
                writer.write("    \"description\": \"" + t.getDescription() + "\",\n");
                writer.write("    \"status\": \"" + t.getStatus() + "\",\n");
                writer.write("    \"createdAt\": \"" + t.getCreatedAt() + "\",\n");
                writer.write("    \"updatedAt\": \"" + t.getUpdatedAt() + "\"\n");
                writer.write("  }");
                if (i < tasks.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("💾 Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // ===========================================================
    // 🔹 Load tasks from JSON file manually
    // ===========================================================
    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
                    w.write("[]");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line.trim());

            String content = sb.toString();
            if (content.length() < 3) return; // empty

            content = content.substring(1, content.length() - 1); // remove [ ]
            String[] jsonTasks = content.split("\\},\\s*\\{");

            for (String json : jsonTasks) {
                if (!json.startsWith("{")) json = "{" + json;
                if (!json.endsWith("}")) json = json + "}";

                // Simple manual parsing (since no Gson)
                Task task = parseTaskFromJson(json);
                if (task != null) tasks.add(task);
            }
            System.out.println("📂 Loaded " + tasks.size() + " tasks from file.");
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // ===========================================================
    // 🔹 Find a task by ID
    // ===========================================================
    private static Task findTaskById(int id) {
        for (Task t : tasks) {
            if (t.getTaskId() == id) return t;
        }
        return null;
    }

    // ===========================================================
    // 🔹 Parse JSON manually into a Task object
    // ===========================================================
    private static Task parseTaskFromJson(String json) {
        try {
            json = json.replace("{", "").replace("}", "").replace("\"", "");
            String[] parts = json.split(",");

            int id = Integer.parseInt(parts[0].split(":")[1].trim());
            String desc = parts[1].split(":")[1].trim();
            String status = parts[2].split(":")[1].trim();
            String createdAt = parts[3].split(":")[1].trim();
            String updatedAt = parts[4].split(":")[1].trim();

            Task task = new Task();
            task.setTaskId(id);
            task.setDescription(desc);
            task.setStatus(status);
            task.setCreatedAt(java.time.LocalDate.parse(createdAt));
            task.setUpdatedAt(java.time.LocalDate.parse(updatedAt));

            return task;
        } catch (Exception e) {
            System.out.println("Error parsing task: " + e.getMessage());
            return null;
        }
    }
}
