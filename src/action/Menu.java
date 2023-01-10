package action;

import manager.ManagerProduct;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    MyRegex myRegex = new MyRegex();
    ManagerProduct managerProduct = new ManagerProduct();

    public void begin(Scanner scanner) {
        boolean check = false;
        int choice = -1;
        String input;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Xem Danh Sách");
                System.out.println("2. Thêm Mới");
                System.out.println("3. Cập Nhật");
                System.out.println("4. Xóa");
                System.out.println("5. Sắp Xếp");
                System.out.println("6. Tìm Giá Đắt Nhất");
                System.out.println("7. Đọc Từ File");
                System.out.println("8. Ghi Vào File");
                System.out.println("----------------");
                System.out.println("0. Exit :");
                System.out.println("----------------");
                System.out.println("Enter Choice Menu From 0 -> 8 ");
                System.out.print("Enter Choice: ");
                input = scanner.nextLine();
                if (myRegex.regex(input, myRegex.getPatternBegin())) {
                    choice = Integer.parseInt(input);
                    check = true;
                } else {
                    System.err.println("                         Incorrect Choice, Please Re-Enter");
                    System.out.println("\n");
                }
            } while (!check);

            switch (choice) {
                case 1:
                    managerProduct.display();
                    break;
                case 2:
                    managerProduct.add(scanner);
                    break;
                case 3:
                    managerProduct.update(scanner);
                    break;
                case 4:
                    managerProduct.delete(scanner);
                    break;
                case 5:
                    System.out.println("Sap Xep");
                    sort(scanner);
                    break;
                case 6:
                    System.out.println("Sản Phẩm Có Giá Lớn Nhất");
                    managerProduct.findPriceMax();
                    break;
                case 7:
                    System.out.println("Đọc Từ File");

                    managerProduct.setListProduct((ArrayList<Product>) managerProduct.readFile());
                    break;
                case 8:
                    System.out.println("Ghi Vào File");
                    managerProduct.writeCsv(managerProduct.getListProduct());
                    break;
            }
        } while (choice != 0);
    }

    public void sort(Scanner scanner) {
        String patternBegin = "^[1-3]{1}$";
        boolean check = false;
        int choice = -1;
        String input;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Giá Tăng Dần");
                System.out.println("2. Giá Giảm Giần");
                System.out.println("3. Quay Lại");
                input = scanner.nextLine();
                if (myRegex.regex(input, patternBegin)) {
                    choice = Integer.parseInt(input);
                    check = true;
                } else {
                    System.err.println("                         Incorrect Choice, Please Re-Enter");
                    System.out.println("\n");
                }
            } while (!check);
            switch (choice) {
                case 1:
                    System.out.println("Giá Tăng Dần");
                    managerProduct.sortUp();
                    break;
                case 2:
                    System.out.println("Giá Giảm Dần");
                    managerProduct.sortDow();
                    break;
            }

        }while(choice !=3);
    }
}