/*
 * AnyDutyShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.labTutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.LabTutorial;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyLabTutorialShowService implements AbstractShowService<Any, LabTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyLabTutorialRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;

	@Override
	public boolean authorise(final Request<LabTutorial> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public LabTutorial findOne(final Request<LabTutorial> request) {
		assert request!=null;
		
		LabTutorial result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneLabTutorialById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<LabTutorial> request, final LabTutorial entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "ticker", "title", "abstractText", "cost", "hyperlink");
		final Money totalPrice = this.convertToLocalCurrency(entity.getCost());
		model.setAttribute("cost", totalPrice);
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
				
				// If localCurrency = EUR
				if(localCurrency.equals("EUR")) {
					convertedAmount = currency.equals("USD")
						? amount * USD_EUR_FACTOR
						: currency.equals("GBP")
						? amount * GBP_EUR_FACTOR
						: amount;
				// If localCurrency = USD
				}else if(localCurrency.equals("USD")) {
					convertedAmount = currency.equals("EUR")
							? amount * EUR_USD_FACTOR
							: currency.equals("GBP")
							? amount * GBP_USD_FACTOR
							: amount;
				// If localCurrency = GBP
				}else{
					convertedAmount = currency.equals("EUR")
							? amount * EUR_GBP_FACTOR
							: currency.equals("USD")
							? amount * USD_GBP_FACTOR
							: amount;
				}
			
			res.setAmount(convertedAmount);
			res.setCurrency(localCurrency);
			
			return res;
		}


}
