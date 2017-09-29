package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Supplier;

import repositories.SupplierRepository;

@Component
@Transactional
public class StringToSupplierConverter implements Converter<String, Supplier>{
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Override
	public Supplier convert(String text){
		Supplier result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = supplierRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
