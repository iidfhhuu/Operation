package com.engineer.test.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.engineer.test.Entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

	List<Currency> findByCode(String code);
	

	
    @Modifying
    @Transactional
    @Query(value = " UPDATE currency "
                    + " SET code= CASE WHEN :#{#currency.code} IS NULL THEN code ELSE :#{#currency.code} END , "
                    + "symbol= CASE WHEN :#{#currency.symbol} IS NULL THEN symbol ELSE :#{#currency.symbol} END, "
                    + "rate= CASE WHEN :#{#currency.rate} IS NULL THEN rate ELSE :#{#currency.rate} END, "
                    + "description= CASE WHEN :#{#currency.description} IS NULL THEN description ELSE :#{#currency.description} END, "
                    + "rate_float= CASE WHEN :#{#currency.rateFloat} IS NULL THEN rate_float ELSE :#{#currency.rateFloat} END, "
                    + "update_date= CASE WHEN :#{#currency.updateDate} IS NULL THEN update_date ELSE :#{#currency.updateDate} END"
                    + " Where id=:#{#currency.id}", nativeQuery = true)
    int update(@Param(value = "currency") Currency currency);

}
