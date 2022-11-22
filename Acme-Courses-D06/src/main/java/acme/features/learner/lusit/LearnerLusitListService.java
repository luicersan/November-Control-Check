/*
 * 
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Lusit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Learner;

@Service
public class LearnerLusitListService implements AbstractListService<Learner, Lusit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LearnerLusitRepository repository;

	@Override
	public boolean authorise(final Request<Lusit> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Learner.class); 

		return result;
	}

	@Override
	public Collection<Lusit> findMany(final Request<Lusit> request) {
		assert request != null;
		
		Collection<Lusit> result;
		
		result = this.repository.findManyLusitsByLearner(request.getPrincipal().getActiveRoleId());
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Lusit> request, final Lusit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "iname", "logo", "initialDate");
	}

}
