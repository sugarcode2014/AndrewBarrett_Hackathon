package com.titanicapps.bottlerecycler;

import java.io.Serializable;

public class RecycleHistoryData implements Serializable{

	private static final long serialVersionUID = 1L;
	private long dateReturnedInMs = 0;
	private long totalCount = 0;
	public long getDateReturnedInMs() {
		return dateReturnedInMs;
	}
	public void setDateReturnedInMs(long dateReturnedInMs) {
		this.dateReturnedInMs = dateReturnedInMs;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalValueCents() {
		return totalValueCents;
	}
	public void setTotalValueCents(long totalValueCents) {
		this.totalValueCents = totalValueCents;
	}
	private long totalValueCents = 0;
}
