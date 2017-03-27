package catalog.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.exceptions.PrintFontNotFoundException;
import catalog.exceptions.PrintNotFoundException;
import catalog.exceptions.ShirtNotFoundException;
import catalog.exceptions.ShirtPrintPositionNotFoundException;
import catalog.models.print.Print;
import catalog.models.print.PrintFont;
import catalog.models.print.PrintText;
import catalog.models.product.PrintInShirt;
import catalog.models.product.Product;
import catalog.models.product.ProductRepository;
import catalog.models.product.TextInShirt;
import catalog.models.shirt.Shirt;
import catalog.models.shirt.ShirtPrintPosition;
import catalog.pojos.ProductCreator;
import catalog.pojos.ProductDesign;
import catalog.pojos.ProductPrintText;
import catalog.pojos.ProductPrintsInShirt;
import catalog.pojos.ProductTextsInShirt;
import commons.exceptions.EstampalaException;

/**
 * 
 * @author akane
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ShirtService shirtService;
	
	@Autowired
	private PrintService printService;
	
	@Autowired
	private ShirtPrintPositionService shirtPrintPositionService;
	
	@Autowired
	private PrintFontsService printFontsService;	
	
	public ProductService() {
		
	}
	
	public Product find(UUID id) {
		return repository.findOne(id);
	}
	
	public Page<Product> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}
	
	public Product save(ProductCreator item) throws EstampalaException {
		if (item != null){
			
			Collection<PrintInShirt> printsInShirts = new ArrayList<>();
			Collection<TextInShirt> textsInShirts = new ArrayList<>();
			
			if (item.getDesigns() != null){				
				for(ProductDesign proDesign : item.getDesigns()){
					if (proDesign.getShirt() != null){
						Shirt shirt = shirtService.find(proDesign.getShirt());
						if (shirt == null){
							throw new ShirtNotFoundException();
						}
						
						printsInShirts.add(createPrintInShirt(proDesign.getPrintsInShirts()));						
						textsInShirts.add(createTextInShirt(proDesign.getTextsInShirts()));
					}
				}
			}			

			Product product = new Product(UUID.randomUUID(), item.getTotalPrice(), printsInShirts, textsInShirts);
			return repository.save(product);
		}
		return null;
	}
	
	public Product update(ProductCreator item) throws EstampalaException {
		if (item != null){
			
			Collection<PrintInShirt> printsInShirts = new ArrayList<>();
			Collection<TextInShirt> textsInShirts = new ArrayList<>();
			
			if (item.getDesigns() != null){				
				for(ProductDesign proDesign : item.getDesigns()){
					if (proDesign.getShirt() != null){
						Shirt shirt = shirtService.find(proDesign.getShirt());
						if (shirt == null){
							throw new ShirtNotFoundException();
						}
						
						printsInShirts.add(createPrintInShirt(proDesign.getPrintsInShirts()));						
						textsInShirts.add(createTextInShirt(proDesign.getTextsInShirts()));
					}
				}
			}			

			Product product = new Product(item.getProduct(), item.getTotalPrice(), printsInShirts, textsInShirts);
			return repository.save(product);
		}
		return null;
	}

	
	public void delete(UUID id){
		if(id != null){
			repository.delete(id);
		}
	}
	
	public boolean exists(UUID id){
		if(id != null){
			return repository.exists(id);
		}
		
		return false;
	}
	
	private PrintInShirt createPrintInShirt(List<ProductPrintsInShirt> printsInShirts)  throws EstampalaException{
		PrintInShirt printInShirt = null;
		
		if (printsInShirts != null){
			for(ProductPrintsInShirt proPrintInShirt : printsInShirts){
				Print print = printService.find(proPrintInShirt.getPrint());
				if (print == null){
					throw new PrintNotFoundException();
				}
				
				ShirtPrintPosition shirtPrintPosition = shirtPrintPositionService.find(proPrintInShirt.getShirtPrintPosition());
				if (shirtPrintPosition == null){
					throw new ShirtPrintPositionNotFoundException();
				}
				
				printInShirt = new PrintInShirt(UUID.randomUUID(), print, shirtPrintPosition);								
			}
		}
		
		return printInShirt;
	}
	
	private TextInShirt createTextInShirt(List<ProductTextsInShirt> textsInShirts)  throws EstampalaException{
		TextInShirt textInShirt = null;
		
		if (textsInShirts != null){
			for(ProductTextsInShirt proTextInShirt : textsInShirts){				
				ShirtPrintPosition shirtPrintPosition = shirtPrintPositionService.find(proTextInShirt.getShirtPrintPosition());
				if (shirtPrintPosition == null){
					throw new ShirtPrintPositionNotFoundException();
				}
				
				ProductPrintText p = proTextInShirt.getPrintText();
				if (p != null){
					PrintFont printFont = printFontsService.find(p.getFont());
					if (printFont == null){
						throw new PrintFontNotFoundException();
					}					
					
					PrintText printText = new PrintText(UUID.randomUUID(), printFont, p.getMessage(), p.getSize());
					
					textInShirt = new TextInShirt(UUID.randomUUID(), printText, shirtPrintPosition);
				}
			}
		}
		
		return textInShirt;
	}
}
