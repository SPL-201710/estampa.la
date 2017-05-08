package co.edu.uniandes.estampala.services;

import co.edu.uniandes.estampala.annotations.Authentidated;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;

public class SecurityProcessor extends AbstractProcessor<CtAnnotation<Authentidated>> {
	
	@Override
	public void init() {
		super.init();
	}
		
	public void process(CtAnnotation<Authentidated> annotation) {
		if(annotation.getParent() instanceof CtMethod)
			System.out.println("Class Found "+annotation.getParent().getSignature());
		else if (annotation.getParent() instanceof CtConstructor)
			System.out.println("Constructor Found "+annotation.getParent().getSignature());
	}	

	@Override
	public void processingDone() {
		super.processingDone();
	}	
}