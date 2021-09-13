package com.engineer.test.Service;

import java.util.List;

import com.engineer.test.Entity.Currency;

public interface CurrencyService {

	List<Currency> findByCode(String code);

	List<Currency> findAll();

	Currency update(Currency value);

	Currency create(Currency value);

	void delete();

}
