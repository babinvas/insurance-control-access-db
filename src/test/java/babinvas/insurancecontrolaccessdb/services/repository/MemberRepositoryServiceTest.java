package babinvas.insurancecontrolaccessdb.services.repository;

import babinvas.insurancecontrolaccessdb.entities.Member;
import org.junit.jupiter.api.*;

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
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Test
	void getByInsurance_ShouldReturnTrueForIdenticalLists() {
		// Gets the expiration date of the member (President)
		Date expirationDate = getDate("00005");

		List<Member> expectedMembers = getMembersList(expirationDate);

		MemberRepositoryService memberRepositoryService = new MemberRepositoryService(entityManagerFactory);
		List<Member> actualMembers = memberRepositoryService.getByInsurance(expirationDate);

		Assertions.assertArrayEquals(expectedMembers.toArray(), actualMembers.toArray());
	}

	@Test
	void getByInsurance_ShouldReturnFalseForIdenticalLists() {
		// Gets the expiration date of the member (President)
		Date expirationDate = getDate("00005");

		List<Member> expectedMembers = getMembersList(expirationDate);

		expirationDate = new Date(expirationDate.getTime() + (1000 * 60 * 60 * 24));

		MemberRepositoryService memberRepositoryService = new MemberRepositoryService(entityManagerFactory);
		List<Member> actualMembers = memberRepositoryService.getByInsurance(expirationDate);

		Assertions.assertNotEquals(expectedMembers, actualMembers);
	}

	@BeforeAll
	static void setup() {
		entityManagerFactory = Persistence.createEntityManagerFactory("nkso-members-and-employers");
	}

	@AfterAll
	static void done() {
		entityManagerFactory.close();
	}

	@BeforeEach
	void init() {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterEach
	void close() {
		entityManager.close();
	}

	private Date getDate(String memberId) {
		Member member = entityManager.find(Member.class, memberId);
		return member.getMemberInsurance().getExpirationDate();
	}

	private List<Member> getMembersList(Date date) {
		EntityTransaction entityTransaction = entityManager.getTransaction();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = query.from(Member.class);

		query.select(root);

		query.where(
				criteriaBuilder.equal(
						root.
								get("memberInsurance").
								get("expirationDate"), date));

		return entityManager.createQuery(query).getResultList();
	}
}
