package com.yedam.java.dept;
// VO클래스
public class Department {
	//필드
	private int departmentId; //부서번호
	private String departmentName; //부서이름
	private int managerId;	//부서장의 사원번호
	private int locationId; //위치번호
	//생성자 => 기본생성자
	
	
	//메소드 - getter()/setter(), toString()
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + ", locationId=" + locationId + "]";
	}	
}
