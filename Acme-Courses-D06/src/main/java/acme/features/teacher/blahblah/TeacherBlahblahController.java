///*
// * TeacherBlahblahController.java
// *
// * Copyright (C) 2012-2022 Rafael Corchuelo.
// *
// * In keeping with the traditional purpose of furthering education and research, it is
// * the policy of the copyright owner to permit non-commercial use and redistribution of
// * this software. It has been tested carefully, but it is not guaranteed for any particular
// * purposes. The copyright owner does not offer any warranties or representations, nor do
// * they accept any liabilities with respect to them.
// */
//
//package acme.features.teacher.blahblah;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import acme.entities.Lusit;
//import acme.framework.controllers.AbstractController;
//import acme.roles.Teacher;
//
//@Controller
//public class TeacherBlahblahController extends AbstractController<Teacher, Lusit> {
//
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected TeacherBlahblahListService	listService;
//
//	@Autowired
//	protected TeacherBlahblahShowService	showService;
//	
//	// Constructors -----------------------------------------------------------
//
//
//	@PostConstruct
//	protected void initialise() {
//		super.addCommand("list", this.listService);
//		super.addCommand("show", this.showService);
//		
//	}
//
//}
