package br.com.unisagrado.Unisagrado.unieventos.eventos.dto;

import java.util.Date;
import java.util.List;

public class FilterEventoDTO {

	private FilterRelatorioType filterType;
	private Params params;

	public FilterEventoDTO() {
	}

	public FilterEventoDTO(FilterRelatorioType filterType, Params params) {
		super();
		this.filterType = filterType;
		this.params = params;
	}

	public FilterRelatorioType getFilterType() {
		return filterType;
	}

	public void setFilterType(FilterRelatorioType filterType) {
		this.filterType = filterType;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public class Params {

		private Date startDate;
		private Date endDate;
		private String categoryId;
		private String course;
		private List<String> eventIds;

		public Params() {
		}

		public Params(Date startDate, Date endDate, String categoryId, List<String> eventIds, String course) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.categoryId = categoryId;
			this.eventIds = eventIds;
			this.course = course;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public List<String> getEventIds() {
			return eventIds;
		}

		public void setEventIds(List<String> eventIds) {
			this.eventIds = eventIds;
		}

	}

}
