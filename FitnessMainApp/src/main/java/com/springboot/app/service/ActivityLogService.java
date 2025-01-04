package com.springboot.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.ActivityLogEntryDto;
import com.springboot.app.dto.ExerciseDto;
import com.springboot.app.model.ActivityLog;
import com.springboot.app.model.RegularUser;
import com.springboot.app.repository.ActivityLogRepository;
import com.springboot.app.repository.RegularUserRepository;

@Service
@Transactional
public class ActivityLogService {

	private final ActivityLogRepository activityLogRepository;
	private final RegularUserRepository regularUserRepository;
	private final ActivityLogEntryService activityLogEntryService;

	public ActivityLogService(ActivityLogRepository activityLogRepository, RegularUserRepository regularUserRepository,
			ActivityLogEntryService activityLogEntryService) {
		this.activityLogRepository = activityLogRepository;
		this.regularUserRepository = regularUserRepository;
		this.activityLogEntryService = activityLogEntryService;
	}

	public List<ActivityLog> findAllActivityLogs() {
		return activityLogRepository.findAll();
	}

	public ActivityLog create() {
		return activityLogRepository.save(new ActivityLog());
	}

	public List<ActivityLogEntryDto> findAllActivityLogsForUser(Integer userId) {
		RegularUser regUser = regularUserRepository.findById(userId).orElse(null);
		ActivityLog activityLog = null;

		if (regUser != null) {
			activityLog = regUser.getActivityLog();
			if (activityLog != null) {
				return activityLogEntryService.findAllEntriesOfActivityLog(activityLog);
			}
		}

		return null;
	}

	public ActivityLogEntryDto addActivityLogEntry(Integer userId, ActivityLogEntryDto activityLogEntryDto) {
		RegularUser regUser = regularUserRepository.findById(userId).orElse(null);
		ActivityLog activityLog = null;

		if (regUser != null) {
			activityLog = regUser.getActivityLog();
			if (activityLog != null) {
				return activityLogEntryService.addActivityLogEntry(activityLog, activityLogEntryDto);
			}
		}

		return null;
	}

	public List<ExerciseDto> findAllExercisesForUser(Integer userId) {
		RegularUser regUser = regularUserRepository.findById(userId).orElse(null);
		ActivityLog activityLog = null;

		if (regUser != null) {
			activityLog = regUser.getActivityLog();
			if (activityLog != null) {
				return activityLogEntryService.findAllExercisesActivityLog(activityLog);
			}
		}

		return null;
	}

	public  Map<Date, Double>  findAllDataForUser(Integer userId, Integer exerciseId) {
		ActivityLog activityLog = getActivityLogForUser(userId);
		if (activityLog != null) {
			return activityLogEntryService.findAllDataForExerciseForActivityLog(activityLog.getId(), exerciseId);
		}
		return null;
	}

	public  Map<Date, Double>  findYearlyDataForUser(Integer userId, Integer exerciseId, Integer year) {
		ActivityLog activityLog = getActivityLogForUser(userId);
		if (activityLog != null) {
			return activityLogEntryService.findYearlyDataForExerciseForActivityLog(activityLog.getId(), exerciseId, year);
		}
		return null;
	}

	public Map<Date, Double> findMonthlyDataForUser(Integer userId, Integer exerciseId, Integer year,
			Integer month) {
		ActivityLog activityLog = getActivityLogForUser(userId);
		if (activityLog != null) {
			return activityLogEntryService.findMonthlyDataForExerciseForActivityLog(activityLog.getId(), exerciseId, year, month);
		}
		return null;
	}

	private ActivityLog getActivityLogForUser(Integer userId) {
		RegularUser regUser = regularUserRepository.findById(userId).orElse(null);

		if (regUser != null) {
			return regUser.getActivityLog();

		}
		
		return null;
	}

	public byte[] getDiaryAsPDF(Integer userId) {
		RegularUser regUser = regularUserRepository.findById(userId).orElse(null);
		ActivityLog activityLog = null;

		if (regUser != null) {
			activityLog = regUser.getActivityLog();
			if (activityLog != null) {
				return activityLogEntryService.getDiaryAsPDF(activityLog);
			}
		}
		return null;
	}

}
