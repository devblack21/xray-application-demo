package com.devblack.xray.infra;


import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;

public class XRayTrace {
	
	public static void trace(final String traceId, final Runnable runnable) {
		
		final Segment subsegment = AWSXRay.beginSegment(traceId);
		subsegment.setParentId("xray-application-demo");
		subsegment.setOrigin("xray-application-demo");
		try {
			runnable.run();
		} catch (final Exception e) {
			subsegment.addException(e);
			throw e;
		} finally {
			AWSXRay.endSegment();
		}
	}
	
}