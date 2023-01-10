package manager;

import action.MyRegex;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ManagerProduct {

    MyRegex myRegex = new MyRegex();
    private int autoId;
    private ArrayList<Product> listProduct;

    public ManagerProduct() {
        listProduct = new ArrayList<>();
        if (listProduct.size() > 0) {
            autoId = (listProduct.get(listProduct.size() - 1).getId()) + 1;
        } else {
            autoId = 1;
        }
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void title() {
        System.out.printf("%-5s%-15s%-10s%-15s%-20s%s",
                "| ID", "|   NAME", "|   PRICE", "|   QUANTITY", "|   DESCRIBE", " |\n");
        System.out.println("-------------------------------------------------------------------");
    }

    public boolean checkIdToList(int id, ArrayList<Product> list) {
        boolean check = false;
        for (Product p : list) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public Product findById(Scanner scanner, String pattern) {
        Product product = null;
        int id = 0;
        String input;
        boolean checkRegex = false;
        if (listProduct.isEmpty()) {
            System.out.println("                     No Product");
        } else {
            do {
                display();
                System.out.print("Enter  Id : ");
                input = scanner.nextLine();
                if (myRegex.regex(input, pattern)) {
                    checkRegex = true;
                    id = Integer.parseInt(input);
                } else {
                    System.err.println("Malformed ID");
                    System.out.println("\nId Contains Only Numbers\n");
                }
            }
            while (!checkRegex);
            if (checkIdToList(id, listProduct)) {
                for (Product c : listProduct) {
                    if (c.getId() == id) {
                        product = c;
                        product.display();
                        break;
                    }
                }
            } else {
                System.err.println("                    Id Does Not Exist");
            }
        }
        return product;


    }

    public void display() {
        title();
        for (Product p : listProduct) {
            p.display();
        }
    }

    public Product create(Scanner scanner) {
        Product product;
        boolean check;
        String name;
        int quantity = 0;
        double price = 0;
        String sizeString;
        String quantityString;
        String priceString;
        String describe;
        System.out.println("\nCreate New Product...!");
        System.out.println("\nname : (3-15) characters ");
        do {
            System.out.print("Enter Name : ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nname : (3-15) characters ");
                check = false;
            }
        } while (!check);
        check = true;
        do {
            System.out.print("Enter Quantity : ");
            quantityString = scanner.nextLine();
            if (myRegex.regex(String.valueOf(quantityString), myRegex.getPatternDouble())) {
                quantity = Integer.parseInt(quantityString);
                check = true;
            } else {
                System.err.println("Malformed Quantity:");
                System.out.println("\nSize :contains only numbers\n ");
                check = false;
            }
        } while (!check);
        check = true;
        do {
            System.out.print("Enter Price : ");
            priceString = scanner.nextLine();
            if (myRegex.regex(String.valueOf(priceString), myRegex.getPatternDouble())) {
                price = Double.parseDouble(priceString);
                check = true;
            } else {
                System.err.println("Malformed Price:");
                System.out.println("\nPrice :contains only numbers\n ");
                check = false;
            }
        } while (!check);
        check = true;

        System.out.print("Enter Describe : ");
        describe = scanner.nextLine();
        product = new Product(autoId, name, price, quantity, describe);
        System.out.println("                                                Create Successful...! \n");
        title();
        product.display();
        return product;
    }

    public void add(Scanner scanner) {
        Product product = create(scanner);
        if (product != null) {
            listProduct.add(product);
            autoId++;
        }
    }

    public void update(Scanner scanner) {
        Product product = findById(scanner, myRegex.getPatternNumber());
        boolean check;
        String name;
        int size;
        int quantity;
        double price;
        String describe;
        System.out.println("\nUpdate Product...!\n");
        System.out.println("name : (3-15) characters ");
        do {
            System.out.println("UPDATE NAME : ");
            System.out.print(product.getName() + " UPDATE --> ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nname : (3-15) characters ");
                check = false;
            }
        } while (!check);
        product.setName(name);
        check = true;
        do {
            System.out.print("UPDATE QUANTITY  ");
            System.out.print(product.getQuantity() + " UPDATE --> ");
            quantity = Integer.parseInt(scanner.nextLine());
            if (myRegex.regex(String.valueOf(quantity), myRegex.getPatternDouble())) {
                check = true;
            } else {
                System.err.println("Malformed Quantity:");
                System.out.println("\nSize :contains only numbers\n ");
                check = false;
            }
        } while (!check);
        product.setQuantity(quantity);
        check = true;
        do {
            System.out.print("UPDATE PRICE ");
            System.out.print(product.getPrice() + " UPDATE --> ");
            price = Double.parseDouble(scanner.nextLine());
            if (myRegex.regex(String.valueOf(price), myRegex.getPatternDouble())) {
                check = true;
            } else {
                System.err.println("Malformed Price:");
                System.out.println("\nPrice :contains only numbers\n ");
                check = false;
            }
        } while (!check);
        product.setPrice(price);
        System.out.print("UPDATE DESCRIBE ");
        System.out.print(product.getDescription() + " UPDATE --> ");
        describe = scanner.nextLine();
        product.setDescription(describe);
        System.out.println("\n                                  Update Successful...! ");

    }

    public void delete(Scanner scanner) {
        Product product = findById(scanner, myRegex.getPatternNumber());
        System.out.println("Nhập ký y để xóa Sản Phẩm");
        System.out.println("Nhập ký tự khác không xóa");
        System.out.print("Nhập ký tự : ");
        String input = scanner.nextLine();
        if (input.equals("y")) {
            listProduct.remove(product);
            System.out.println("\n                                  Delate Successful...! ");
        } else {
            System.out.println("bạn đã không xóa sản phẩm");
        }
    }

    public void sortUp() {
        System.out.println("Giá Tăng Dần: ");
        listProduct.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
        display();
    }

    public void sortDow() {
        System.out.println("Giá Giảm Dần: ");
        listProduct.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() < o2.getPrice() ? 1 : -1;
            }
        });
        display();
    }

    public void findPriceMax() {
        double max = 0;
        for (Product p : listProduct) {
            if (p.getPrice() > max) {
                max = p.getPrice();
            }
        }
        for (Product p : listProduct) {
            if (p.getPrice() == max) {
                p.display();
            }
        }
    }

    public List<Product> readFile() {
        String path = "src\\file\\product.csv";
        ArrayList<Product> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                int id = Integer.parseInt(txt[0]);
                String name = txt[1];
                double price = Double.parseDouble(txt[2]);
                int quantity = Integer.parseInt(txt[3]);
                String detail = txt[4];
                list.add(new Product(id, name, price, quantity, detail));
            }
            br.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public void writeCsv(ArrayList<Product> list) {
        String path = "src\\file\\product.csv";
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Product product : list) {
                bw.write(product.toString());
                bw.newLine();

            }
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
