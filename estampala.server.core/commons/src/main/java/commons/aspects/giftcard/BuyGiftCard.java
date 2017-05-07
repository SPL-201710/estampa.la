package commons.aspects.giftcard;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;


@Aspect
public class BuyGiftCard {

	@Pointcut("execution(* getAll(..))")
	public void action() {
	}

	@Before("action()")
	public void beforeAction(JoinPoint joinPoint) {
		System.out.println("Advice antes de método bar");
	}

	@After("action()")
	public void afterAction(JoinPoint joinPoint) {
		System.out.println("Advice después de métdo bar");
	}
}
