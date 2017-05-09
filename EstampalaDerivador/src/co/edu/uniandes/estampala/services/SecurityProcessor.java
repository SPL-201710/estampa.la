package co.edu.uniandes.estampala.services;

import co.edu.uniandes.estampala.annotations.Authenticated;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;

public class SecurityProcessor extends AbstractProcessor<CtAnnotation<Authenticated>> {
	
	@Override
	public void init() {
		super.init();
	}
		
	public void process(CtAnnotation<Authenticated> annotation) {
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