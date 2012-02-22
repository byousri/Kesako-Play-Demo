# Summary
1. Download the Play framework.
2. Create and run the application.
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

# Create and run the application

**Open** <a href="http://www.playframework.org/" class="external-link">http://www.playframework.org/</a> Download the binary

> There is no need to *install* Play as such, although you might want to add the download's directory to your `PATH`, so you can execute the `$PLAY_HOME/play` commmand directly. On my machine, though, it's just as easy to just type `~/Downloads/play-1.2.4/play` for this kind of demo.

**Execute** `play new tasks <br class="atl-forced-newline" />~ What is the application name? tasks`

> A task list application is an easy example for people to understand, but it is more fun to pick an example that is more familiar and relevant to your audience, such as 'customers' or 'cocktails'.

**Execute** `find tasks -type f` (optional) - List the generated files

> It is crucial to note that Play has not generated a lot of code here; the files are minimal stubs that you will only add to, rather than a lot of generated code that you will have to delete.

**Execute** `play run tasks` - Start the Play server runtime to run the application.

> Depending on what you are used to, start-up time may seem extremely short.

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show welcome page.

> The welcome page describes how the stub application works, which the next few steps show.

**Start** a text editor.

> You could use an IDE, such as Eclipse, already at this point. However, it is useful to start with a plain text editor to make it clear that you do not have to compile the Java code yourself.

**Edit** `conf/routes` - Show the route for the `GET /` HTTP request.

> Since Play has an HTTP focus, in incoming HTTP request is a good starting point for explaining how it works. The `routes` file, is therefore crucial, because this defines the (two-way) mapping between HTTP requests and Java methods.

**Edit** `app/controllers/Application.java` - Show the `index()` method.

**Edit** `app/views/main.html` - Show the default template: HTML 5, CSS and jQuery.

**Edit** `app/views/Application/index.html` - Show the `#{welcome /`} tag and replace it with `&lt;h1&gt;Tasks&lt;/h1&gt;`.

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the heading.

**Edit** `public/stylesheets/main.css` - Add some CSS to make things less ugly:

<div class="code">
<pre class="brush: css; gutter: false">html { border-top: 5px solid #67A927;  }
body { font-family:"Helvetica Neue"; padding:2em; background: #F7F7F7 url(/public/playmanual/logo.png) no-repeat 98% 20%; }
body:before { content:'Play task list demo'; color:#568C00; font-size:150%; text-transform:uppercase; letter-spacing:0.4em; }
ul { padding:0; list-style:none; }
li, form { width:30em; background:white; padding:1em; border:1px solid #ccc; border-radius:0.5em; margin:1em 0; position:relative; }
li a { text-decoration:none; color:transparent; position:absolute; top:1em; right:1em; }
li a:after { content:'?'; color:#aaa; font-size:120%; font-weight:bold; }
form * { font-size:120%; }
input { width:16em; }
button { cursor:pointer; color: white; background-color: #3D6F04; background-image: -webkit-linear-gradient(top, #5AA706, #3D6F04); text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); border: 1px solid #CCC; border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); border-radius:4px; }
p.error { margin:0; color:#c00; }</pre></div>

# Show dynamic data in the template

**Edit** `app/views/Application/index.html` - Change the heading to `&lt;h1&gt;${items}&lt;/h1&gt;`.

**Edit** `app/controllers/Application.java` - Change the `index()` method body to the following (omitting a semi-colon).

<div class="code">
<pre class="brush: java; gutter: false">final String items = "Things"
render(items);</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the Java compilation error.

**Edit** `app/controllers/Application.java` - Add the missing semi-colon.

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the new heading, and Java changes without compilation or deployment.

**Edit** `app/controllers/Application.java` - Replace the `items` declaration line with a `String items` method parameter.

**Open** <a href="http://localhost:9000/?items=Things" class="external-link">http://localhost:9000/?items=Things</a>

**Edit** `app/controllers/Application.java` - Undo the last change - remove the parameter and put the declaration back.


# Show message file content in the template

**Edit** `app/views/Application/index.html` - Change the heading to `&lt;h1&gt;&amp;{'model.shipments'}&lt;/h1&gt;`.

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the message key being displayed, because the message is not defined.

**Edit** `conf/messages` - Add the line `model.shipments = Shipments`

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the message being displayed.


# Eclipse

**Execute** `Control+C` - Show how little logging there is by default.

**Execute** `play eclipsify tasks` - Generate Eclipse project and class path configuration.

**Eclipse** *File » Import… » Existing projects into workspace* - Show project structure.

**Eclipse** `eclipse/tasks.launch` » Run » tasks - Start the Play server runtime from within Eclipse.

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the application running.


# IntelliJ IDEA

**Execute** `Control+C` - Show how little logging there is by default.

**Execute** `play idealize tasks` - Generate IDEA project and class path configuration.

**Execute** `open tasks/tasks.ipr` - Open the project in IDEA.

**Execute** `play run tasks` - Start the Play server again.


# JPA entity

**Edit** `app/models` - create class:

<div class="code">
<pre class="brush: java; gutter: false">@Entity
public class Task extends play.db.jpa.Model {

   public String title;
}</pre></div>

> At this point you may need to explain that `Task` is a Java Bean at run-time, because Play dynamically adds getter and setter methods for the public fields, turning them into normal Java Bean properties.

**Edit** `app/controllers/Application.java` - Change the `index()` method body to

<div class="code">
<pre class="brush: java; gutter: false">List tasks = Task.findAll();
render(tasks);</pre></div>

**Edit** `app/views/Application/index.html` - After the heading, add:

<div class="code">
<pre class="brush: html; gutter: false">&lt;ul&gt;
#{list tasks, as:'task'}
   &lt;li&gt;${task.title}&lt;/li&gt;
#{/list}
&lt;/ul&gt;</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the JPA error.

**Edit** `conf/application.conf` - Uncomment the line `# db=mem`

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the page - no tasks.


# HTML form

**Edit** `app/views/Application/index.html` - After the list, add:

<div class="code">
<pre class="brush: html; gutter: false">#{form @add()}
&lt;p&gt;
  &lt;input name="task.title" autofocus&gt;

  &lt;button type="submit"&gt;Add Task&lt;/button&gt;
&lt;/p&gt;
#{/form}</pre></div>

**Edit** `app/controllers/Application.java` - Add the method:

<div class="code">
<pre class="brush: java; gutter: false">public static void add(final Task task) {
   task.save();
   index();
}</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Add tasks.


# Command link

**Edit** `app/views/Application/index.html` - Inside the `&lt;li&gt;` add a link:

<div class="code">
<pre class="brush: html; gutter: false">&lt;a href="@{delete(task.id)}"&gt;delete&lt;/a&gt;</pre></div>

> As for forms, there is also a tag for generating links; this way just generates the URL.

**Edit** `conf/routes` - Add `GET /delete Application.delete`

**Edit** `app/controllers/Application.java` - Add the method, noting the `id` parameter:

<div class="code">
<pre class="brush: java; gutter: false">public static void delete(final Long id) {
   Task task = Task.findById(id);
   task.delete();
   index();
}</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Delete tasks - show the link URL and query string parameter.

**Edit** `conf/routes` - Change the delete route to `GET /delete/{id} Application.delete`

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Delete tasks - show the link URL and URL path parameter.


# Java extensions

**Edit** `app/views/Application/index.html` - Change the heading to:

<div class="code">
<pre class="brush: html; gutter: false">&lt;h1&gt;${tasks.size()} Task${tasks.pluralize()}&lt;/h1&gt;</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Add/delete tasks to show singular and plural forms.

> If you are lucky, at this point someone in the audience will be smart enough to point out that some plurals are not just formed by adding an 's', at which point you can change the example, and show the `pluralize` method with one or more parameters, e.g. `${tasks.pluralize(messages.get('task'), messages.get('tasks'))`}


# Form validation

**Edit** `app/controllers/Application.java` - Add the `@Valid` annotation to the add method's `Shipment` parameter, replace the first line of the method body (`Task.save();`) with the following.

<div class="code">
<pre class="brush: java; gutter: false">if (validation.hasErrors()) {
   validation.keep();
}
else {
   task.save();			
}</pre></div>

**Edit** `app/views/Application/index.html` - immediately after the `form` tag, add:

<div class="code">
<pre class="brush: html; gutter: false">#{errors}
    &lt;p class="error"&gt;${error}&lt;/p&gt;

#{/errors}</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the validation error when submitting an empty name.

> The validation error is just 'Required', but we can change this.

**Edit** `conf/messages` - Add the line `validation.required = %s is a required field`

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the new validation error.

> Now we get the field name, but not as a formatted label.

**Edit** `conf/messages` - Change the placeholder in `validation.required` to `&amp;{%s`}, and add the line `task.name = Task name`

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the new validation error.

> This lists validation errors in one place. A better way is to list the errors next to each field.

**Edit** `app/views/Application/index.html` - Replace the errors tag with:

<div class="code">
<pre class="brush: html; gutter: false">#{ifErrors}
    &lt;p class="error"&gt;Validation failed&lt;/p&gt;
#{/ifErrors}</pre></div>

… and after the text input and button, before the closing `form` tag, add:

<div class="code">

<pre class="brush: html; gutter: false">&lt;p class="error"&gt;#{error 'task.title'/}&lt;/p&gt;</pre></div>

**Open** <a href="http://localhost:9000/" class="external-link">http://localhost:9000/</a> - Show the new validation error.
