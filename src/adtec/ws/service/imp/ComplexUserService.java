package adtec.ws.service.imp;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import adtec.ws.model.User;
import adtec.ws.service.IComplexUserService;

@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public class ComplexUserService implements IComplexUserService {
    
    public User getUserByName(@WebParam(name = "name") String name) {
        User user = new User();
        user.setId(111);
        user.setName(name);
        user.setAddress("china");
        user.setEmail(name + "@hoo.com");
        return user;
    }
    
    public void setUser(User user) {
        System.out.println("############Server setUser###########");
        System.out.println("setUser:" + user);
    }
}