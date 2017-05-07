package payment.pojos;

import java.util.UUID;

import payment.models.GiftCardMethodPSE;

public class GiftCardCreator {

	private UUID giftCard;	
	private UUID buyer;	
	private UUID receiver;	
	private Double initialBalance;	
	private GiftCardMethodPSE pse_method;
	private Boolean active;
	
	public UUID getGiftCard() {
		return giftCard;
	}
	public void setGiftCard(UUID giftCard) {
		this.giftCard = giftCard;
	}
	public UUID getBuyer() {
		return buyer;
	}
	public void setBuyer(UUID buyer) {
		this.buyer = buyer;
	}
	public UUID getReceiver() {
		return receiver;
	}
	public void setReceiver(UUID receiver) {
		this.receiver = receiver;
	}
	public Double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}
	public GiftCardMethodPSE getPse_method() {
		return pse_method;
	}
	public void setPse_method(GiftCardMethodPSE pse_method) {
		this.pse_method = pse_method;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
