package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Supplier;

@Component
@Transactional
public class SupplierToStringConverter implements Converter<Supplier, String>{
	
	@Override
	public String convert(Supplier supplier){
		String result;
		
		if(supplier == null)
			result = null;
		
		else
			result = String.valueOf(supplier.getId());
		
		return result;
	}

}
