package com.ars;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student-report")
@Table(name = "students")
public class StudentReportResult extends RepresentationModel<StudentReportResult> {
	
	private String overallReport;

	public String getOverallReport() {
		return overallReport;
	}

	public void setOverallReport(String overallReport) {
		this.overallReport = overallReport;
	}
}