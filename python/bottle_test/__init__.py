import sqlite3
import bottle

@bottle.route('/')
def greet():
	return "<H2> Hello World! </H2>"

@bottle.route("/list")
@bottle.route("/todo")
def todo_list():
	conn = sqlite3.connect("todo.db")
	curs = conn.cursor()
	curs.execute("SELECT id, task FROM todo WHERE status LIKE '1';")
	res = curs.fetchall()
	curs.close()
	conn.close()
	output = bottle.template('templates/make_table', rows=res)
	return output


@bottle.route('/new', method='GET')
def new_item():
	if bottle.request.GET.get('save','').strip():
		new = bottle.request.GET.get('task', '').strip()
		conn = sqlite3.connect('todo.db')
		c = conn.cursor()

		c.execute("INSERT INTO todo (task,status) VALUES (?,?)", (new,1))
		new_id = c.lastrowid

		conn.commit()
		c.close()

		return '<p>The new task was inserted into the database, the ID is %s</p>' % new_id
	else:
		return bottle.template('new_task.tpl')   
	
def main():
	bottle.debug(True)
	bottle.run(reloader=True)
	
if __name__ == '__main__':
	main()
	
