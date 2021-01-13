package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.AirlineOperationDao;
import com.lti.dao.GenericDao;
import com.lti.dto.SearchFlightsDT;
import com.lti.entity.Flight;
import com.lti.entity.User;

@Service
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	AirlineOperationDao airlineDao;

	@Autowired
	GenericDao dao;

	@Override
	public int registerUser(User user) {
		User updatedUser = (User) dao.save(user);
		return updatedUser.getUserId();
	}

	@Override
	public List<Flight> searchFlights(SearchFlightsDT searchFlightsDT) {
		return airlineDao.searchFlightOperation(searchFlightsDT);
	}

}
