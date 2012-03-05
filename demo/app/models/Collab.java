package models;

import javax.persistence.Entity;

import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Collab extends Model {

    @Required
    public String nom;
    
    @Required
    public String prenom;
    
    @Required
    @Email
    public String email;
    
    public String toString() {
    	return nom + " " + prenom;
    }
}