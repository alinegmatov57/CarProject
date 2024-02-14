package pdp.db;

import pdp.bean.Car;
import pdp.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DB {
    public static final List<User> users=new ArrayList<>();
    public static final List<Car> cars=new ArrayList<>();
    public static User session=null;

    public static User registerUser(User user){
        if (checkUserExistByLogin(user.getUsername())){
            return null;
        }
        user.setId(users.size()+1);
        users.add(user);
        return user;
    }
    public static void userlist(){
        for (User user: users){
            System.out.println(user.getUsername());
        }
    }

    public static User login(String username,String password){
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static boolean checkUserExistByLogin(String username){
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        return false;
    }
    public static Car addCar(Car car){
        car.setId(cars.size());
        cars.add(car);
        return car;
    }
    public static List<Car> showMyCars(){
        List<Car> myCars=new ArrayList<>();
        for (Car car1 : cars) {
            if (car1.getUserId().equals(DB.session.getId())){
                myCars.add(car1);
            }
        }
        return myCars;
    }
    public static Car sellCar(Integer carId){
        for (Car car : cars) {
            if (car.getId().equals(carId)){
                if (car.getUserId().equals(session.getId())){
                    if (!car.isInStore()){
                        session.setBalance(car.getPrice());
//                        car.setUserId(null);
                        car.setInStore(true);
                        return car;
                    }
                }

            }
        }
        return null;

    }
    public static List<Car> showCarsForBuy(){
        List<Car> sellingCars=new ArrayList<>();
        for (Car car : cars) {
            if (car.isInStore()){
                if (!car.getUserId().equals(session.getId())){
                    sellingCars.add(car);
                }
            }
        }
        return sellingCars;
    }
    public static List<Car> showCarsForSale(){
        List<Car> carList=new ArrayList<>();
        for (Car car : cars) {
            if (!car.isInStore() && car.getUserId().equals(session.getId())){
                carList.add(car);
            }
        }
        return carList;
    }
    public static void carlist(){
        for (Car car : cars) {
            System.out.println(car.getName()+"  "+car.getUserId());
        }
    }
    public static void buyCar(Integer id){
        for (Car car : cars) {
            if (car.getId().equals(id) && car.isInStore()){
                User userById = getUserById(car.getUserId());
                if (session.getBalance()>=car.getPrice()){
                    if (userById!=null){
                        userById.setBalance((int) car.getPrice());
                        car.setUserId(session.getId());
                        car.setInStore(false);
                    }
                }else {
                    System.err.println("Not enough money!");
                }
            }
        }
       
        
    }
    public static User getUserById(Integer id){
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

}
