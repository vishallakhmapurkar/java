package biz.neustar.netnumber.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import biz.neustar.netnumber.dao.NetNumberLastTxnDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;


public class NetNumberLastTxnDaoImpl implements NetNumberLastTxnDao {
    
    private JdbcTemplate jdbcTemplate;
	
    private static final Logger LOGGER = Logger
			.getLogger(NetNumberLastTxnDaoImpl.class);
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_LAST_TRANSACTION_QUERY = " SELECT LAST_PROCESSES_TXN_ID FROM NN_LAST_TXN WHERE CUSTOMER_NAME = ? ";

    @Override
	public long getLastProcessedTxnIdForCust(String customerName) throws NetNumberOverrideDataProvisioningException {
    	
    	long lastTxnId = 0;
    	
    	try {
			lastTxnId =  jdbcTemplate.queryForLong(GET_LAST_TRANSACTION_QUERY, customerName);
			LOGGER.debug("Last Transaction id is :"+lastTxnId);
		} catch (DataAccessException e) {
		throw new NetNumberOverrideDataProvisioningException("Query "+GET_LAST_TRANSACTION_QUERY+ " failure",e);
		}
    	System.out.println();
    	return lastTxnId;
	}
    
}
