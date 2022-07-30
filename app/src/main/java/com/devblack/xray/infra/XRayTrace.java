package com.devblack.xray.infra;


import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;
import com.amazonaws.xray.entities.TraceID;

public class XRayTrace {
	
	public static void trace(final String traceId, final Runnable runnable) {
		
		final Subsegment subsegment = AWSXRay.beginSubsegment(traceId);
		subsegment.setTraceId(TraceID.fromString(traceId));
		final Segment segment = AWSXRay.beginSegment(traceId);
		segment.setTraceId(TraceID.fromString(traceId));
		
		try {
			runnable.run();
		} catch (final Exception e) {
			subsegment.addException(e);
			segment.addException(e);
			throw e;
		} finally {
			AWSXRay.endSegment();
			AWSXRay.endSubsegment();
		}
	}
	
}