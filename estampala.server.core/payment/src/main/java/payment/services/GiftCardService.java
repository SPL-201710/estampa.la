package payment.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import commons.exceptions.EstampalaException;
import payment.exceptions.GiftCardNotEnoughBalance;
import payment.exceptions.GiftCardNotFoundException;
import payment.exceptions.RequiredParameterException;
import payment.models.GiftCard;
import payment.models.GiftCardMethodPSE;
import payment.models.GiftCardMethodPSERepository;
import payment.models.GiftCardRepository;
import payment.pojos.GiftCardCreator;

@Service
public class GiftCardService {

	@Autowired
	private GiftCardRepository giftCardRepository;
	
	@Autowired
	private GiftCardMethodPSERepository pseRepository;

	public GiftCardService() {
		
	}

	public GiftCard find(UUID id) {
		return giftCardRepository.findOne(id);
	}

	public Page<GiftCard> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return giftCardRepository.findAll(pageRequest);
	}

	public GiftCard save(GiftCardCreator creator) throws EstampalaException {
		if (creator != null) {
			
			if(creator.getBuyer() == null) {
				throw new RequiredParameterException("buyer");
			}
			
			if(creator.getReceiver() == null) {
				throw new RequiredParameterException("receiver");
			}
			
			if(creator.getInitialBalance() == null) {
				throw new RequiredParameterException("initialBalance");
			}
			
			GiftCard giftCard = new GiftCard(UUID.randomUUID(), creator.getBuyer(), creator.getReceiver(), creator.getInitialBalance());
			giftCard = giftCardRepository.save(giftCard);

			GiftCardMethodPSE pse = creator.getPse_method();
			pse.setGiftCard(giftCard);

			pseRepository.save(pse);
			
			return giftCardRepository.save(giftCard);
		}
		return null;
	}

	public GiftCard update(GiftCardCreator creator) throws EstampalaException {
		if (creator != null) {
			
			GiftCard giftCard = find(creator.getGiftCard());
			
			if(creator.getInitialBalance() != null) {
				giftCard.setInitialBalance(creator.getInitialBalance());
			}
			
			if(creator.getBuyer() != null) {
				giftCard.setBuyer(creator.getBuyer());
			}
			
			if(creator.getReceiver() != null) {
				giftCard.setReceiver(creator.getReceiver());
			}
			
			if(creator.getActive() != null) {
				giftCard.setActive(creator.getActive());
			}
			
			return giftCardRepository.save(giftCard);
		}
		return null;
	}

	public void delete(UUID id) {
		if(id != null){
			giftCardRepository.delete(id);
		}
	}
	
	public double payGiftCard(UUID id, double price) throws GiftCardNotFoundException, GiftCardNotEnoughBalance {
		
		double newBalance = 0;
		
		if(!exists(id)) {
			throw new GiftCardNotFoundException();
		} 
		
		GiftCard giftCard = giftCardRepository.findOne(id);
		
		if(giftCard.getBalance() < price) {
			throw new GiftCardNotEnoughBalance();
		}
		
		newBalance = giftCard.getBalance() - price; 
		giftCard.setBalance(newBalance);
		
		giftCardRepository.save(giftCard);
		
		return newBalance;
	}

	public boolean exists(UUID id) {
		if(id != null){
			return giftCardRepository.exists(id);
		}
		return false;
	}
}
