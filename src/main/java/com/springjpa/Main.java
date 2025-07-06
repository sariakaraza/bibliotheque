// package com.springjpa;

// import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

// // import com.springjpa.entity.Departement;
// import com.springjpa.service.CategorieService;

// public class Main {
//     public static void main(String[] args) {
//         ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//         CategorieService departementService = (CategorieService) context.getBean(CategorieService.class);
//         departementService.saveDept("Finance");

//         System.out.println("Departement saved successfully!");
//     }
// }