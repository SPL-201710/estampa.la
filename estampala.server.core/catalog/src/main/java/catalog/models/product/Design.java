package catalog.models.product;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import catalog.models.shirt.Shirt;

/**
 * @author akane
 * 
 */

@Entity
public class Design {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;
		
	@Column(nullable = true)
	@ManyToOne
	private Shirt shirt;
			
	@Column(nullable = true)
	@OneToMany
	private Collection<PrintInShirt> printsInShirts;
	
	@Column(nullable = true)
	@OneToMany
	private Collection<TextInShirt> textsInShirts;

	Design(){

	}

	public Design(UUID id, Shirt shirt, Collection<PrintInShirt> printsInShirts, Collection<TextInShirt> textsInShirts) {
		super();
		this.id = id;
		this.shirt = shirt;
		this.printsInShirts = printsInShirts;
		this.textsInShirts = textsInShirts;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Shirt getShirt() {
		return shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
	}

	public Collection<PrintInShirt> getPrintsInShirts() {
		return printsInShirts;
	}

	public void setPrintsInShirts(Collection<PrintInShirt> printsInShirts) {
		this.printsInShirts = printsInShirts;
	}

	public Collection<TextInShirt> getTextsInShirts() {
		return textsInShirts;
	}

	public void setTextsInShirts(Collection<TextInShirt> textsInShirts) {
		this.textsInShirts = textsInShirts;
	}	
}