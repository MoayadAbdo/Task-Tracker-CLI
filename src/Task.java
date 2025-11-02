import java.time.LocalDate;
import java.util.Scanner;

public class Task {
    private int taskId;
    private String description;
    private String status;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt = LocalDate.now();

    public Task() {

    }

    public void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task ID: ");
        this.taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter task description: ");
        this.description = scanner.nextLine();

        System.out.print("Enter task status (e.g., Pending, Done, In Progress): ");
        this.status = scanner.nextLine();

        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();

        System.out.println("\n✅ Task added successfully!");
        System.out.println("ID: " + taskId);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Created At: " + createdAt);
    }
    public void updateTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Update Task ---");
        System.out.println("Current description: " + this.description);
        System.out.print("Enter new description (leave blank to keep current): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            this.description = newDescription;
        }

        System.out.println("Current status: " + this.status);
        System.out.print("Enter new status (leave blank to keep current): ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) {
            this.status = newStatus;
        }

        this.updatedAt = LocalDate.now();
        System.out.println("\n✅ Task updated successfully!");
        System.out.println("Updated At: " + this.updatedAt);
    }
    public void getInfo() {
        System.out.println("\n--- Task Information ---");
        System.out.println("Task ID: " + taskId);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Created At: " + createdAt);
        System.out.println("Last Updated At: " + updatedAt);
    }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }


    @Override
    public String toString() {
        return "\nID: " + taskId +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nCreated At: " + createdAt +
                "\nUpdated At: " + updatedAt;
    }
}
