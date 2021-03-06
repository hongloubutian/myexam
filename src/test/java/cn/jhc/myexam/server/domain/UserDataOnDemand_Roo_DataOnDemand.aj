// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.jhc.myexam.server.domain;

import cn.jhc.myexam.server.domain.User;
import cn.jhc.myexam.server.domain.UserDataOnDemand;
import cn.jhc.myexam.server.repository.UserRepository;
import cn.jhc.myexam.server.service.UserService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect UserDataOnDemand_Roo_DataOnDemand {
    
    declare @type: UserDataOnDemand: @Component;
    
    private Random UserDataOnDemand.rnd = new SecureRandom();
    
    private List<User> UserDataOnDemand.data;
    
    @Autowired
    UserService UserDataOnDemand.userService;
    
    @Autowired
    UserRepository UserDataOnDemand.userRepository;
    
    public User UserDataOnDemand.getNewTransientUser(int index) {
        User obj = new User();
        setDisplayName(obj, index);
        setEmail(obj, index);
        setEnabled(obj, index);
        setPassword(obj, index);
        setUsername(obj, index);
        return obj;
    }
    
    public void UserDataOnDemand.setDisplayName(User obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 128) {
            displayName = displayName.substring(0, 128);
        }
        obj.setDisplayName(displayName);
    }
    
    public void UserDataOnDemand.setEmail(User obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 128) {
            email = email.substring(0, 128);
        }
        obj.setEmail(email);
    }
    
    public void UserDataOnDemand.setEnabled(User obj, int index) {
        Boolean enabled = true;
        obj.setEnabled(enabled);
    }
    
    public void UserDataOnDemand.setPassword(User obj, int index) {
        String password = "password_" + index;
        if (password.length() > 1024) {
            password = password.substring(0, 1024);
        }
        obj.setPassword(password);
    }
    
    public void UserDataOnDemand.setUsername(User obj, int index) {
        String username = "username_" + index;
        if (username.length() > 64) {
            username = new Random().nextInt(10) + username.substring(1, 64);
        }
        obj.setUsername(username);
    }
    
    public User UserDataOnDemand.getSpecificUser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        User obj = data.get(index);
        Long id = obj.getId();
        return userService.findUser(id);
    }
    
    public User UserDataOnDemand.getRandomUser() {
        init();
        User obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return userService.findUser(id);
    }
    
    public boolean UserDataOnDemand.modifyUser(User obj) {
        return false;
    }
    
    public void UserDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = userService.findUserEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'User' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User obj = getNewTransientUser(i);
            try {
                userService.saveUser(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            userRepository.flush();
            data.add(obj);
        }
    }
    
}
