package payment.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PaymentMethodGiftCard extends PaymentMethod{

	@OneToOne
	private GiftCard giftCard;
	
	public PaymentMethodGiftCard() {
		
	}

	public GiftCard getGiftCard() {
		return giftCard;
	}

	public void setGiftCard(GiftCard giftCard) {
		this.giftCard = giftCard;
	}

}
