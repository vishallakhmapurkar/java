package biz.neustar.netnumber.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

@Aspect
public class DBRetryAspect {

	private static final Logger LOGGER = Logger.getLogger(DBRetryAspect.class);

	private int retryCount;

	private int sleepTime;

	/**
	 * Method setRetryCount.
	 * 
	 * @param retryCount
	 *            int
	 */
	public void setRetryCount(final int retryCount) {
		this.retryCount = retryCount;
	}

	/**
	 * Method setSleepTime.
	 * 
	 * @param sleepTime
	 *            int
	 */
	public void setSleepTime(final int sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * Method retryDbService.
	 * 
	 * @param joinPoint
	 *            ProceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* biz.neustar.netnumber.dao.*.*(..))")
	public Object retryDbOperation(final ProceedingJoinPoint joinPoint)
			throws Throwable {

		String methodName = joinPoint.getSignature().getName();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Entering : " + methodName);
			LOGGER.debug("Passed arguments : "
					+ Arrays.toString(joinPoint.getArgs()));
		}

		Object result = null;

		boolean retry = true;

		int numRetries = 1;

		while (retry) {
			try {

				result = joinPoint.proceed();

				retry = false;

			} catch (Exception e) {

				if (numRetries <= retryCount) {
					LOGGER.warn("DataAccessException in database operation "
							+ methodName, e);

					LOGGER.warn("Sleeping for "
							+ sleepTime
							+ " seconds and retrying again. number of retries = "
							+ numRetries);

					// sleep for configurable sleep time and try again
					// Utils.sleep(sleepTime);

					numRetries++;
				} else {
					// if operation fails after configurable number of retries,
					// throw DB exception
					throw new NetNumberOverrideDataProvisioningException(
							"Unable to resolve DataAccessException in "
									+ methodName + " after " + retryCount
									+ " retries", e);
				}
			}
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Method returned : " + result);
		}
		return result;
	}
}
