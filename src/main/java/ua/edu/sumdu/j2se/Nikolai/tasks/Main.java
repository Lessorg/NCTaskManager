package ua.edu.sumdu.j2se.Nikolai.tasks;

public class Main {

	public static void main(String[] args) {

		Task a = new Task("бег1", 4);
		Task q = new Task("Simple IN", 55, true);
		Task w = new Task("Simple OUT", 10, true);
		Task e = new Task("Inactive OUT", 0, 1000, 1, false);
		Task r = new Task("Simple bound OUT", 50, true);
		Task t = new Task("Simple bound IN", 60, true);
		Task y = new Task("Repeat inside IN", 51, 58, 2, true);
		Task u = new Task("Repeat outside IN", 0, 100, 5, true);
		Task i = new Task("Repeat outside OUT", 0, 100, 22, true);
		Task o = new Task("Repeat left OUT", 0, 40, 1, true);
		Task p = new Task("Repeat right OUT", 65, 100, 1, true);
		Task l = new Task("Repeat left intersect IN 1", 0, 55, 13, true);
		Task k = new Task("Repeat left intersect IN 2", 0, 60, 30, true);
		Task j = new Task("Repeat left intersect OUT", 0, 55, 22, true);
		Task as = new Task("Repeat right intersect IN", 55, 100, 20, true);

		ArrayTaskList f = new ArrayTaskList();

		f.add(a);
		f.add(q);
		f.add(w);
		f.add(e);
		f.add(r);
		f.add(t);
		f.add(y);
		f.add(u);
		f.add(i);
		f.add(o);
		f.add(p	);
		f.add(l);
		f.add(k);
		f.add(j);
		f.add(as);

		ArrayTaskList f1 = f.incoming(50,60);
		System.out.println(f1.size());
	}
}
