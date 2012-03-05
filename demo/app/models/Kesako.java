package models;

import java.util.*;

import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Kesako extends Model {

    @Required
    @MinSize(3)
	public String nom;
    
    public Date date;
    
    @OneToMany(fetch=FetchType.EAGER, targetEntity=Collab.class)
    public Set<Collab> inscrits = new HashSet<Collab>();
	
	@Override
	public String toString() {
		return "Kesako '" + nom + "' du " + date;
	}
}