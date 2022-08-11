package com.sip.ams;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
/*
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class TestProviderRepository {

	@Autowired
	private ProviderRepository providerRepositoryTest;

	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateProvider() {

		Provider provider = providerRepositoryTest.save(new Provider("HP", "USA", "hp@gmail.com"));
		assertThat(provider.getId()).isGreaterThan(1);
	}

	@Test
	@Rollback(false)
	@Order(2)
	public void testFindProviderByAddress() {

		Provider provider = providerRepositoryTest.findProviderByAdress("USA");
		assertThat(provider.getAdress()).isEqualTo("USA");
	}

	@Test
	@Rollback(false)
	@Order(3)
	public void testDeleteProvider() {

		Provider provider = providerRepositoryTest.findProviderByAdress("USA");

		assertThat(provider.getAdress()).isEqualTo("USA");
		providerRepositoryTest.delete(provider);
	}
}
*/