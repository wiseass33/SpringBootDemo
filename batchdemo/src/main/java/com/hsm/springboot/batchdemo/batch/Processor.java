package com.hsm.springboot.batchdemo.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsm.springboot.batchdemo.entity.Users;
import com.hsm.springboot.batchdemo.repository.UsersRepository;

@Component
public class Processor implements ItemProcessor<Users, Users> {

	@Autowired
	private UsersRepository userRepo;

	@Override
	public Users process(Users user) throws Exception {
		Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		System.out.println("User id is ::"+ user.getUserId()+ " ::name : "+user.getName());
		System.out.println("Amt value ::"+ user.getAccount());
		
		//Optional class information available after job has been invoked.
		if(userFromDb.isPresent()) {
			user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
			System.out.println("User avaialble"+user);
		}
		return user;
	}

}