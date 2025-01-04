package com.springboot.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.ByteArrayOutputStream;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.ActivityLogEntryDto;
import com.springboot.app.dto.ExerciseDto;
import com.springboot.app.model.ActivityLog;
import com.springboot.app.model.ActivityLogEntry;
import com.springboot.app.model.Exercise;
import com.springboot.app.repository.ActivityLogEntryRepository;
import com.springboot.app.utility.EntityToDto;

import static com.springboot.app.utility.EntityToDto.*;
import static com.springboot.app.utility.DtoToEntity.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Transactional
public class ActivityLogEntryService {

	private final ActivityLogEntryRepository activityLogEntryRepository;
	
	public ActivityLogEntryService(ActivityLogEntryRepository activityLogEntryRepository) {
		this.activityLogEntryRepository = activityLogEntryRepository;
	}
	
	public List<ActivityLogEntry> findAllActivityLogEntrys(){
		return activityLogEntryRepository.findAll();
	}

	public List<ActivityLogEntryDto> findAllEntriesOfActivityLog(ActivityLog activityLog) {
		List<ActivityLogEntry> activityLogEntries = activityLogEntryRepository.findByActivityLog(activityLog);
		return activityLogEntries.stream()
								.map(EntityToDto::ConvertToDto)
								.sorted(Comparator.comparing(ActivityLogEntryDto::getDate).reversed())
								.collect(Collectors.toList());
	}

	public ActivityLogEntryDto addActivityLogEntry(ActivityLog activityLog, ActivityLogEntryDto activityLogEntryDto) {
		ActivityLogEntry activityLogEntry = DtoToActivityLogEntry(activityLogEntryDto);
		activityLogEntry.setActivityLog(activityLog);
		
		
		activityLogEntryRepository.save(activityLogEntry);
		return ConvertToDto(activityLogEntry);
	}

	public List<ExerciseDto> findAllExercisesActivityLog(ActivityLog activityLog) {
		List<Exercise> exercises = activityLogEntryRepository.findDistinctExercisesByActivityLogId(activityLog.getId());
		return exercises.stream()
						.map(EntityToDto::ConvertToDto)
						.collect(Collectors.toList());
	}

	public Map<Date, Double> findAllDataForExerciseForActivityLog(Integer activityLogId, Integer exerciseId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> results = activityLogEntryRepository.findAverageWeightByActivityLogAndExerciseIdGroupedByDate(activityLogId,exerciseId);
		return results.stream()
		        .collect(Collectors.toMap(
		            result -> {
		                try {
		                    return dateFormat.parse((String) result[0]); // Convert String to Date
		                } catch (ParseException e) {
		                    throw new RuntimeException("Failed to parse date", e);
		                }
		            }, // Convert Date to LocalDate
		            result -> (Double) result[1] // The average weight is already a BigDecimal
		        ));
	}

	public Map<Date, Double> findYearlyDataForExerciseForActivityLog(Integer activityLogId,
			Integer exerciseId, Integer year) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> results = activityLogEntryRepository.findAverageWeightByActivityLogAndExerciseIdForYearGroupedByDate(activityLogId, exerciseId, year);
		return results.stream()
		        .collect(Collectors.toMap(
		            result -> {
		                try {
		                    return dateFormat.parse((String) result[0]); // Convert String to Date
		                } catch (ParseException e) {
		                    throw new RuntimeException("Failed to parse date", e);
		                }
		            }, // Convert Date to LocalDate
		            result -> (Double) result[1] // The average weight is already a BigDecimal
		        ));
	}
	public Map<Date, Double> findMonthlyDataForExerciseForActivityLog(Integer activityLogId,
			Integer exerciseId, Integer year, Integer month) {
		List<Object[]> results = activityLogEntryRepository.findAverageWeightByActivityLogAndExerciseIdForMonthInYearGroupedByDate(activityLogId, exerciseId, year, month);
		 
		return results.stream()
			        .collect(Collectors.toMap(
			            result -> (Date) result[0], // Convert Date to LocalDate
			            result -> (Double) result[1] // The average weight is already a BigDecimal
			        ));
	}

	public byte[] getDiaryAsPDF(ActivityLog activityLog) {
		try {
			List<ActivityLogEntryDto> entries = findAllEntriesOfActivityLog(activityLog);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("Diary PDF");

            for(var entry : entries) {
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            	String paragraph = String.format(
            	    "\nDate: %s\nExercise: %s\nDuration: %d minutes\nIntensity: %s\nWeight: %.2f kg\n",
            	    dateFormat.format(entry.getDate()),  // Format the Date object
            	    entry.getExercise(),
            	    entry.getDuration(),
            	    entry.getIntensity().getIntensityName(),
            	    entry.getWeight()
            	);
             	document.add(new Paragraph(paragraph));
            
            }
            
            document.close();

            return out.toByteArray();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
		return null;
	}
}
