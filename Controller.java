package CRUD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
    public ArrayList<User> users = new ArrayList<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        Controller.runCRUD(controller);
    }


    public static void runCRUD(Controller controller) throws IOException {
            controller.printAction();
         while (!(reader.readLine()).equals("exit")){
            try {
                controller.chooseAction(Action.valueOf(reader.readLine()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void chooseAction(Action action) throws IOException {
        switch (action) {
            case CREATE:
                createUser();
                break;
            case READ_ALL_USERS:
                if (users.size() == 0) {
                    printEmptyList();
                }
                for (User user : users) {
                    printUser(user);
                }
                break;
            case READ_BY_ID:
                readUserById();
                break;
            case EDIT_BY_ID:
                updateUserById();
                break;
            case DELETE:
                deleteUser();
                break;
        }
    }

    public void createUser() throws IOException {
        int id = createId();
        System.out.println("Type email:");
        String email = reader.readLine();
        System.out.println("Type name:");
        String name = reader.readLine();
        User user = new User(id, email, name);
        printUser(user);
        users.add(user);
    }

    public int createId() {
        return (int) ((Math.random() * 100000) + 1000);
    }

    public ArrayList<User> deleteUser() throws IOException {
        if (users.size() == 0) {
            printEmptyList();
            return users;
        }
        System.out.println("What user do you want to delete? Input index: ");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("[" + i + "] -" + users.get(i));
        }
        int input = Integer.parseInt(reader.readLine());
        if (input < 0 || input >= users.size()) {
            return deleteUser();
        }
        users.remove(input);
        return users;
    }

    public void readUserById() throws IOException {
        if (users.size() == 0) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Choose and type id:");
            for (User user : users) {
                System.out.println(user.getId());
            }
            int input = Integer.parseInt(reader.readLine());
            for (User user : users) {
                if (user.getId() == input) {
                    printUser(user);
                }
            }
        }
    }

    public void updateUserById() throws IOException {
        if (users.size() == 0) {
            printEmptyList();
            return;
        }
        System.out.println("Choose and input id:");
        for (User user : users) {
            System.out.println(user.getId());
        }
        int input = Integer.parseInt(reader.readLine());
        for (User user : users) {
            if (user.getId() == input) {
                System.out.println("Input new user's email");
                user.setEmail(reader.readLine());

                System.out.println("Input new user's name");
                user.setName(reader.readLine());
            }
        }
    }
    public void printUser(User user){
        System.out.println(user.toString());
    }

    public void printAction(){
        System.out.println("CREATE,\n" +
                "    READ_ALL_USERS,\n" +
                "    READ_BY_ID,\n" +
                "    EDIT_BY_ID,\n" +
                "    DELETE");
    }
    public void printEmptyList(){
        System.out.println("The list is empty!");
    }
}


