package com.fresher.report.entities;

public class InterviewResult {

	private String email;
	private String phone;
	private String interviewResult;
	private String note;

	public InterviewResult(String email, String phone, String interviewResult, String note) {
		super();
		this.email = email;
		this.phone = phone;
		this.interviewResult = interviewResult;
		this.note = note;
	}

	public InterviewResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InterviewResult [email=" + email + ", phone=" + phone + ", interviewResult=" + interviewResult
				+ ", note=" + note + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
