package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard>request) {
		assert request !=null;
		return true;
	}
	
	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard>request) {
		assert request != null;
		
		AdministratorDashboard result;
		
		// Manage TheoryTutorial
		Integer totalNumberOfTheoryTutorials;
		List<Object> averageCostOfTheoryTutorials;
		List<Object> deviationCostOfTheoryTutorials;
		List<Object> minimumCostOfTheoryTutorials;
		List<Object> maximumCostOfTheoryTutorials;
		
		// Manage LabTutorial
		Integer totalNumberOfLabTutorials;
		List<Object> averageCostOfLabTutorials;
		List<Object> deviationCostOfLabTutorials;
		List<Object> minimumCostOfLabTutorials;
		List<Object> maximumCostOfLabTutorials;
		
		// Manage TOTALS HelpRequest
		Integer totalNumberOfProposedHelpRequests;
		Integer totalNumberOfAcceptedHelpRequests;
		Integer totalNumberOfDeniedHelpRequests;

		// Manage PROPOSED HelpRequest
		List<Object> averageBudgetOfProposedHelpRequests;
		List<Object> deviationBudgetOfProposedHelpRequests;
		List<Object> minimumBudgetOfProposedHelpRequests;
		List<Object> maximumBudgetOfProposedHelpRequests;

		// Manage ACCEPTED HelpRequest
		List<Object> averageBudgetOfAcceptedHelpRequests;
		List<Object> deviationBudgetOfAcceptedHelpRequests;
		List<Object> minimumBudgetOfAcceptedHelpRequests;
		List<Object> maximumBudgetOfAcceptedHelpRequests;

		// Manage DENIED HelpRequest
		List<Object> averageBudgetOfDeniedHelpRequests;
		List<Object> deviationBudgetOfDeniedHelpRequests;
		List<Object> minimumBudgetOfDeniedHelpRequests;
		List<Object> maximumBudgetOfDeniedHelpRequests;
		
		// Control Check
		Double 		 ratioOfTheoryTutorialWithLusit;
		List<Object> averageCostOfLusit;
		List<Object> deviationCostOfLusit;
		List<Object> minimumCostOfLusit;
		List<Object> maximumCostOfLusit;
		
		ratioOfTheoryTutorialWithLusit = this.repository.ratioOfTheoryTutorialWithLusit();
		averageCostOfLusit = this.repository.averageCostOfLusit();
		deviationCostOfLusit = this.repository.deviationCostOfLusit();
		minimumCostOfLusit = this.repository.minimumCostOfLusit();
		maximumCostOfLusit = this.repository.maximumCostOfLusit();
		
		
		totalNumberOfTheoryTutorials = this.repository.totalNumberOfTheoryTutorials();
		averageCostOfTheoryTutorials = this.repository.averageCostOfTheoryTutorials();
		deviationCostOfTheoryTutorials = this.repository.deviationCostOfTheoryTutorials();
		minimumCostOfTheoryTutorials = this.repository.minimumCostOfTheoryTutorials();
		maximumCostOfTheoryTutorials = this.repository.maximumCostOfTheoryTutorials();
		
		totalNumberOfLabTutorials = this.repository.totalNumberOfLabTutorials();
		averageCostOfLabTutorials = this.repository.averageCostOfLabTutorials();
		deviationCostOfLabTutorials = this.repository.deviationCostOfLabTutorials();
		minimumCostOfLabTutorials = this.repository.minimumCostOfLabTutorials();
		maximumCostOfLabTutorials = this.repository.maximumCostOfLabTutorials();
		
		totalNumberOfProposedHelpRequests = this.repository.totalNumberOfProposedHelpRequests();
		totalNumberOfAcceptedHelpRequests = this.repository.totalNumberOfAcceptedHelpRequests();
		totalNumberOfDeniedHelpRequests = this.repository.totalNumberOfDeniedHelpRequests();
		
		averageBudgetOfProposedHelpRequests = this.repository.averageBudgetOfProposedHelpRequests();
		deviationBudgetOfProposedHelpRequests = this.repository.deviationBudgetOfProposedHelpRequests();
		minimumBudgetOfProposedHelpRequests = this.repository.minimumBudgetOfProposedHelpRequests();
		maximumBudgetOfProposedHelpRequests = this.repository.maximumBudgetOfProposedHelpRequests();
		
		averageBudgetOfAcceptedHelpRequests = this.repository.averageBudgetOfAcceptedHelpRequests();
		deviationBudgetOfAcceptedHelpRequests = this.repository.deviationBudgetOfAcceptedHelpRequests();
		minimumBudgetOfAcceptedHelpRequests = this.repository.minimumBudgetOfAcceptedHelpRequests();
		maximumBudgetOfAcceptedHelpRequests = this.repository.maximumBudgetOfAcceptedHelpRequests();
		
		averageBudgetOfDeniedHelpRequests = this.repository.averageBudgetOfDeniedHelpRequests();
		deviationBudgetOfDeniedHelpRequests = this.repository.deviationBudgetOfDeniedHelpRequests();
		minimumBudgetOfDeniedHelpRequests = this.repository.minimumBudgetOfDeniedHelpRequests();
		maximumBudgetOfDeniedHelpRequests = this.repository.maximumBudgetOfDeniedHelpRequests();
		
		result = new AdministratorDashboard();
		result.setTotalNumberOfTheoryTutorials(totalNumberOfTheoryTutorials);
		result.setAverageCostOfTheoryTutorials(averageCostOfTheoryTutorials);
		result.setDeviationCostOfTheoryTutorials(deviationCostOfTheoryTutorials);
		result.setMinimumCostOfTheoryTutorials(minimumCostOfTheoryTutorials);
		result.setMaximumCostOfTheoryTutorials(maximumCostOfTheoryTutorials);
		
		result.setTotalNumberOfLabTutorials(totalNumberOfLabTutorials);
		result.setAverageCostOfLabTutorials(averageCostOfLabTutorials);
		result.setDeviationCostOfLabTutorials(deviationCostOfLabTutorials);
		result.setMinimumCostOfLabTutorials(minimumCostOfLabTutorials);
		result.setMaximumCostOfLabTutorials(maximumCostOfLabTutorials);
		
		result.setTotalNumberOfProposedHelpRequests(totalNumberOfProposedHelpRequests);
		result.setTotalNumberOfAcceptedHelpRequests(totalNumberOfAcceptedHelpRequests);
		result.setTotalNumberOfDeniedHelpRequests(totalNumberOfDeniedHelpRequests);
		
		result.setAverageBudgetOfProposedHelpRequests(averageBudgetOfProposedHelpRequests);
		result.setDeviationBudgetOfProposedHelpRequests(deviationBudgetOfProposedHelpRequests);
		result.setMinimumBudgetOfProposedHelpRequests(minimumBudgetOfProposedHelpRequests);
		result.setMaximumBudgetOfProposedHelpRequests(maximumBudgetOfProposedHelpRequests);
		
		result.setAverageBudgetOfAcceptedHelpRequests(averageBudgetOfAcceptedHelpRequests);
		result.setDeviationBudgetOfAcceptedHelpRequests(deviationBudgetOfAcceptedHelpRequests);
		result.setMinimumBudgetOfAcceptedHelpRequests(minimumBudgetOfAcceptedHelpRequests);
		result.setMaximumBudgetOfAcceptedHelpRequests(maximumBudgetOfAcceptedHelpRequests);
		
		result.setAverageBudgetOfDeniedHelpRequests(averageBudgetOfDeniedHelpRequests);
		result.setDeviationBudgetOfDeniedHelpRequests(deviationBudgetOfDeniedHelpRequests);
		result.setMinimumBudgetOfDeniedHelpRequests(minimumBudgetOfDeniedHelpRequests);
		result.setMaximumBudgetOfDeniedHelpRequests(maximumBudgetOfDeniedHelpRequests);
		
//		Control Check
		result.setRatioOfTheoryTutorialWithLusit(ratioOfTheoryTutorialWithLusit);
		result.setAverageCostOfLusit(averageCostOfLusit);
		result.setDeviationCostOfLusit(deviationCostOfLusit);
		result.setMinimumCostOfLusit(minimumCostOfLusit);
		result.setMaximumCostOfLusit(maximumCostOfLusit);

	
		return result;
	}
	
	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity,model,"totalNumberOfTheoryTutorials","averageCostOfTheoryTutorials","deviationCostOfTheoryTutorials","minimumCostOfTheoryTutorials","maximumCostOfTheoryTutorials",
									"totalNumberOfLabTutorials","averageCostOfLabTutorials","deviationCostOfLabTutorials","minimumCostOfLabTutorials","maximumCostOfLabTutorials",
									"totalNumberOfProposedHelpRequests","totalNumberOfAcceptedHelpRequests","totalNumberOfDeniedHelpRequests",
									"averageBudgetOfProposedHelpRequests","deviationBudgetOfProposedHelpRequests","minimumBudgetOfProposedHelpRequests","maximumBudgetOfProposedHelpRequests",
									"averageBudgetOfAcceptedHelpRequests","deviationBudgetOfAcceptedHelpRequests","minimumBudgetOfAcceptedHelpRequests","maximumBudgetOfAcceptedHelpRequests",
									"averageBudgetOfDeniedHelpRequests","deviationBudgetOfDeniedHelpRequests","minimumBudgetOfDeniedHelpRequests","maximumBudgetOfDeniedHelpRequests",
									"ratioOfTheoryTutorialWithLusit","averageCostOfLusit","deviationCostOfLusit","minimumCostOfLusit","maximumCostOfLusit"
									
			);
	}
	
}
