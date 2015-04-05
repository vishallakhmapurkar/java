package biz.neustar.netnumber.test;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import biz.neustar.netnumber.common.Constants;
import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.model.NNAuditProcessModel;
import biz.neustar.netnumber.service.AuditManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:netnumber-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
public class AuditManagementTest {
	
      @Autowired
      private AuditManagementService<NNAuditProcessModel> auditManagementService;
      @Autowired
      private AppConfig appConfig;
      
	  private final static int  FILE_COUNTER =2;
	  private final static int  LOG_ID =2;
	  
	  
      @Ignore
      public void insertAuditTest(){
    	  long newID = auditManagementService.insertNNProcessAudit(getNnAuditProcessModelForInsert(FILE_COUNTER,LOG_ID));
    	  assertTrue(newID>0);
    	  
      }
      @Test
      public void updateAuditTest(){
    	  long newID = auditManagementService.insertNNProcessAudit(getNnAuditProcessModelForInsert(FILE_COUNTER,LOG_ID));
    	  
    	  int count =auditManagementService.updateNNProcessAudit(getNnAuditProcessModelForUpdate(newID, LOG_ID, FILE_COUNTER, Constants.ProcessExecutionStatus.SUCCESS.toString()));
    	  
    	  assertTrue(count ==0);
    	  
      }
      private NNAuditProcessModel getNnAuditProcessModelForInsert(
  			long generatedFileCounter, long logID) {
  		NNAuditProcessModel nnAuditProcessModel = new NNAuditProcessModel();
  		nnAuditProcessModel.setProcessType(Constants.ProcessType.BOOTSTRAP
  				.toString());
  		nnAuditProcessModel
  				.setProcessStatus(Constants.ProcessExecutionStatus.STARTED
  						.toString());
  		nnAuditProcessModel.setGeneratedFilesCounter(generatedFileCounter);
  		nnAuditProcessModel.setEndTxnId(logID);
  		nnAuditProcessModel.setCustomerName("TEST");
  		nnAuditProcessModel.setStartTxnId(0);
  		nnAuditProcessModel.setEndTxnId(0);
  		return nnAuditProcessModel;
  	}
      private NNAuditProcessModel getNnAuditProcessModelForUpdate(long id,
  			long logID, int generatedFileCounter, String processStatus) {
  		NNAuditProcessModel nnAuditProcessModel = new NNAuditProcessModel();
  		nnAuditProcessModel.setProcessStatus(processStatus);
  		nnAuditProcessModel.setId(id);
  		nnAuditProcessModel.setGeneratedFilesCounter(generatedFileCounter);
  		nnAuditProcessModel.setEndTxnId(logID);
  		nnAuditProcessModel.setCustomerName(appConfig.getCustomerName());
  		return nnAuditProcessModel;
  	}
}
