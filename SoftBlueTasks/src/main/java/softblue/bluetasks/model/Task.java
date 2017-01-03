package softblue.bluetasks.model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
public class Task implements Serializable {
	private static final long serialVersionIUD = 1L;
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Boolean completed;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
}
