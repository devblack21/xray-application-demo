package com.devblack.xray.infra;


import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;

public class XRayTrace {
	
	public static void trace(final String traceId, final Runnable runnable) {
		
		final Subsegment subsegment = AWSXRay.beginSubsegment(traceId);

		try {
			runnable.run();
		} catch (final Exception e) {
			subsegment.addException(e);
			throw e;
		} finally {
			AWSXRay.endSubsegment();
		}
	}
	
}