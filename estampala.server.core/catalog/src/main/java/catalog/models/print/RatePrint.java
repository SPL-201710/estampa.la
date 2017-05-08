package catalog.models.print;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author akane
 *
 */

@Entity
public class RatePrint {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
	
	@Column(nullable = false)
	private UUID idUser;
	
	@Column(nullable = false)
	private UUID idPrint;
	
	@Column(nullable = false)
	private float rate;

	public RatePrint() {

	}
	
	public RatePrint(UUID id, UUID idUser, UUID idPrint, float rate) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idPrint = idPrint;
		this.rate = rate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	public UUID getIdPrint() {
		return idPrint;
	}

	public void setIdPrint(UUID idPrint) {
		this.idPrint = idPrint;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
}
