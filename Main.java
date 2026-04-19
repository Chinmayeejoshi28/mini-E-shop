import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        ProductDAO productDAO = new ProductDAO();
        CartDAO cartDAO = new CartDAO();

        int userId;

        System.out.println("=== Welcome to E-Commerce App ===");
        System.out.println("1. Login\n2. Register");
        int ch = -1;
        while (ch != 1 && ch != 2) {
            try {
                ch = sc.nextInt();
                if (ch != 1 && ch != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // consume invalid input
            }
        }

        sc.nextLine();

        if (ch == 2) {
            System.out.print("Username: ");
            String u = sc.nextLine();

            System.out.print("Password: ");
            String p = sc.nextLine();

            userDAO.register(u, p);
        }

        System.out.print("Login Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        userId = userDAO.login(u, p);

        if (userId == -1) {
            System.out.println("Login Failed!");
            return;
        }

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = -1;
            while (choice < 1 || choice > 5) {
                try {
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter 1-5.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine();
                }
            }

            switch (choice) {

                case 1 -> productDAO.viewProducts();

                case 2 -> {
                    System.out.print("Enter Product ID: ");
                    int pid = -1;
                    while (pid <= 0) {
                        try {
                            pid = sc.nextInt();
                            if (pid <= 0) {
                                System.out.println("Product ID must be positive.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                        }
                    }

                    System.out.print("Quantity: ");
                    int qty = -1;
                    while (qty <= 0) {
                        try {
                            qty = sc.nextInt();
                            if (qty <= 0) {
                                System.out.println("Quantity must be positive.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                        }
                    }

                    cartDAO.addToCart(userId, pid, qty);
                }

                case 3 -> cartDAO.viewCart(userId);

                case 4 -> cartDAO.checkout(userId);

                case 5 -> System.exit(0);
            }
        }
    }
}