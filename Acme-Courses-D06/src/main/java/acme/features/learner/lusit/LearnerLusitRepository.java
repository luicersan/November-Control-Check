/*
 * LearnerHelpRequestRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.learner.lusit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.HelpRequest;
import acme.entities.Lusit;
import acme.entities.TheoryTutorial;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;

@Repository
public interface LearnerLusitRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(@Param("id") int id);
	
	@Query("select i from Learner i where i.userAccount.id = :id")
	Learner findOneLearnerById(@Param("id") int id);

	@Query("select h from HelpRequest h where h.learner.id = :id and h.publish = true")
	Collection<HelpRequest> findManyHelpRequestsByLearner(@Param("id") int id);
	
	@Query("select h.learner.id from HelpRequest h where h.id = :id")
	Integer findLearnerByHelpRequestId(@Param("id") int id);
	
	// Control-Check
	
	@Query("select b from Lusit b where b.id = :id")
	Lusit findOneLusitById(@Param("id") int id);
	
	@Query("select b from Lusit b where b.theoryTutorial.id = :id")
	Lusit findOneLusitByTheoryTutorialId(@Param("id") int id);

	@Query("select b from Learner te, Course c, Register r, TheoryTutorial t, Lusit b where c.id = r.course.id and r.theoryTutorial.id = t.id and t.id = b.theoryTutorial.id and te.id = :id")
	Collection<Lusit> findManyLusitsByLearner(@Param("id") int id);

	@Query("select t from TheoryTutorial t where t.id = :id")
	TheoryTutorial findByTheoryTutorialId(@Param("id") int id);

	@Query("select b from Lusit b")
	Collection<Lusit> findAllLusits();

}
