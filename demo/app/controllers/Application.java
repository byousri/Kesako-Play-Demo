package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        List kesakos = Kesako.findAll();
		render(kesakos);
    }

	public static void add(final Kesako kesako) {
		validation.required("kesako.nom", kesako.nom);
	
		if (validation.hasErrors()) {
			validation.keep();
		} else {
			kesako.save();           
		}

		index();
	}
	
	public static void delete(final Long id) {
		Kesako kesako = Kesako.findById(id);
		kesako.delete();
		index();
	}
	
}