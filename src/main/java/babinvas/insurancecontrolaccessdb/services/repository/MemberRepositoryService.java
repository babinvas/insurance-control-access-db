package babinvas.insurancecontrolaccessdb.services.repository;

import babinvas.insurancecontrolaccessdb.entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class MemberRepositoryService implements RepositoryService<Member> {
	private final EntityManagerFactory entityManagerFactory;

	public MemberRepositoryService(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public List<Member> getByInsurance(Date expirationDate) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = query.from(Member.class);

		query.select(root);

		System.out.println(query.toString());


		query.where(
				criteriaBuilder.equal(
						root.
								get("memberInsurance").
								get("expirationDate"), expirationDate));


		return entityManager.createQuery(query).getResultList();
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
