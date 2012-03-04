# Live coding demo : Kesako in Play 1.2.x

Largely inspired from [http://www.lunatech-research.com/archives/2010/06/14/how-demo-play-framework-live-coding-script](http://www.lunatech-research.com/archives/2010/06/14/how-demo-play-framework-live-coding-script).

## Summary
1. Download the Play framework.
2. Create, run the application and push it to Github.
3. Inspect the set-up for the default welcome page.
4. Replace the welcome page with a trivial dynamic template.
5. Generate a Java compilation error.
6. Retrieve HTTP request parameters.
7. Show message file content in the template
8. Add the project to Eclipse.
9. Add a JPA Entity.
10. Add an HTML form for creating entity instances.
11. Add a link for deleting each instance.
12. Use a Java extensions in the view template.
13. Add form validation.
14. Testing (TODO)
15. CRUD (TODO)

## Create and run the application

**Open** [http://www.playframework.org/](http://www.playframework.org/) Download the binary

> There is no need to *install* Play as such, although you might want to add the download's directory to your `PATH`, so you can execute the `$PLAY_HOME/play` commmand directly. On my machine, though, it's just as easy to just type `~/Downloads/play-1.2.4/play` for this kind of demo.

**Execute**

    play new kesakos
    ~ What is the application name? kesakos

> A kesako list application is an easy example for people to understand, but it is more fun to pick an example that is more familiar and relevant to your audience, such as 'customers' or 'cocktails'.

**Execute** `find kesakos -type f` (optional) - List the generated files

> It is crucial to note that Play has not generated a lot of code here; the files are minimal stubs that you will only add to, rather than a lot of generated code that you will have to delete.

**Execute** `play run kesakos` - Start the Play server runtime to run the application.

> Depending on what you are used to, start-up time may seem extremely short.

**Open** [http://localhost:9000/](http://localhost:9000/) - Show welcome page.

> The welcome page describes how the stub application works, which the next few steps show.

**Start** a text editor.

> You could use an IDE, such as Eclipse, already at this point. However, it is useful to start with a plain text editor to make it clear that you do not have to compile the Java code yourself.

**Edit** `conf/routes` - Show the route for the `GET /` HTTP request.

> Since Play has an HTTP focus, in incoming HTTP request is a good starting point for explaining how it works. The `routes` file, is therefore crucial, because this defines the (two-way) mapping between HTTP requests and Java methods.

**Edit** `app/controllers/Application.java` - Show the `index()` method.

**Edit** `app/views/main.html` - Show the default template: HTML 5, CSS and jQuery.

**Edit** `app/views/Application/index.html` - Show the `#{welcome /}` tag and replace it with `<h1>Kesakos</h1>`.

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the heading.

**Edit** `public/stylesheets/main.css` - Add some CSS to make things less ugly:

```css
html { }
body {
	color: black;
    font-family: Arial,Verdana,Helvetica,sans-serif;
	padding:2em;
	background: url(http://www.sqli.com/design/fre/images/body-home-03.jpg) repeat scroll 0 0 #000000;
}
body:before { content:'Play - Demo Kesako'; color:black; font-size:150%; text-transform:uppercase; letter-spacing:0.4em; }
ul { padding:0; list-style:none; }
li, form { width:30em; background:white; padding:1em; border:1px solid #ccc; border-radius:0.5em; margin:1em 0; position:relative; }
li a {
	text-decoration:none; 
	color:transparent; 
	position:absolute; 
	top:1em; 
	right:1em;
}
li a:after { 
	content: url(http://icons.iconarchive.com/icons/custom-icon-design/office/16/delete-icon.png); 
	color:#aaa; 
	font-size:120%; 
	font-weight:bold; 
}
form * { font-size:110%; }
input { width:16em; }
button {
	cursor:pointer; 
	color: white; 
	background-color: #DA0E1B; 
	background-image: linear-gradient(top, #5AA706, #3D6F04); 
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); 
	border: 1px solid #CCC; 
	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); 
	border-radius: 4px; 
}
p.error { margin:0; color:#c00; }
```

** Execute **

    git init
    git add .
    git commit -m 'first commit'
    git remote add origin git@github.com:dlecan/kesakos.git
    git push -u origin master

** Setup Cloudbees Jenkins Job **
	
## Show dynamic data in the template

**Edit** `app/views/Application/index.html` - Change the heading to `<h1>${trucs}</h1>`.

**Edit** `app/controllers/Application.java` - Change the `index()` method body to the following (omitting a semi-colon).

```java
final String trucs = "Machins"
render(trucs);
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the Java compilation error.

**Edit** `app/controllers/Application.java` - Add the missing semi-colon.

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the new heading, and Java changes without compilation or deployment.

**Edit** `app/controllers/Application.java` - Replace the `trucs` declaration line with a `String trucs` method parameter.

**Open** [http://localhost:9000/?trucs=Machins](http://localhost:9000/?trucs=Machins)

**Edit** `app/controllers/Application.java` - Undo the last change - remove the parameter and put the declaration back.


## Show message file content in the template

**Edit** `app/views/Application/index.html` - Change the heading to `<h1>&{'kesako.titre'}</h1>`.

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the message key being displayed, because the message is not defined.

**Edit** `conf/messages` - Add the line `kesako.titre = Kesako`

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the message being displayed.


## Eclipse

**Execute** `Control+C` - Show how little logging there is by default.

**Execute** `play eclipsify kesakos` - Generate Eclipse project and class path configuration.

**Eclipse** *File => Import... => Existing projects into workspace* - Show project structure.

**Eclipse** `eclipse/kesakos.launch` => Run => kesakos - Start the Play server runtime from within Eclipse.

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the application running.


## IntelliJ IDEA

**Execute** `Control+C` - Show how little logging there is by default.

**Execute** `play idealize kesakos` - Generate IDEA project and class path configuration.

**Execute** `open kesakos/kesakos.ipr` - Open the project in IDEA.

**Execute** `play run kesakos` - Start the Play server again.


## JPA entity

**Edit** `app/models` - create class:

```java
package models;

@javax.persistence.Entity
public class Kesako extends play.db.jpa.Model {

   public String nom;
}
```

> At this point you may need to explain that `Kesako` is a Java Bean at run-time, because Play dynamically adds getter and setter methods for the public fields, turning them into normal Java Bean properties.

**Edit** `app/controllers/Application.java` - Change the `index()` method body to

```java
List kesakos = Kesako.findAll();
render(kesakos);
```

**Edit** `app/views/Application/index.html` - After the heading, add:

```html
<ul>
#{list kesakos, as:'kesako'}
   <li>${kesako.nom}</li>
#{/list}
</ul>
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the JPA error.

**Edit** `conf/application.conf` - Uncomment the line `# db=mem`

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the page - no kesakos.


## HTML form

**Edit** `app/views/Application/index.html` - After the list, add:

```html
#{form @add()}
<p>
  <input name="kesako.nom" autofocus>

  <button name="submit" type="submit">Add Kesako</button>
</p>
#{/form}
```

**Edit** `app/controllers/Application.java` - Add the method:

```java
public static void add(final Kesako kesako) {
   kesako.save();
   index();
}
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Add kesakos.

## Command link

**Edit** `app/views/Application/index.html` - Inside the `<li>` add a link:

```html
<a href="@{delete(kesako.id)}">delete</a>
```

> As for forms, there is also a tag for generating links; this way just generates the URL.

**Edit** `conf/routes` - Add `GET /delete Application.delete`

**Edit** `app/controllers/Application.java` - Add the method, noting the `id` parameter:

```java
public static void delete(final Long id) {
   Kesako kesako = Kesako.findById(id);
   kesako.delete();
   index();
}
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Delete kesakos - show the link URL and query string parameter.

**Edit** `conf/routes` - Change the delete route to `GET /delete/{id} Application.delete`

**Open** [http://localhost:9000/](http://localhost:9000/) - Delete kesakos - show the link URL and URL path parameter.

## Java extensions

**Edit** `app/views/Application/index.html` - Change the heading to:

```html
<h1>${kesakos.size()} Kesako${kesakos.pluralize()}</h1>
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Add/delete kesakos to show singular and plural forms.

> If you are lucky, at this point someone in the audience will be smart enough to point out that some plurals are not just formed by adding an 's', at which point you can change the example, and show the `pluralize` method with one or more parameters, e.g. `${kesakos.pluralize(messages.get('kesako'), messages.get('kesakos'))`}

## Form validation

**Edit** `app/controllers/Application.java` - Replace the first line of the method body (`Kesako.save();`) with the following.

```java
validation.required("nom", kesako.nom);

if (validation.hasErrors()) {
	validation.keep();
} else {
	kesako.save();           
}
```

**Edit** `app/views/Application/index.html` - immediately after the `form` tag, add:

```html
#{errors}
	<p class="error">${error}</p>
#{/errors}
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the validation error when submitting an empty name.

> The validation error is just 'Required', but we can change this.

**Edit** `conf/messages` - Add the line `validation.required = "%s" est un champ obligatoire`

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the new validation error.

> Now we get the field name, but not as a formatted label.

**Edit** `conf/messages` - Change the placeholder in `validation.required` to `&{%s}`, and add the line `nom = Le nom du Kesako`

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the new validation error.

> This lists validation errors in one place. A better way is to list the errors next to each field.

**Edit** `app/views/Application/index.html` - Replace the errors tag with:

```html
#{ifErrors}
	<p class="error">Echec de la validation</p>
#{/ifErrors}
```

... and after the text input and button, before the closing `form` tag, add:

```html
<p class="error">#{error 'nom'/}</p>
```

**Open** [http://localhost:9000/](http://localhost:9000/) - Show the new validation error.

## Testing
**Create** class `test/models/KesakoTest`

```java
package models;

import java.util.List;

import org.junit.Test;

import play.test.UnitTest;

public class KesakoTest extends UnitTest {
	
	@Test
	public void testFindAll(){
		List<Kesako> kesakoList = Kesako.findAll();
		assertEquals(2, kesakoList.size());
	}

}
```

**Run** JUnit Test with IDE - Test Failed

**Execute** `play test kesakos` - Start the Play server runtime in test mode.

**Open** [http://localhost:9000/@tests](http://localhost:9000/@tests) - Start a Play Test Runner page

**Select and Start** test `models/KesakoTest` - Test failed

**Execute**

    git add .
    git commit -m 'Failed unit test'
    git push -u origin master
    
**See a Jenkins Job** - No Deployment

**Edit** `test/data.yml` - Add some datas

```yml
# Test data

Kesako(java):
    nom:          Java is beautiful

    
Kesako(scala):
    nom:          Scala is beautiful too
```

**Edit** class `test/models/KesakoTest`

```java
package models;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;
public class KesakoTest extends UnitTest {
	@Before
	public void setUp(){
		Fixtures.deleteAllModels();
		Fixtures.loadModels("data.yml");
	}
	@Test
	public void testFindAll(){
		List<Kesako> kesakoList = Kesako.findAll();
		assertEquals(2, kesakoList.size());
	}
}
```

**Open** [http://localhost:9000/@tests](http://localhost:9000/@tests) - Start a Play Test Runner page

**Select and Start** test `models/KesakoTest` - Test success

**Create** file `test/AddKesako.test.html`

```html
#{selenium}
    // Open the home page, and check that no error occured
    open('/')
    type('kesako.nom','Java vs Scala')
    clickAndWait('submit')
	verifyTextPresent('JJJJJ')
#{/selenium}
```

**Open** [http://localhost:9000/@tests](http://localhost:9000/@tests) - Start a Play Test Runner page

**Select and Start** test `AddKesako` - Test failed (JJJJJ not found)

**Open** [http://localhost:9000/](http://localhost:9000/) - Kesako 'Java vs Scala' was added

**Edit** file `test/AddKesako.test.html`

```html
    type('kesako.nom','Java vs Scala 2')
    clickAndWait('submit')
	verifyTextPresent('Java vs Scala 2')
```

**Open** [http://localhost:9000/@tests](http://localhost:9000/@tests) - Start a Play Test Runner page

**Select and Start** test `AddKesako` - Test success

## CRUD 
**activer le module CRUD**

/conf/application.conf file décommenter la ligne : 
module.crud=${play.path}/modules/crud

**regénérer la conf Eclipse du projet**
play eclipsify kesako

**Créer une classe persistante Collab**

```
package models;

import javax.persistence.Entity;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Classe persistante représentant les cossaborateurs de SQLI.
 * @author Sylvain
 */
@Entity
public class Collab extends Model {

    @Required
    @MinSize(8)
	public String matricule;
    
    @Required
    public String nom;
    
    @Required
    public String prenom;
    
    @Required
    @MinSize(10)
    public String email;
    
    public String toString() {
    	return nom + " " + prenom;
    }
}
```

**Modifier la classe Kesako pour ajouter un lien Kesako => collab**

```
package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Classe persistante représentant un kesako
 * @author Sylvain
 */
@Entity
public class Kesako extends Model {

    @Required
    @MinSize(8)
	public String nom;
    
    @Required
    public Date date;
    
    @OneToMany(fetch=FetchType.EAGER, targetEntity=Collab.class)
    public Set<Collab> inscrits = new HashSet<Collab>();
}
```
**Créer les classes de controller pour les objets persistants**
```
package controllers;

import models.Kesako;

@CRUD.For(Kesako.class)
public class ControlleurCrudKesako extends CRUD {

}
```

```
package controllers;

import models.Collab;

@CRUD.For(Collab.class)
public class ControlleurCrudCollab extends CRUD {

}
```
** faire afficher les routes de l'application**
[http://localhost:9000/toto](http://localhost:9000/toto)



**Accéder à l'acran de gestion des entités**
[http://localhost:9000/crud/](http://localhost:9000/crud/)
montrer les routes créées par le module CRUD

**installer une route pour le module CRUD**
ajouter dans le fichier routes : 
```
*      /crud              	module:crud
```

**Accéder à la racine du module CRUD**
[http://localhost:9000/crud](http://localhost:9000/crud)

**Customiser les écrans**
C:\play\demo_kesako\kesako>play crud:ov --template Collab/list
ouvrir views/ControlleurCrudCollab/list.html

modifier l'affichage de la table : 
```
    <div id="crudListTable">
        #{crud.table fields:['matricule', 'nom', 'prenom', 'email'] /}
    </div>
```
dans layout.html
enlever le footer

**aller plus loin avec le module**
* créer physiquemenet les vues
* 
```
play crud:ov --template ControlleurCrudKesako/list
play crud:ov --template ControlleurCrudKesako/show
play crud:ov --template ControlleurCrudCollab/show
```

* Modifier les CRUD pour avoir des infos en plus dnas les pages : 
faire hériter les Controlleurs de CrudKesako

```
package controllers;

import play.db.Model;
import play.i18n.Lang;
import play.i18n.Messages;

/**
 *  la même chose que le CRUD, mais capable de donner des informations customisées concernant la classe persistante
 * @author Sylvain
 */
public abstract class CrudKesako extends CRUD {

	public static ObjectTypeKesako createObjectType(Class<? extends Model> classePersistante) {
		return new ObjectTypeKesako(classePersistante);
	}
	
	public static class ObjectTypeKesako extends CRUD.ObjectType implements Comparable<ObjectType> {
		public String descriptionIndex;
		private String titlename1;
		private String description2;
		public ObjectTypeKesako(Class<? extends Model> modelClass) {
			super(modelClass);
			this.descriptionIndex = Messages.getMessage(Lang.get(), "crud.descriptionIndex." + modelName, new Object[] {});
			this.titlename1 = Messages.getMessage(Lang.get(), "crud.titlename1." + modelName, new Object[] {});
			this.description2 = Messages.getMessage(Lang.get(), "crud.description2." + modelName, new Object[] {});
		}
	}
}
```

** dans Messages : **

```
crud.descriptionIndex.Collab = Liste des collabs
crud.titlename1.Collab = Collabs
crud.description2.Collab = Collaborateur

crud.descriptionIndex.Kesako = Liste des kesakos
crud.titlename1.Kesako = Kesakos
crud.description2.Kesako = Kesako
```

* modifier la CSS : crud.css
* 
```
body:before { content:'Play - Demo Kesako'; color:black; font-size:150%; text-transform:uppercase; letter-spacing:0.4em; }
#crud {
    color: black; font-family: Arial,Verdana,Helvetica,sans-serif; background: url(http://www.sqli.com/design/fre/images/body-home-03.jpg) repeat scroll 0 0 #000000; width: 80%;
}
#crud #crudHeader {
	background: #404040; padding: 10px 20px;
}
#crud #crudHeader h1 {
	margin: 0; font-size:150%; text-transform:uppercase;  letter-spacing:0.4em;
}
#crud #crudHeader h1 a {
	color: white; text-decoration: none;
}
#crudBreadcrumb {
	padding: 5px 20px; font-size: 80%; border-bottom: 1px solid #ddd; 
}
#crudBreadcrumb ul {
	margin: 0; padding: 0; list-style: none;
}
#crudBreadcrumb li {
	display: inline;
}
#crudBreadcrumb a {
	color: #333;
}
#crudBreadcrumb li:last-child a {
	color: #888; text-decoration: none;
}
.crudFlash {
	padding: 5px 20px; border-bottom: 1px solid #ddd; color: #fff;
}
.flashError {
	background: #c00;
}
.flashSuccess {
	background: #54BD06;
}
#crud #crudContent {
	padding: 10px 20px;
}
#crud #crudContent h2 {
	color: #333;
	font-weight: normal;
	margin-top: 10px;
}
#crud #crudContent a {
	color: #444;
}
#crud #crudContent table {
	width: 100%; border: 1px solid #ddd; border-top: none; border-collapse: collapse; table-layout: fixed; 
}
#crud #crudContent table {
}
#crud #crudContent table thead tr {
	background: #808080;
}
#crud #crudContent table th {
	color: #fff; font-weight: bold;
}
#crud #crudContent table th a {
	color: #fff; font-weight: bold;
}
#crud #crudContent table th {
	text-align: left; padding: 6px 5px;
}
#crud #crudContent table td {
	text-align: left; padding: 4px 5px;
}
#crud #crudContent table td {
	border-bottom: 1px solid #eee;
}
#crud #crudContent table tr.even {
	background: #f9f9f9;
}
#crud #crudContent table tr:last-child td {
	border-bottom: 1px solid #ddd;
}
.crudSortedAsc:after {
	content: '▼';
}
.crudSortedDesc:after {
	content: '▲';
}
form {
	margin: 0;
}
input, textarea, select {
	font-family: 'Helvetica', 'Arial', 'Sans'; font-size: 12px;
}
textarea {
	padding: 2px;
}
.objectForm {
	border: 1px solid #ddd;
}
label {
	display: block; font-weight: bold; width: 20%; float: left; color: #666; cursor: pointer; 
}
.crudField {
	padding: 10px; border-bottom: 1px solid #eee; position: relative;
}
.crud_hidden {
    display: none;
}
.crudButtons {
	background: #efefef; text-align: right; margin: 0; padding: 10px;
}
.crudField .error {
	color: #c00; padding-left: 10px; position: absolute; top: 15px; font-size: 80%;
}
.crudField .hasError {
	color: #c00;
}
.crudField select[multiple] {
	max-height: 100px;
}
.crudField .crudHelp {
	font-size: 70%; color: #888; display: block; margin-left: 20%; margin-top: 5px;
}
form .currentAttachment {
	display: block; font-size: 80%; margin-left: 20%; padding-top: 4px;
}
form .removeAttachment {
    display: block; font-size: 80%; margin-left: 20%;
}
#crud #crudFooter {
	font-size: 80%; color: #aaa; padding: 10px 20px; border-top: 1px dashed #ccc; border-bottom: 1px dashed #ccc; margin-top: 20px;
}
#crud #crudFooter a {
	color: #333;
}
.crudNew {
	text-align: right !important;
}
.crudNew a {
	text-decoration:none; color:transparent;  position:absolute;  top:1em;  right:1em;
}
.crudNew a:after {
	content: url(http://icons.iconarchive.com/icons/deleket/soft-scraps/16/Button-Add-icon.png); color:#aaa;  font-size:120%;  font-weight:bold;  
}
.crudNew a:hover {
	opacity: .8;
}
#crudList {
	position: relative;
}
#crudListAdd a {
	margin: 0; position: absolute; top: 0; right: 0; background: #DA0E1B; padding: 3px 10px; font-size: 90%; -moz-border-radius: 8px; -webkit-border-radius: 8px; color: #fff !important; text-decoration: none;
	content: url(http://icons.iconarchive.com/icons/deleket/soft-scraps/16/Button-Add-icon.png);  color:#aaa;  font-size:120%;  font-weight:bold;  
}
#crudListAdd a:hover {
	opacity: .8;
}
#crudListSearch {
	padding: 4px 5px; border: 1px solid #ddd; background: #efefef;
}
#crudListSearch input[type=text] {
	width: 40%;
}
#crudListSearch a {
	color: #111 !important; font-size: 90%;
}
#crudListPagination {
	padding: 4px 5px; border: 1px solid #ddd; background: #efefef; border-top: 0; font-size: 90%; position: relative;
}
#crudListPagination .crudCount {
	margin: 0;
}
#crudListPagination .crudPages {
	margin: 0; position: absolute; right: 5px; top: 4px;
}
#crudShow {
	position: relative;
}
.crudDelete input {
	color: #fff; border: none; margin: 0; position: absolute; top: 0; right: 0; background: #c00; padding: 2px 10px; font-size: 90%; -moz-border-radius: 8px; -webkit-border-radius: 8px; cursor: pointer;
}
.crudDelete input:hover {
	opacity: .7;
}

```

* modifier le format d'affichage des dates dans la table (table.html)
```html
	%{ } else if(_caller.type.getField(field).type == 'date') { }%
                        	${object[field]?.format()}
```

* modifier le format d'affichage des dates
application.conf : 
```
date.format.fr=dd/MM/yyyy
```



