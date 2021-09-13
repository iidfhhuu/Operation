package com.engineer.test.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engineer.test.Entity.Currency;
import com.engineer.test.Repository.CurrencyRepository;
import com.engineer.test.Service.CurrencyService;

@Service("CurrencyService")
public class CurrencyServiceImpl implements CurrencyService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyRepository repository;

	@Override
	public Currency create(Currency value) {
		value.setId(null);
		return repository.save(value);
	}

	@Override
	public Currency update(Currency value) {
		repository.update(value);
		Optional<Currency> c = repository.findById(value.getId());
		return c.get();
	}

	@Override
	public void delete() {
		repository.deleteAll();
	}

	@Override
	public List<Currency> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Currency> findByCode(String code) {
		return repository.findByCode(code);
	}

}
