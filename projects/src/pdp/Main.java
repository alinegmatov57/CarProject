package pdp;

import pdp.bean.ApiResponce;
import pdp.bean.Car;
import pdp.bean.User;
import pdp.db.DB;
import pdp.recourse.AuthRecourse;
import pdp.recourse.CarRecource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        showMenu();

    }
    private static void showMenu() {

        System.out.println("0. Exit");

        if (DB.session == null) {
            System.out.println("1. Register");
            System.out.println("2. Login");
        } else {
            System.out.println("3.Add car");
            System.out.println("4.show my cars");
            System.out.println("5.buy car");
            System.out.println("6.sell car");
            System.out.println("7. Logout");
        }


        int choice = scanner.nextInt();

        switch (choice) {
            case 0 -> {
                return;
            }
            case 1 -> {
                register();
            }
            case 2 -> {
                login();
            }
            case 7 -> {
                DB.session = null;
            }
            case 3 -> {
                addCar();
            }
            case 4 -> {
                showMyCars();
            }
            case 5->{
                buyCar();
            }
            case 6->{
                sellCar();
            }
        }

        showMenu();
    }

    private static void login() {
        System.out.println("Enter username:");
        String username=scanner.next();
        System.out.println("Enter password:");
        String password=scanner.next();
        AuthRecourse authRecourse=new AuthRecourse();
        User user=new User(username,password,0);
        ApiResponce login = authRecourse.login(user.getUsername(), user.getPassword());
        if (login.getCode().equals(200)){
            System.out.println("Successfully logined!");
            DB.session= (User) login.getData();
        }else {
            System.err.println("Error during login!");
        }
    }

    public static void register(){
        System.out.println("Enter username:");
        String username=scanner.next();
        System.out.println("Enter password:");
        String password=scanner.next();
        User user=new User(username,password,1000);
        AuthRecourse authRecourse=new AuthRecourse();
        ApiResponce register = authRecourse.register(user);
        if (register.getCode().equals(200)){
            System.out.println("Successfully registered!");
            DB.session=user;
        }else
        {
            System.err.println("Error!");
        }
    }
    public static void addCar(){
        System.out.println("Enter car name");
        String name=scanner.next();
        System.out.println("Enter car color");
        String color=scanner.next();
        System.out.println("Enter car price");
        double price=scanner.nextDouble();
        Car car=new Car(name,color,price,DB.session.getId(),false);
        CarRecource carRecource=new CarRecource();
        ApiResponce apiResponce = carRecource.addCar(car);
        if (apiResponce.getCode().equals(200)){
            System.out.println("Successfully added car!");
        }else {
            System.err.println("Error adding car!");
        }
        showMenu();
    }
    public static void showMyCars(){
        List <Car> list = DB.showMyCars();
        if (list.size()>0){
            for (Car car : list) {
                System.out.println(car.getId()+": "+car.getColor()+"  "+car.getName()+"  is in store  "+car.isInStore());
            }
        }else {
            System.err.println("no cars");
        }
    }
    public static void sellCar(){
        List<Car> cars = DB.showCarsForSale();
        for (Car car : cars) {
            System.out.println(car.getId()+": "+car.getColor()+"  "+car.getName()+"   "+car.getPrice()+" mln"+"  isInStore="+car.isInStore());
        }
        System.out.println("Choose car!");
        int chooseCar=scanner.nextInt();
        CarRecource carRecource=new CarRecource();
        ApiResponce apiResponce = carRecource.sellCar(chooseCar);
        if (apiResponce.getCode().equals(200)){
            System.out.println("Success");
        }else {
            System.err.println("Error!");
        }
    }
    public static void buyCar(){
        List<Car> cars = DB.showCarsForBuy();
        System.out.println(cars);
        System.out.println("Enter car id");
        int id=scanner.nextInt();
        DB.buyCar(id);
    }

}
