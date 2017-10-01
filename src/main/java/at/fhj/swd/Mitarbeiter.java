package at.fhj.swd;

import javax.persistence.*;

/**
 * @author Helmut Bierbaumer
 * Created on 2017/09/30.
 */
@Entity
class Mitarbeiter {
    @Id private int id;
    private String name;
    private int salary;

    public void raiseSalary(int x) {
        salary += x;
    }

    public int getSalary() {
        return salary;
    }

    public Mitarbeiter() {

    }

    public Mitarbeiter(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
