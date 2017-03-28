package catalog.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catalog.exceptions.ShirtColorNotFoundException;
import catalog.exceptions.ShirtMaterialNotFoundException;
import catalog.exceptions.ShirtSizeNotFoundException;
import catalog.exceptions.ShirtStyleNotFoundException;
import catalog.models.shirt.Shirt;
import catalog.models.shirt.ShirtColor;
import catalog.models.shirt.ShirtMaterial;
import catalog.models.shirt.ShirtRepository;
import catalog.models.shirt.ShirtSize;
import catalog.models.shirt.ShirtStyle;
import catalog.pojos.ShirtCreator;
import commons.exceptions.EstampalaException;

/**
 *
 * @author akane
 *
 */
@Service
public class ShirtService {

	@Autowired
	private ShirtRepository repository;
	@Autowired
	private ShirtColorService colorService;
	@Autowired
	private ShirtMaterialService materialService;
	@Autowired
	private ShirtSizeService sizeService;
	@Autowired
	private ShirtStyleService styleService;

	public ShirtService() {

	}

	public Shirt find(UUID id) {
		return repository.findOne(id);
	}

	public Page<Shirt> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		return repository.findAll(pageRequest);
	}

	public Shirt save(ShirtCreator item) throws EstampalaException {
		if (item != null){
			ShirtMaterial material = materialService.find(item.getShirtMaterial());
			if (material == null){
				throw new ShirtMaterialNotFoundException();
			}
			
			ShirtColor color = colorService.find(item.getShirtColor());
			if (color == null){
				throw new ShirtColorNotFoundException();
			}
			
			ShirtSize size = sizeService.find(item.getShirtSize());
			if (size == null){
				throw new ShirtSizeNotFoundException();
			}
			
			ShirtStyle style = styleService.find(item.getShirtStyle());
			if (style == null){
				throw new ShirtStyleNotFoundException();
			}

			Shirt shirt = new Shirt(UUID.randomUUID(), style, size, color, material);
			return repository.save(shirt);
		}
		return null;
	}

	public Shirt update(ShirtCreator item) {
		if (item != null){
			ShirtMaterial material = materialService.find(item.getShirtMaterial());
			ShirtColor color = colorService.find(item.getShirtColor());
			ShirtSize size = sizeService.find(item.getShirtSize());
			ShirtStyle style = styleService.find(item.getShirtStyle());

			Shirt shirt = find(item.getShirt());
			shirt.setColor(color);
			shirt.setMaterial(material);
			shirt.setSize(size);
			shirt.setStyle(style);

			return repository.save(shirt);
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
}
