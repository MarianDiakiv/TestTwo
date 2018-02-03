package com.logos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Course;
import entity.Student;
import entity.Teacher;
import entity.TeacherDetails;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
        
		
		//заповнення вчителів
//		for ( int i = 1; i <= 100; i++) {
//			
//			Teacher teacher = new Teacher("Teacher_Login#"+i, "Teacher_Password#"+i);
//			teacher.setTeacherDetails(new TeacherDetails("First_Name#"+i, 
//					"Last_Name#"+i, i+10, "I like PC gaming#"+i));
//			
//			em.persist(teacher);
//		}
//		//заповнення курсів із звязками до викладачів
//		for (int i = 1; i <= 20; i++ ) {
//			
//			Teacher teacher = em.createQuery("SELECT t FROM Teacher t WHERE t.id = :id", Teacher.class)
//					.setParameter("id", new Long(i)).getSingleResult();
//			
//			Course course = new Course("JPQL#" + i, "JPQL - for beginners", new BigDecimal(i + ".99"));
//			course.setTeacher(teacher);
//			em.persist(course);
//		}
//		
//		//заповнення студентів з звязком до курсів
//		for (int i = 1; i < 20; i++) {
//			
//			Course course = em.createQuery("SELECT c FROM Course c WHERE c.id = :id", Course.class)
//					.setParameter("id", new Long(i)).getSingleResult();
//			
//			Student student = new Student("Full_Name#" + i, i + 10);
//			course.getStudents().add(student);
//			em.persist(student);
//		}
		
		
		
		//запити з функціями 
//		BigDecimal min = em.createQuery("SELECT min(c.price) FROM Course c", BigDecimal.class)
//		.getSingleResult();
//System.out.println("MIN: " + min);
//
//BigDecimal max = em.createQuery("SELECT max(c.price) FROM Course c", BigDecimal.class)
//		.getSingleResult();
//System.out.println("MAX: " + max);
//
//Double avg = em.createQuery("SELECT avg(c.price) FROM Course c", Double.class)
//		.getSingleResult();
//System.out.println("AVG: " + avg);
//
//BigDecimal sum = em.createQuery("SELECT sum(c.price) FROM Course c", BigDecimal.class)
//		.getSingleResult();
//System.out.println("SUM: " + sum);
//
//Long count = em.createQuery("SELECT count(c) FROM Course c", Long.class).getSingleResult();
//System.out.println(count);

// JOIN - JOIN FETCH

//	Teacher teacher = em.createQuery("SELECT t FROM Teacher t WHERE t.id = :id", Teacher.class)
//			.setParameter("id", new Long(5))
//			.getSingleResult();
//	System.out.println(teacher);

//Teacher teachertwo = em.createQuery("SELECT DISTINCT t FROM Teacher t JOIN FETCH t.courses c WHERE c.id = :id", Teacher.class)
//			.setParameter("id", 5L).getSingleResult();
//System.out.println(teachertwo);

		//List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
		//teachers.forEach(t -> System.out.println(t));

		//Teacher teacher = em.createQuery("SELECT t FROM Teacher t WHERE t.id = :id", Teacher.class)
//				.setParameter("id", new Long(45)).getSingleResult();
		//System.out.println(teacher);

		//List<Teacher> teachers = em
//				.createQuery("SELECT t FROM Teacher t WHERE t.login LIKE '%9' AND t.id > :number", Teacher.class)
//				.setParameter("number", new Long(10))
//				.getResultList();
		//teachers.forEach(t -> System.out.println(t));

		//List<Teacher> teachers = em.createQuery(
//				"SELECT t FROM Teacher t WHERE t.id BETWEEN :start AND :finish", Teacher.class)
//				.setParameter("start", new Long(34))
//				.setParameter("finish", new Long(78))
//				.getResultList();
		//teachers.forEach(t -> System.out.println(t));

		//List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t WHERE t.id IN (:ids)", Teacher.class)
//				.setParameter("ids", Arrays.asList(new Long(34), new Long(45), new Long(89))).getResultList();
		//teachers.forEach(t -> System.out.println(t));
		
		
//		Course course = em.createQuery("Select c from Course c where c.id = :id", Course.class)
//				.setParameter("id", 5L)
//				.getSingleResult();
//		System.out.println(course);
//		
		
		
		
		///////////////////////////////////////////// 29-01-2018 критерії етапів
		
//		Teacher teacher = em.createQuery("Select t from Teacher t where t.id=:id", Teacher.class).getSingleResult();
		CriteriaBuilder cb = em.getCriteriaBuilder();  //критерій не потрібно закривати
		CriteriaQuery<Course> query =cb.createQuery(Course.class);
		
		Root<Course> root = query.from(Course.class); // відповідник в  sql from Course c   
		
		query.select(root);// відповідник в  sql select c from Course c
		
		Expression<BigDecimal> priceExpression = root.get("price");
		Predicate pricePredecat =cb.ge(priceExpression, new BigDecimal("15.99")); //ge це більше рівне 
		Predicate pricePredicatTwo = cb.le(priceExpression, new BigDecimal("19.99")); //це менше рівне
		Predicate allPredicat = cb.and(pricePredecat, pricePredicatTwo); // можна записати багато предикатів
		
		
		//query.where(allPredicat);  // можна закоментувати лише це і не буде працювати попередній запит  щоб все не коментувати
		
		
		Expression<Long> betweenId =root.get("id");
		Predicate predicatId = cb.between(betweenId, new Long(5),new Long(10));
		//query.where(predicatId);
		
		 Expression<String> titleExprision = root.get("title");
		 Predicate titlePridicate = cb.like(titleExprision, "%2");
		 
		 Expression<Long> idExpresion = root.get("id");
		 Predicate idPredicate = cb.ge(idExpresion, new Long(10));
		 Predicate allPredicatTwo = cb.and(idPredicate, titlePridicate); 
		 //query.where(allPredicatTwo);
		 
		 Join<Course, Teacher> teacherJoin=  root.join("teacher");
		 Expression<Long> teacherIdJoin =teacherJoin.get("id");
		 Predicate teacherIdPredicate =cb.equal(teacherJoin, new Long(7));
		 query.where(teacherIdPredicate);
		 root.fetch("teacher");
		
		List<Course> courses = em.createQuery(query).getResultList();
		courses.forEach(c->System.out.println(c + " " + c.getTeacher()));   //c.getTeacher()  вданому випадку виведе викладача 	
		
		
		
		
		
		
        
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
