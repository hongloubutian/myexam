// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.jhc.myexam.server.domain;

import cn.jhc.myexam.server.domain.Capability;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

privileged aspect Capability_Roo_Jpa_Entity {
    
    declare @type: Capability: @Entity;
    
    @Id
    @SequenceGenerator(name = "capabilityGen", sequenceName = "capability_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "capabilityGen")
    @Column(name = "id")
    private Long Capability.id;
    
    @Version
    @Column(name = "version")
    private Integer Capability.version;
    
    public Long Capability.getId() {
        return this.id;
    }
    
    public void Capability.setId(Long id) {
        this.id = id;
    }
    
    public Integer Capability.getVersion() {
        return this.version;
    }
    
    public void Capability.setVersion(Integer version) {
        this.version = version;
    }
    
}
