// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.jhc.myexam.server.domain;

import cn.jhc.myexam.server.domain.Capability;
import cn.jhc.myexam.server.domain.Role;
import cn.jhc.myexam.server.domain.User;
import java.util.List;
import java.util.Set;

privileged aspect Role_Roo_JavaBean {
    
    public String Role.getRolename() {
        return this.rolename;
    }
    
    public void Role.setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String Role.getDescription() {
        return this.description;
    }
    
    public void Role.setDescription(String description) {
        this.description = description;
    }
    
    public List<User> Role.getUsers() {
        return this.users;
    }
    
    public void Role.setUsers(List<User> users) {
        this.users = users;
    }
    
    public Set<Capability> Role.getCapabilities() {
        return this.capabilities;
    }
    
    public void Role.setCapabilities(Set<Capability> capabilities) {
        this.capabilities = capabilities;
    }
    
}
