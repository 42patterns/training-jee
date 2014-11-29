package com.nordea.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="todos")
@NamedQuery(name = Todo.GET_ALL, query = "from Todo")
@XmlRootElement
public class Todo {

	final public static String GET_ALL = "Todo.GetAll";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "todo_id")
    private long id;
	
	@Column(name = "todo_title")
    private String title;
	
	@Column(name = "todo_order")
    private long order;
	
	@Column(name = "todo_completed")
    private boolean completed;

    public Todo(){
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getOrder() {
        return order;
    }

    public boolean isCompleted() {
        return completed;
    }

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOrder(long order) {
		this.order = order;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}

