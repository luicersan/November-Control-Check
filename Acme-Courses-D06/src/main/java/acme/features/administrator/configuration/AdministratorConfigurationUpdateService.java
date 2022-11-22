package acme.features.administrator.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator,Configuration> {
	@Autowired
	protected AdministratorConfigurationRepository repository;
	
	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("currency")) {
			final List<String> acceptedCurrenciesList = Arrays.asList(entity.getAcceptedCurrencies().split(","));
			errors.state(request, acceptedCurrenciesList.contains(entity.getCurrency()), "currency", "administrator.configuration.configuration.form.error.currency");
		}
	}
	
	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "currency","acceptedCurrencies", "spamRecords", "spamThreshold", "spamBoosterFactor");
	}
	
	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "currency","acceptedCurrencies", "spamRecords", "spamThreshold", "spamBoosterFactor");
	}
	
	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		Configuration result;


		result = this.repository.findConfiguration();

		return result;
	}
	
	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Configuration> request, final Response<Configuration> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	

}
