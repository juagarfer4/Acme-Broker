package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import domain.CreditCard;

@Component
@Transactional
public class StringToCreditCardConverter implements Converter<String, CreditCard>{
	
	@Override
	public CreditCard convert(String text){
		CreditCard result;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				result = new CreditCard();
				result.setCardNumber(text);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
