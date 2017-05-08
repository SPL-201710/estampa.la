package payment.aspects.giftcard;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

import payment.exceptions.ServiceNotAvailableException;

@Aspect
public class BuyGiftCard {

	@Pointcut("execution(* payment.controllers.GiftCardController.*(..))")
	public void action() {
	}

	@Before("action()")
	public void beforeAction(JoinPoint joinPoint) throws ServiceNotAvailableException {
		throw new ServiceNotAvailableException();
	}

	@After("action()")
	public void afterAction(JoinPoint joinPoint) {
	}
}
