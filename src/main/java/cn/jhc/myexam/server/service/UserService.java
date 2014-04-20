package cn.jhc.myexam.server.service;
import java.util.Set;

import org.springframework.roo.addon.layers.service.RooService;

import cn.jhc.myexam.server.domain.Capability;
import cn.jhc.myexam.server.domain.User;

@RooService(domainTypes = { cn.jhc.myexam.server.domain.User.class })
public interface UserService {
	public User findByUsername(String name);
	public Set<Capability> findCapabilities(User user);
}
