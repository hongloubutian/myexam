// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.jhc.myexam.server.repository;

import cn.jhc.myexam.server.domain.Role;
import cn.jhc.myexam.server.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect RoleRepository_Roo_Jpa_Repository {
    
    declare parents: RoleRepository extends JpaRepository<Role, Long>;
    
    declare parents: RoleRepository extends JpaSpecificationExecutor<Role>;
    
    declare @type: RoleRepository: @Repository;
    
}
