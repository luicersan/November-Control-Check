package acme.features.learner.lusit;

/*
 * TeacherBlahblahShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Lusit;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Learner;

@Service
public class LearnerLusitShowService implements AbstractShowService<Learner, Lusit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LearnerLusitRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;

	@Override
	public boolean authorise(final Request<Lusit> request) {
		assert request != null;

		boolean result;
		int lusitId;
		Lusit lusit;

		lusitId = request.getModel().getInteger("id");
		lusit = this.repository.findOneLusitById(lusitId);
		result = lusit != null;

		return result;
	}

	@Override
	public Lusit findOne(final Request<Lusit> request) {
		assert request != null;

		int lusitId;
		Lusit result;

		lusitId = request.getModel().getInteger("id");
		result = this.repository.findOneLusitById(lusitId);

		return result;
	}
	
	@Override
	public void unbind(final Request<Lusit> request, final Lusit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "iname", "logo", "description", "initialDate", "budget", "beginDate", "finishDate", "hlink");
		
		final Money budget = this.convertToLocalCurrency(entity.getBudget());
		model.setAttribute("budget", budget);
		
		model.setAttribute("masterId", entity.getTheoryTutorial().getId());
	}
	
	// Other methods
	private Money convertToLocalCurrency(final Money prices) {
		final Money res = new Money();
		
		final String localCurrency = this.configurationRepository.findConfiguration().getCurrency();
		Double amount;
		Double convertedAmount;
		String currency;
		
		// EUR
		final Double EUR_USD_FACTOR = 1.0006;
		final Double EUR_GBP_FACTOR = 0.881655;
					
		// USD
		final Double USD_EUR_FACTOR = 0.998169;
		final Double USD_GBP_FACTOR = 0.88121;
		
		// GBP
		final Double GBP_EUR_FACTOR = 1.14938;
		final Double GBP_USD_FACTOR = 1.137041;
		
		amount = prices.getAmount();
		currency = prices.getCurrency();
		
		final Double operationGBPEUR = currency.equals("GBP")
				? amount * GBP_EUR_FACTOR
				: amount;
		
		final Double operationGBPUSD = currency.equals("GBP")
				? amount * GBP_USD_FACTOR
				: amount;
		
		final Double operationUSDGBP = currency.equals("USD")
				? amount * USD_GBP_FACTOR
				: amount;
		
		// If localCurrency = EUR
		if(localCurrency.equals("EUR")) {
			convertedAmount = currency.equals("USD")
				? amount * USD_EUR_FACTOR
				: operationGBPEUR;
		// If localCurrency = USD
		}else if(localCurrency.equals("USD")) {
			convertedAmount = currency.equals("EUR")
				? amount * EUR_USD_FACTOR
				: operationGBPUSD;
		// If localCurrency = GBP
		}else{
			convertedAmount = currency.equals("EUR")
				? amount * EUR_GBP_FACTOR
				: operationUSDGBP;
		}
		
		res.setAmount(convertedAmount);
		res.setCurrency(localCurrency);
		
		return res;
	}
	
}
