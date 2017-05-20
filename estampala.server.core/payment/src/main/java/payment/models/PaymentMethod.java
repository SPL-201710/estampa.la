package payment.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PaymentMethod {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	protected UUID id;
	
	@ManyToOne
	private Payment payment;
	
	private double value;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
