package springBootDemo.model;

import java.io.Serializable;

public class Department implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1159236636251019279L;
	private Integer id;
    private String name;
    private String descr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", descr=" + descr + "]";
	}
}