package pdp.recourse;

import pdp.bean.ApiResponce;
import pdp.bean.Car;
import pdp.db.DB;

public class CarRecource {
    static {
        Car car=new Car("Cobalt","oq",146d,1,true);
        car.setId(0);
        DB.cars.add(car);
        car=new Car("Tahoe","qora",1120d,1,true);
        car.setId(1);
        DB.cars.add(car);
    }
    public ApiResponce addCar(Car car){
        Car car1 = DB.addCar(car);
        if (car!=null){
            return new ApiResponce(200,"Succesffully added car!",car1);
        }else {
            return new ApiResponce(403,"Car didn't add!",null);
        }
    }
    public ApiResponce sellCar(Integer carId){
        Car car = DB.sellCar(carId);
        if (car!=null){
            return new ApiResponce(200,"Car in store now!",car);
        }else {
            return new ApiResponce(403,"Error!",null);
        }
    }
//    public  ApiResponce showMyCar(){
//        Car car = DB.showMyCars();
//        if (car==null){
//            return new ApiResponce(404,"Cars not found!",null);
//        }else {
//            return new ApiResponce(200,"Success!",car);
//        }
//    }

}
