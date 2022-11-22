package acme.features.any.course;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.LabTutorial;
import acme.entities.TheoryTutorial;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.features.any.labTutorial.AnyLabTutorialRepository;
import acme.features.any.theoryTutorial.AnyTheoryTutorialRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyCourseShowService implements AbstractShowService<Any, Course>{

	
	@Autowired
	protected AnyCourseRepository repository;
	
	@Autowired
	protected AnyLabTutorialRepository labTutorialRepository;
	
	@Autowired
	protected AnyTheoryTutorialRepository theoryTutorialRepository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;
		
		Course result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneCourseById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		boolean existsTheoryTutorial = false;
		boolean existsLabTutorial = false;
		
		final int courseId = request.getModel().getInteger("id");
		final List<Object[]> costLab = this.repository.getCourseLabTutorialPrice(courseId);
		final List<Object[]> costTheory = this.repository.getCourseTheoryTutorialPrice(courseId);
		final Money moneyLab = this.convertToLocalCurrency(costLab);
		final Money moneyTheory = this.convertToLocalCurrency(costTheory);

		final Money totalCost = new Money();
		totalCost.setCurrency(moneyTheory.getCurrency());
		totalCost.setAmount(moneyLab.getAmount()+moneyTheory.getAmount());
		model.setAttribute("cost", totalCost);
		
		
		
		
		
		final Collection<TheoryTutorial> theoryTutorials  = this.theoryTutorialRepository.findManyTheoryTutorialsByCourseId(courseId);
		final Collection<LabTutorial> labTutorials  = this.labTutorialRepository.findManyLabTutorialsByCourseId(courseId);
		if(theoryTutorials.size()!=0) existsTheoryTutorial=true;
		if(labTutorials.size()!=0) existsLabTutorial=true;
		model.setAttribute("existsTheoryTutorial", existsTheoryTutorial);
		model.setAttribute("existsLabTutorial", existsLabTutorial);
		
		request.unbind(entity, model, "ticker", "caption", "abstractText");
		
		
	}
	
	// Other methods
	private Money convertToLocalCurrency(final List<Object[]> prices) {
		final Money res = new Money();

		final String localCurrency = this.configurationRepository.findConfiguration().getCurrency();
		Double amount;
		String currency;
		Double sumAmount = 0.0;

		// EUR
		final Double EUR_USD_FACTOR = 1.0006;
		final Double EUR_GBP_FACTOR = 0.881655;

		// USD
		final Double USD_EUR_FACTOR = 0.998169;
		final Double USD_GBP_FACTOR = 0.88121;

		// GBP
		final Double GBP_EUR_FACTOR = 1.14938;
		final Double GBP_USD_FACTOR = 1.137041;

		for (final Object[] b:prices) {
			amount = (Double) b[0];
			currency = (String) b[1];
			
			// If localCurrency = EUR
			if(localCurrency.equals("EUR")) {
				sumAmount += currency.equals("USD")
					? amount * USD_EUR_FACTOR
					: currency.equals("GBP")
					? amount * GBP_EUR_FACTOR
					: amount;
			// If localCurrency = USD
			}else if(localCurrency.equals("USD")) {
				sumAmount += currency.equals("EUR")
						? amount * EUR_USD_FACTOR
						: currency.equals("GBP")
						? amount * GBP_USD_FACTOR
						: amount;
			// If localCurrency = GBP
			}else{
				sumAmount += currency.equals("EUR")
						? amount * EUR_GBP_FACTOR
						: currency.equals("USD")
						? amount * USD_GBP_FACTOR
						: amount;
			}
		}
		
		res.setAmount(sumAmount);
		res.setCurrency(localCurrency);
		
		return res;
	}

}
