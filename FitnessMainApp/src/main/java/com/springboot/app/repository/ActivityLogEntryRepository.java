package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.ActivityLog;
import com.springboot.app.model.ActivityLogEntry;
import com.springboot.app.model.Exercise;

@Repository
public interface ActivityLogEntryRepository extends JpaRepository<ActivityLogEntry, Integer> {

	List<ActivityLogEntry> findByActivityLog(ActivityLog activityLog);

	@Query("SELECT DISTINCT e.exercis FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId")
	List<Exercise> findDistinctExercisesByActivityLogId(Integer activityLogId);
	
	@Query("SELECT e FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId")
	List<ActivityLogEntry> findByActivityLogAndExcerciseId(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId);
	
	@Query("SELECT e FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId AND FUNCTION('YEAR', e.date) = :year")
	List<ActivityLogEntry> findByActivityLogAndExerciseIdForYear(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId, @Param("year") Integer year);
	
	@Query("SELECT e FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId AND FUNCTION('YEAR', e.date) = :year AND FUNCTION('MONTH', e.date) = :month")
	List<ActivityLogEntry> findByActivityLogAndExerciseIdForMonthInYear(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId, @Param("year") Integer year, @Param("month") Integer month);

	@Query("SELECT DATE_FORMAT(e.date, '%Y-01-01') AS date, AVG(e.weight) FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId GROUP BY DATE_FORMAT(e.date, '%Y-01-01')")
	List<Object[]> findAverageWeightByActivityLogAndExerciseIdGroupedByDate(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId);

	@Query("SELECT DATE_FORMAT(e.date, '%Y-%m-01') AS date, AVG(e.weight) FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId AND FUNCTION('YEAR', e.date) = :year GROUP BY DATE_FORMAT(e.date, '%Y-%m-01')")
	List<Object[]> findAverageWeightByActivityLogAndExerciseIdForYearGroupedByDate(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId, @Param("year") Integer year);
	
	@Query("SELECT e.date, AVG(e.weight) FROM ActivityLogEntry e WHERE e.activityLog.id = :activityLogId AND e.exercis.id = :exerciseId AND FUNCTION('YEAR', e.date) = :year AND FUNCTION('MONTH', e.date) = :month GROUP BY e.date")
	List<Object[]> findAverageWeightByActivityLogAndExerciseIdForMonthInYearGroupedByDate(@Param("activityLogId") Integer activityLogId, @Param("exerciseId") Integer exerciseId, @Param("year") Integer year, @Param("month") Integer month);
}
