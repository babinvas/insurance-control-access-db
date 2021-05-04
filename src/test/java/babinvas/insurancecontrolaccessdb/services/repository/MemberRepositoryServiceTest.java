package babinvas.insurancecontrolaccessdb.services.repository;

import babinvas.insurancecontrolaccessdb.entities.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

class MemberRepositoryServiceTest {
	EntityManagerFactory entityManagerFactory;

	@Test
	void getByInsurance_ShouldReturnTrueForIdenticalLists() {
		entityManagerFactory = Persistence.createEntityManagerFactory("nkso-members-and-employers");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Gets the expiration date of the member (President)
		Member member = entityManager.find(Member.class, "00005");
		Date expirationDate = member.getMemberInsurance().getExpirationDate();

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = query.from(Member.class);

		query.select(root);

		query.where(
				criteriaBuilder.equal(
						root.
								get("memberInsurance").
								get("expirationDate"), expirationDate));

		List<Member> expectedMembers = entityManager.createQuery(query).getResultList();

		MemberRepositoryService memberRepositoryService = new MemberRepositoryService(entityManagerFactory);
		List<Member> actualMembers = memberRepositoryService.getByInsurance(expirationDate);

		Assertions.assertEquals(expectedMembers.size(), actualMembers.size());

		for (int i = 0; i < expectedMembers.size(); i++) {
			Assertions.assertEquals(expectedMembers.get(i).getIdRegistryNumber(), actualMembers.get(i).getIdRegistryNumber());
		}
	}
}