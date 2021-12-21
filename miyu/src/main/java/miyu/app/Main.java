package miyu.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class Main {
	// 실행 함수
	public static void main(String... args) {
	// FactoryManager를 생성합니다. "JpaExample"은 persistence.xml에 쓰여 있는 이름이다.
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("miyu");
	try {
	// Manager를 생성한다.
	EntityManager em = emf.createEntityManager();
	try {
	// User 테이블에서 nowonbun의 아이디를 가져온다.
	User user = em.find(User.class, 0);
	// 콘솔 출력
	System.out.println(user.getUserid());
	} finally {
	// Manager를 닫는다.
	em.close();
	}
	} finally {
	// FactoryManager를 닫는다.
	emf.close();
	}
	}
}