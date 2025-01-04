package com.springboot.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.PaymentDto;
import com.springboot.app.dto.ProgramInfoDto;
import com.springboot.app.model.Payment;
import com.springboot.app.model.Program;
import com.springboot.app.model.RegularUser;
import com.springboot.app.model.RegularUserProgram;
import com.springboot.app.model.RegularUserProgramPK;
import com.springboot.app.repository.RegularUserProgramRepository;
import com.springboot.app.utility.EntityToDto;

import static com.springboot.app.utility.EntityToDto.ConvertToDtoInfo;;

@Service
@Transactional
public class RegularUserProgramService {

	private final RegularUserProgramRepository regularUserProgramRepository;
	private final RegularUserService regularUserService;
	private final ProgramService programService;
	private final PaymentService paymentService;
	
	public RegularUserProgramService(RegularUserProgramRepository regularUserProgramRepository, RegularUserService regularUserService, 
			ProgramService programService, PaymentService paymentService) {
		this.regularUserProgramRepository = regularUserProgramRepository;
		this.regularUserService = regularUserService;
		this.programService = programService;
		this.paymentService = paymentService;
	}
	
	public List<RegularUserProgram> findAllRegularUserPrograms(){
		return regularUserProgramRepository.findAll();
	}

	public List<ProgramInfoDto> findSubscribedForUser(Integer userId) {
		List<RegularUserProgram> programs = regularUserProgramRepository.findByRegularUserId(userId);
		return programs.stream()
						.map(EntityToDto::ConvertToDtoInfo)
						.collect(Collectors.toList());
	}

	public ProgramInfoDto subscribeToProgram(PaymentDto paymentDto) {
		RegularUser regUser = regularUserService.findByIdRegularUser(paymentDto.getUserId());
		Program program = programService.findByIdProgram(paymentDto.getProgramId());
		Payment payment = paymentService.addPayment(paymentDto);
		
		if(regUser != null && program != null && paymentDto != null && program.getRegularUser().getId() != regUser.getId()) {
			RegularUserProgramPK pk = new RegularUserProgramPK();
			pk.setProgramId(program.getId());
			pk.setUserId(regUser.getId());
			
			RegularUserProgram regUserProgram = new RegularUserProgram();
			regUserProgram.setProgram(program);
			regUserProgram.setRegularUser(regUser);
			regUserProgram.setPayment(payment);
			regUserProgram.setId(pk);
			
			return ConvertToDtoInfo(regularUserProgramRepository.save(regUserProgram));
		}
		return null;
	}

	public boolean findIsUserSubscribedForProgram(Integer userId, Integer programId) {
		RegularUser regUser = regularUserService.findByIdRegularUser(userId);
		Program program = programService.findByIdProgram(programId);
		
		if(regUser != null && program != null) {
			RegularUserProgramPK pk = new RegularUserProgramPK();
			pk.setProgramId(program.getId());
			pk.setUserId(regUser.getId());
		    Optional<RegularUserProgram> userProgram = regularUserProgramRepository.findById(pk);
		    
		    return userProgram.isPresent();
		}
		
		return false;
	}
	
}
