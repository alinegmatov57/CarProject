package pdp.recourse;

import pdp.bean.ApiResponce;
import pdp.bean.User;
import pdp.db.DB;

public class AuthRecourse {
    static {
        User user=new User("ali","123",1000);
        user.setId(1);
        DB.users.add(user);
    }
    public ApiResponce register(User bean){
        User user= DB.registerUser(bean);

        if(user==null){
            return new ApiResponce(400,"Error",user);
        }else {
            return new ApiResponce(200,"Success",user);
        }
    }
    public ApiResponce login(String username,String password){
        User user=DB.login(username,password);
        if(user==null){
            return new ApiResponce(400,"Error",user);
        }else {
            return new ApiResponce(200,"Success",user);
        }
    }
}
