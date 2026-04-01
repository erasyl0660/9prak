import java.util.*;

abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public abstract void display(int level); // шығару
    public abstract double getBudget(); // бюджет
    public abstract int getEmployeeCount(); // адам саны
}

class Employee extends OrganizationComponent {
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        super(name);
        this.position = position;
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void display(int level) {
        System.out.println("  ".repeat(level) +
                "- " + name + " (" + position + "), зарплата: " + salary);
    }

    @Override
    public double getBudget() {
        return salary;
    }

    @Override
    public int getEmployeeCount() {
        return 1;
    }

    public String getName() {
        return name;
    }
}

class Contractor extends Employee {
    public Contractor(String name, String position, double salary) {
        super(name, position, salary);
    }

    @Override
    public double getBudget() {
        return 0; // не учитывается
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> children = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent component) {
        if (!children.contains(component)) {
            children.add(component);
        }
    }

    @Override
    public void remove(OrganizationComponent component) {
        children.remove(component);
    }

    @Override
    public void display(int level) {
        System.out.println("  ".repeat(level) + "+ Отдел: " + name);
        for (OrganizationComponent c : children) {
            c.display(level + 1);
        }
    }

    @Override
    public double getBudget() {
        double total = 0;
        for (OrganizationComponent c : children) {
            total += c.getBudget();
        }
        return total;
    }

    @Override
    public int getEmployeeCount() {
        int total = 0;
        for (OrganizationComponent c : children) {
            total += c.getEmployeeCount();
        }
        return total;
    }

    public Employee findEmployee(String name) {
        for (OrganizationComponent c : children) {
            if (c instanceof Employee e) {
                if (e.getName().equals(name)) {
                    return e;
                }
            } else if (c instanceof Department d) {
                Employee found = d.findEmployee(name);
                if (found != null) return found;
            }
        }
        return null;
    }

    public void listAllEmployees() {
        for (OrganizationComponent c : children) {
            if (c instanceof Employee e) {
                System.out.println(e.getName());
            } else if (c instanceof Department d) {
                d.listAllEmployees();
            }
        }
    }
}

public class MainOrganization {
    public static void main(String[] args) {

        Department company = new Department("Компания");

        Department it = new Department("IT");
        Department hr = new Department("HR");

        Employee e1 = new Employee("Ерасыл", "Developer", 3000);
        Employee e2 = new Employee("Иван", "Tester", 2000);
        Contractor c1 = new Contractor("John", "Freelancer", 1500);

        it.add(e1);
        it.add(e2);
        it.add(c1);

        Employee hr1 = new Employee("Анна", "HR Manager", 2500);
        hr.add(hr1);

        company.add(it);
        company.add(hr);

        System.out.println("\n=== Структура ===");
        company.display(0);

        System.out.println("\nБюджет: " + company.getBudget());

        System.out.println("Сотрудников: " + company.getEmployeeCount());

        System.out.println("\nПоиск сотрудника:");
        Employee found = it.findEmployee("Ерасыл");
        if (found != null) {
            found.display(0);
        }

        e1.setSalary(3500);
        System.out.println("\nПосле изменения зарплаты:");
        System.out.println("Бюджет: " + company.getBudget());

        System.out.println("\nВсе сотрудники:");
        company.listAllEmployees();
    }
}
