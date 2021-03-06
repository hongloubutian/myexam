// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.jhc.myexam.server.service;

import cn.jhc.myexam.server.domain.SingleChoice;
import cn.jhc.myexam.server.repository.SingleChoiceRepository;
import cn.jhc.myexam.server.service.SingleChoiceServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SingleChoiceServiceImpl_Roo_Service {
    
    declare @type: SingleChoiceServiceImpl: @Service;
    
    declare @type: SingleChoiceServiceImpl: @Transactional;
    
    @Autowired
    SingleChoiceRepository SingleChoiceServiceImpl.singleChoiceRepository;
    
    public long SingleChoiceServiceImpl.countAllSingleChoices() {
        return singleChoiceRepository.count();
    }
    
    public void SingleChoiceServiceImpl.deleteSingleChoice(SingleChoice singleChoice) {
        singleChoiceRepository.delete(singleChoice);
    }
    
    public SingleChoice SingleChoiceServiceImpl.findSingleChoice(Long id) {
        return singleChoiceRepository.findOne(id);
    }
    
    public List<SingleChoice> SingleChoiceServiceImpl.findAllSingleChoices() {
        return singleChoiceRepository.findAll();
    }
    
    public List<SingleChoice> SingleChoiceServiceImpl.findSingleChoiceEntries(int firstResult, int maxResults) {
        return singleChoiceRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void SingleChoiceServiceImpl.saveSingleChoice(SingleChoice singleChoice) {
        singleChoiceRepository.save(singleChoice);
    }
    
    public SingleChoice SingleChoiceServiceImpl.updateSingleChoice(SingleChoice singleChoice) {
        return singleChoiceRepository.save(singleChoice);
    }
    
}
